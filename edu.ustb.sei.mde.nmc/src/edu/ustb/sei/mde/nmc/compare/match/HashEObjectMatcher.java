package edu.ustb.sei.mde.nmc.compare.match;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;

import org.apache.commons.collections4.map.MultiKeyMap;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import edu.ustb.sei.mde.nmc.compare.CompareFactory;
import edu.ustb.sei.mde.nmc.compare.Comparison;
import edu.ustb.sei.mde.nmc.compare.EObjectIndex;
import edu.ustb.sei.mde.nmc.compare.IEObjectMatcher;
import edu.ustb.sei.mde.nmc.compare.Match;
import edu.ustb.sei.mde.nmc.compare.EObjectIndex.Side;
import edu.ustb.sei.mde.nmc.compare.match.ProximityEObjectMatcher.DistanceFunction;

public class HashEObjectMatcher implements IEObjectMatcher	{
	
	private EObjectIndex index;
	
	
	private DistanceFunction meter;

	
	private Map<EObject, Side> eObjectsToSide = Maps.newHashMap();
	@Override
	public void createMatches(Comparison comparison, Iterator<? extends EObject> leftEObjects,
			Iterator<? extends EObject> rightEObjects, Iterator<? extends EObject> originEObjects, MultiKeyMap<EObject, Double> distanceMap) {
		if (!leftEObjects.hasNext() && !rightEObjects.hasNext() && !originEObjects.hasNext()) {
			return;
		}

		/*
		 * I just follow origin file , might have change in someday
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
	}
	
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
	
	public boolean isInScope(EObject eContainer) {
		return eObjectsToSide.get(eContainer) != null;
	}

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
	
	
	// Constructor
	public HashEObjectMatcher(DistanceFunction meter, EObjectIndex index) {
		this.meter = meter;
		this.index = index;
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
	
	private boolean readyForThisTest(Comparison inProgress, EObject fastCheck) {
		EObject eContainer = fastCheck.eContainer();
		if (eContainer != null && isInScope(eContainer)) {
			return inProgress.getMatch(eContainer) != null;
		}
		return true;
	}
}
