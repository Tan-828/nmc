package edu.ustb.sei.mde.compare.match;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import edu.ustb.sei.mde.compare.CompareFactory;
import edu.ustb.sei.mde.compare.Comparison;
import edu.ustb.sei.mde.compare.EObjectIndex;
import edu.ustb.sei.mde.compare.IEObjectMatcher;
import edu.ustb.sei.mde.compare.Match;
import edu.ustb.sei.mde.compare.ScopeQuery;
import edu.ustb.sei.mde.compare.EObjectIndex.Side;
import edu.ustb.sei.mde.compare.match.ProximityEObjectMatcher.DistanceFunction;

/**
 * This matcher is using a distance function to match EObject. It guarantees that elements are matched with
 * the other EObject having the lowest distance. If two elements have the same distance regarding the other
 * EObject it will arbitrary pick one. (You should probably not rely on this and make sure your distance only
 * return 0 if both EObject have the very same content). The matcher will try to use the fact that it is a
 * distance to achieve a suitable scalability. It is also build on the following assumptions :
 * <ul>
 * <li>Most EObjects have no difference and have their corresponding EObject on the other sides of the model
 * (right and origins)</li>
 * <li>Two consecutive calls on the distance function with the same parameters will give the same distance.
 * </li>
 * </ul>
 * The scalability you'll get will highly depend on the complexity of the distance function. The
 * implementation is not caching any distance result from two EObjects.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class ProximityEObjectMatcher implements IEObjectMatcher, ScopeQuery {

	/**
	 * The index which keep the EObjects.
	 */
	private EObjectIndex index;
	
	// lyt
	private DistanceFunction meter;

	/**
	 * Keeps track of which side was the EObject from.
	 */
	private Map<EObject, Side> eObjectsToSide = Maps.newHashMap();
	
	// lyt
	public ProximityEObjectMatcher(DistanceFunction meter, EObjectIndex index) {
		this.meter = meter;
		this.index = index;
	}

	/**
	 * {@inheritDoc}
	 */

	public void createMatches(Comparison comparison, Iterator<? extends EObject> leftEObjects,
			Iterator<? extends EObject> rightEObjects, Iterator<? extends EObject> originEObjects) {
		if (!leftEObjects.hasNext() && !rightEObjects.hasNext() && !originEObjects.hasNext()) {
			return;
		}

		/*
		 * We are iterating through the three sides of the scope at the same time so that index might apply
		 * pre-matching strategies elements if they wish.
		 */
		while (leftEObjects.hasNext() || rightEObjects.hasNext() || originEObjects.hasNext()) {

			if (leftEObjects.hasNext()) {
				EObject next = leftEObjects.next();
				index.index(next, Side.LEFT);
				eObjectsToSide.put(next, Side.LEFT);
			}

			if (rightEObjects.hasNext()) {
				EObject next = rightEObjects.next();
				index.index(next, Side.RIGHT);
				eObjectsToSide.put(next, Side.RIGHT);
			}

			if (originEObjects.hasNext()) {
				EObject next = originEObjects.next();
				index.index(next, Side.ORIGIN);
				eObjectsToSide.put(next, Side.ORIGIN);
			}

		}

		matchIndexedObjects(comparison);

//		createUnmatchesForRemainingObjects(comparison);
		// 目前看来submatches没啥用，先注释掉
//		restructureMatchModel(comparison);	

	}

	/**
	 * Match elements for real, if no match is found for an element, an object will be created to represent
	 * this unmatch and the element will not be processed again.
	 * 
	 * @param comparison
	 *            the current comparison.
	 * @param monitor
	 *            monitor to track progress.
	 */
	private void matchIndexedObjects(Comparison comparison) {
		Iterable<EObject> todo = index.getValuesStillThere(Side.LEFT);
		while (todo.iterator().hasNext()) {
			todo = matchList(comparison, todo, true);
		}
		todo = index.getValuesStillThere(Side.RIGHT);
		while (todo.iterator().hasNext()) {
			todo = matchList(comparison, todo, true);
		}

	}

	/**
	 * Create all the Match objects for the remaining EObjects.
	 * 
	 * @param comparison
	 *            the current comparison.
	 * @param monitor
	 *            a monitor to track progress.
	 */
	private void createUnmatchesForRemainingObjects(Comparison comparison) {
		for (EObject notFound : index.getValuesStillThere(Side.RIGHT)) {
			areMatching(comparison, null, notFound, null);
		}
		for (EObject notFound : index.getValuesStillThere(Side.LEFT)) {
			areMatching(comparison, notFound, null, null);
		}
		for (EObject notFound : index.getValuesStillThere(Side.ORIGIN)) {
			areMatching(comparison, null, null, notFound);
		}
	}

	/**
	 * Process the list of objects matching them. This method might not be able to process all the EObjects if
	 * - for instance, their container has not been matched already. Every object which could not be matched
	 * is returned in the list.
	 * 
	 * @param comparison
	 *            the comparison being built.
	 * @param todoList
	 *            the list of objects to process.
	 * @param createUnmatches
	 *            whether elements which have no match should trigger the creation of a Match object (meaning
	 *            we won't try to match them afterwards) or not.
	 * @param monitor
	 *            a monitor to track progress.
	 * @return the list of EObjects which could not be processed for some reason.
	 */
	private Iterable<EObject> matchList(Comparison comparison, Iterable<EObject> todoList,
			boolean createUnmatches) {
		Set<EObject> remainingResult = Sets.newLinkedHashSet();
		List<EObject> requiredContainers = Lists.newArrayList();
		Iterator<EObject> todo = todoList.iterator();
		while (todo.hasNext()) {
			EObject next = todo.next();
			/*
			 * Let's first add every container which is in scope
			 */
			EObject container = next.eContainer();
			while (container != null && isInScope(container)) {
				if (comparison.getMatch(container) == null) {
					requiredContainers.add(0, container);
				}
				container = container.eContainer();
			}
		}
		Iterator<EObject> containersAndTodo = Iterators.concat(requiredContainers.iterator(),
				todoList.iterator());	// 父节点在前
		while (containersAndTodo.hasNext()) {
			EObject next = containersAndTodo.next();
			/*
			 * At this point you need to be sure the element has not been matched in any other way before.
			 */
			if (comparison.getMatch(next) == null) {
				if (!tryToMatch(comparison, next, createUnmatches)) {
					remainingResult.add(next);
				}
			}
		}
		return remainingResult;
	}

	/**
	 * Try to create a Match. If the match got created, register it (having actual left/right/origin matches
	 * or not), if not, then return false. Cases where it might not create the match : if some required data
	 * has not been computed yet (for instance if the container of an object has not been matched and if the
	 * distance need to know if it's match to find the children matches).
	 * 
	 * @param comparison
	 *            the comparison under construction, it will be updated with the new match.
	 * @param a
	 *            object to match.
	 * @param createUnmatches
	 *            whether elements which have no match should trigger the creation of a Match object (meaning
	 *            we won't try to match them afterwards) or not.
	 * @return false if the conditions are not fulfilled to create the match, true otherwhise.
	 */
	private boolean tryToMatch(Comparison comparison, EObject a, boolean createUnmatches) {
		boolean okToMatch = false;
		Side aSide = eObjectsToSide.get(a);
		assert aSide != null;
		Side bSide = Side.LEFT;
		Side cSide = Side.RIGHT;
		if (aSide == Side.RIGHT) {
			bSide = Side.LEFT;
			cSide = Side.ORIGIN;
		} else if (aSide == Side.LEFT) {
			bSide = Side.RIGHT;
			cSide = Side.ORIGIN;
		} else if (aSide == Side.ORIGIN) {
			bSide = Side.LEFT;
			cSide = Side.RIGHT;
		}
		assert aSide != bSide;
		assert bSide != cSide;
		assert cSide != aSide;
		
		// lyt
		Map<Side, EObject> closests = findClosests(comparison, a, aSide);
		
		if (closests != null) {
			EObject lObj = closests.get(bSide);
			EObject aObj = closests.get(cSide);
			if (lObj != null || aObj != null) {
				// we have at least one other match
				areMatching(comparison, closests.get(Side.LEFT), closests.get(Side.RIGHT),
						closests.get(Side.ORIGIN));
				okToMatch = true;
			} else if (createUnmatches) {
				areMatching(comparison, closests.get(Side.LEFT), closests.get(Side.RIGHT),
						closests.get(Side.ORIGIN));
				okToMatch = true;
			}
		}
		return okToMatch;
	}

	/**
	 * Process all the matches of the given comparison and re-attach them to their parent if one is found.
	 * 
	 * @param comparison
	 *            the comparison to restructure.
	 * @param monitor
	 *            a monitor to track progress.
	 */
	private void restructureMatchModel(Comparison comparison) {
		Iterator<Match> it = ImmutableList.copyOf(Iterators.filter(comparison.eAllContents(), Match.class))
				.iterator();

		while (it.hasNext()) {
			Match cur = it.next();
			EObject possibleContainer = null;
			if (cur.getLeft() != null) {
				possibleContainer = cur.getLeft().eContainer();
			}
			if (possibleContainer == null && cur.getRight() != null) {
				possibleContainer = cur.getRight().eContainer();
			}
			if (possibleContainer == null && cur.getOrigin() != null) {
				possibleContainer = cur.getOrigin().eContainer();
			}
			Match possibleContainerMatch = comparison.getMatch(possibleContainer);
			if (possibleContainerMatch != null) {
				((BasicEList<Match>)possibleContainerMatch.getSubmatches()).addUnique(cur);
			}
		}
	}

	/**
	 * Register the given object as a match and add it in the comparison.
	 * 
	 * @param comparison
	 *            container for the Match.
	 * @param left
	 *            left element.
	 * @param right
	 *            right element
	 * @param origin
	 *            origin element.
	 * @return the created match.
	 */
	private Match areMatching(Comparison comparison, EObject left, EObject right, EObject origin) {
		Match result = CompareFactory.eINSTANCE.createMatch();
		result.setLeft(left);
		result.setRight(right);
		result.setOrigin(origin);
		((BasicEList<Match>)comparison.getMatches()).addUnique(result);
		if (left != null) {
			index.remove(left, Side.LEFT);
		}
		if (right != null) {
			index.remove(right, Side.RIGHT);
		}
		if (origin != null) {
			index.remove(origin, Side.ORIGIN);
		}
		return result;
	}

	/**
	 * This represent a distance function used by the {@link ProximityEObjectMatcher} to compare EObjects and
	 * retrieve the closest EObject from one side to another. Axioms of the distance are supposed to be
	 * respected more especially :
	 * <ul>
	 * <li>symetry : dist(a,b) == dist(b,a)</li>
	 * <li>separation :dist(a,a) == 0</li>
	 * </ul>
	 * Triangular inequality is not leveraged with the current implementation but might be at some point to
	 * speed up the indexing. <br/>
	 * computing the distance between two EObjects should be a <b> fast operation</b> or the scalability of
	 * the whole matching phase will be poor.
	 * 
	 * @author cedric brun <cedric.brun@obeo.fr>
	 */
	public interface DistanceFunction {
		/**
		 * Return the distance between two EObjects. When the two objects should considered as completely
		 * different the implementation is expected to return Double.MAX_VALUE.
		 * 
		 * @param inProgress
		 *            the comparison being processed right now. This might be used for the distance to
		 *            retrieve other matches for instance.
		 * @param a
		 *            first object.
		 * @param b
		 *            second object.
		 * @return the distance between the two EObjects or Double.MAX_VALUE when the objects are considered
		 *         too different to be the same.
		 */
		double distance(Comparison inProgress, EObject a, EObject b);

		/**
		 * Check that two objects are equals from the distance function point of view (distance should be 0)
		 * You should prefer this method when you just want to check objects are not equals enabling the
		 * distance to stop sooner.
		 * 
		 * @param inProgress
		 *            the comparison being processed right now. This might be used for the distance to
		 *            retrieve other matches for instance.
		 * @param a
		 *            first object.
		 * @param b
		 *            second object.
		 * @return true of the two objects are equals, false otherwise.
		 */
		boolean areIdentic(Comparison inProgress, EObject a, EObject b);

	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isInScope(EObject eContainer) {
		return eObjectsToSide.get(eContainer) != null;
	}

	// lyt
	public Map<Side, EObject> findClosests(Comparison inProgress, EObject eObj, Side passedObjectSide) {
		
		if (!readyForThisTest(inProgress, eObj)) {
			return null;
		}			
		Map<Side, EObject> result = new HashMap<EObjectIndex.Side, EObject>(3);
		result.put(passedObjectSide, eObj);
		
		Set<EObject> lefts = index.getLefts(eObj);
		Set<EObject> rights = index.getRights(eObj);
		Set<EObject> origins = index.getOrigins(eObj);
		
		if (passedObjectSide == Side.LEFT) {
			EObject closestRight = findTheClosest(inProgress, eObj, lefts, rights, true);
			EObject closestOrigin = findTheClosest(inProgress, eObj, lefts, origins, true);
			result.put(Side.RIGHT, closestRight);
			result.put(Side.ORIGIN, closestOrigin);
		} else if (passedObjectSide == Side.RIGHT) {
			EObject closestLeft = findTheClosest(inProgress, eObj, rights, lefts, true);
			EObject closestOrigin = findTheClosest(inProgress, eObj, rights, origins, true);
			result.put(Side.LEFT, closestLeft);
			result.put(Side.ORIGIN, closestOrigin);

		} else if (passedObjectSide == Side.ORIGIN) {
			EObject closestLeft = findTheClosest(inProgress, eObj, origins, lefts, true);
			EObject closestRight = findTheClosest(inProgress, eObj, origins, rights, true);
			result.put(Side.LEFT, closestLeft);
			result.put(Side.RIGHT, closestRight);
		}
		return result;
	}
	
	/**
	 * Check whether we have all the required information to search for this object matches.
	 */
	private boolean readyForThisTest(Comparison inProgress, EObject fastCheck) {
		EObject eContainer = fastCheck.eContainer();
		if (eContainer != null && isInScope(eContainer)) {
			return inProgress.getMatch(eContainer) != null;
		}
		return true;
	}
	
	/**
	 * return the closest EObject of the passed one found in the sideToFind storage.
	 */
	// lyt
	private EObject findTheClosest(Comparison inProgress, final EObject eObj, Set<EObject> original,
			Set<EObject> storageToSearchFor, boolean shouldDoubleCheck) {
		
		/*
		 * We are starting by looking for EObject having a distance of 0. It means we'll iterate two times in
		 * the worst case but it is very likely that the EObject has another version with a distance of 0. It
		 * is also based on the assumption that calling distance() with a 0 max distance triggers shortcuts
		 * and is faster than calling the same distance() method with a max_distance > 0.
		 */
		Candidate best = findIdenticMatch(inProgress, eObj, storageToSearchFor);
		if (best.some()) {
			return best.eObject;
		}

		SortedMap<Double, EObject> candidates = Maps.newTreeMap();
		/*
		 * We could not find an EObject which is identical, let's search again and find the closest EObject.
		 */
		Iterator<EObject> it = storageToSearchFor.iterator();
		while (best.distance != 0 && it.hasNext()) {
			EObject potentialClosest = it.next();
			double dist = meter.distance(inProgress, eObj, potentialClosest);
			if (dist < best.distance) {
				if (shouldDoubleCheck) {
					// We need to double check the currentlyDigging has the same object as the closest !
					candidates.put(Double.valueOf(dist), potentialClosest);
				} else {
					best.distance = dist;
					best.eObject = potentialClosest;
				}
			}
		}
		if (shouldDoubleCheck) {
			for (Entry<Double, EObject> entry : candidates.entrySet()) {
				EObject doubleCheck = findTheClosest(inProgress, entry.getValue(), storageToSearchFor, original,
						false);
				if (doubleCheck == eObj) {
					best.eObject = entry.getValue();
					best.distance = entry.getKey().doubleValue();
					break;
				}
			}
		}
		return best.eObject;
	}
	
	/**
	 * Look for a perfect match (identic content) in the given list of candidates.
	 */
	private Candidate findIdenticMatch(Comparison inProgress, final EObject eObj, Set<EObject> candidates) {
		Iterator<EObject> it = candidates.iterator();
		Candidate best = new Candidate();

		while (it.hasNext() && !best.some()) {
			EObject fastCheck = it.next();
			if(readyForThisTest(inProgress, fastCheck)) {
				if (meter.areIdentic(inProgress, eObj, fastCheck)) {
					best.eObject = fastCheck;
					best.distance = 0;
				}
			}
		}
		return best;
	}
	
	private static class Candidate {
		/**
		 * an EObject.
		 */
		protected EObject eObject;

		/**
		 * A distance.
		 */
		protected double distance = Double.MAX_VALUE;

		/**
		 * return true of the candidate has an {@link EObject}.
		 * 
		 * @return true of the candidate has an {@link EObject}.
		 */
		public boolean some() {
			return eObject != null;
		}
	}
	
}

