package edu.ustb.sei.mde.compare.match;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.InternalEList;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import edu.ustb.sei.mde.compare.CompareFactory;
import edu.ustb.sei.mde.compare.IDFunction;
import edu.ustb.sei.mde.compare.Match;
import edu.ustb.sei.mde.compare.EObjectIndex.Side;
import edu.ustb.sei.mde.compare.match.IdentifierEObjectMatcher.SwitchMap;


/**
 * Computes matches from eObjects. We'll only iterate once on each of the three sides, building the
 * matches as we go.
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 */
public class MatchComputationByID implements IDFunction{

	/** Matches created by the {@link #compute()} process. */
	private final Set<Match> matches;

	/**
	 * We will try and mimic the structure of the input model. These maps do not need to be ordered, we
	 * only need fast lookup. Map each match to its left eObject.
	 */
	private final Map<EObject, Match> leftEObjectsToMatch;

	/** Map each match to its right eObject. */
	private final Map<EObject, Match> rightEObjectsToMatch;

	/** Map each match to its origin eObject. */
	private final Map<EObject, Match> originEObjectsToMatch;

	/** Left eObjects to match. */
	private Iterator<? extends EObject> leftEObjects;

	/** Right eObjects to match. */
	private Iterator<? extends EObject> rightEObjects;

	/** Origin eObjects to match. */
	private Iterator<? extends EObject> originEObjects;

	/** Remaining left objects after matching. */
	private List<EObject> leftEObjectsNoID;

	/** Remaining right objects after matching. */
	private List<EObject> rightEObjectsNoID;

	/** Remaining origin objects after matching. */
	private List<EObject> originEObjectsNoID;

	/**
	 * This lookup map will be used by iterations on right and origin to find the match in which they
	 * should add themselves.
	 */
	private SwitchMap<String, Match> idProxyMap;
	
	/**
	 * Constructor.
	 * 
	 * @param leftEObjects
	 *            Left eObjects to match.
	 * @param rightEObjects
	 *            Right eObjects to match.
	 * @param originEObjects
	 *            Origin eObjects to match.
	 * @param leftEObjectsNoID
	 *            Remaining left objects after matching.
	 * @param rightEObjectsNoID
	 *            Remaining left objects after matching.
	 * @param originEObjectsNoID
	 *            Remaining left objects after matching.
	 */
	MatchComputationByID(Iterator<? extends EObject> leftEObjects, Iterator<? extends EObject> rightEObjects,
			Iterator<? extends EObject> originEObjects, final List<EObject> leftEObjectsNoID,
			final List<EObject> rightEObjectsNoID, final List<EObject> originEObjectsNoID) {
		this.matches = Sets.newLinkedHashSet();
		this.leftEObjectsToMatch = Maps.newHashMap();
		this.rightEObjectsToMatch = Maps.newHashMap();
		this.originEObjectsToMatch = Maps.newHashMap();
		this.idProxyMap = new SwitchMap<String, Match>();
		this.leftEObjects = leftEObjects;
		this.rightEObjects = rightEObjects;
		this.originEObjects = originEObjects;
		this.leftEObjectsNoID = leftEObjectsNoID;
		this.rightEObjectsNoID = rightEObjectsNoID;
		this.originEObjectsNoID = originEObjectsNoID;
	}
	
	/**
	 * Returns the matches created by this computation.
	 * 
	 * @return the matches created by this computation.
	 */
	public Set<Match> getMatches() {
		return matches;
	}

	/**
	 * Computes matches.
	 */
	public void compute() {
		computeLeftSide();
		computeRightSide();
		computeOriginSide();
		reorganizeMatches();
	}

	/**
	 * Computes matches for left side.
	 */
	private void computeLeftSide() {
		while (leftEObjects.hasNext()) {
			final EObject left = leftEObjects.next();
			final String identifier = idComputation.apply(left);
			if (identifier != null) {
				final Match match = CompareFactory.eINSTANCE.createMatch();
				match.setLeft(left);
				// Can we find a parent? Assume we're iterating in containment order
				final EObject parentEObject = getParentEObject(left);
				final Match parent = leftEObjectsToMatch.get(parentEObject);
				if (parent != null) {
					((InternalEList<Match>)parent.getSubmatches()).addUnique(match);
				} else {
					matches.add(match);
				}
				final boolean isAlreadyContained = idProxyMap.put(left.eIsProxy(), identifier, match);
				if (isAlreadyContained) {
					reportDuplicateID(Side.LEFT, left);
				}
				leftEObjectsToMatch.put(left, match);
			} else {
				leftEObjectsNoID.add(left);
			}
		}
	}

	/**
	 * Computes matches for right side.
	 */
	private void computeRightSide() {
		while (rightEObjects.hasNext()) {
			final EObject right = rightEObjects.next();
			// Do we have an existing match?
			final String identifier = idComputation.apply(right);
			if (identifier != null) {
				Match match = idProxyMap.get(right.eIsProxy(), identifier);
				if (match != null) {
					if (match.getRight() != null) {
						reportDuplicateID(Side.RIGHT, right);
					}
					match.setRight(right);
					rightEObjectsToMatch.put(right, match);
				} else {
					// Otherwise, create and place it.
					match = CompareFactory.eINSTANCE.createMatch();
					match.setRight(right);
					// Can we find a parent?
					final EObject parentEObject = getParentEObject(right);
					final Match parent = rightEObjectsToMatch.get(parentEObject);
					if (parent != null) {
						((InternalEList<Match>)parent.getSubmatches()).addUnique(match);
					} else {
						matches.add(match);
					}
					rightEObjectsToMatch.put(right, match);
					idProxyMap.put(right.eIsProxy(), identifier, match);
				}
			} else {
				rightEObjectsNoID.add(right);
			}
		}
	}

	/**
	 * Computes matches for origin side.
	 */
	private void computeOriginSide() {
		while (originEObjects.hasNext()) {
			final EObject origin = originEObjects.next();
			// Do we have an existing match?
			final String identifier = idComputation.apply(origin);
			if (identifier != null) {
				Match match = idProxyMap.get(origin.eIsProxy(), identifier);
				if (match != null) {
					if (match.getOrigin() != null) {
						reportDuplicateID(Side.ORIGIN, origin);
					}
					match.setOrigin(origin);
					originEObjectsToMatch.put(origin, match);
				} else {
					// Otherwise, create and place it.
					match = CompareFactory.eINSTANCE.createMatch();
					match.setOrigin(origin);
					// Can we find a parent?
					final EObject parentEObject = getParentEObject(origin);
					final Match parent = originEObjectsToMatch.get(parentEObject);
					if (parent != null) {
						((InternalEList<Match>)parent.getSubmatches()).addUnique(match);
					} else {
						matches.add(match);
					}
					idProxyMap.put(origin.eIsProxy(), identifier, match);
					originEObjectsToMatch.put(origin, match);
				}
			} else {
				originEObjectsNoID.add(origin);
			}
		}
	}

	/**
	 * Reorganize matches.
	 */
	private void reorganizeMatches() {
		// For all root matches, check if they can be put under another match.
		for (Match match : ImmutableSet.copyOf(matches)) {
			EObject parentEObject = getParentEObject(match.getLeft());
			Match parent = leftEObjectsToMatch.get(parentEObject);
			if (parent != null) {
				matches.remove(match);
				((InternalEList<Match>)parent.getSubmatches()).addUnique(match);
			} else {
				parentEObject = getParentEObject(match.getRight());
				parent = rightEObjectsToMatch.get(parentEObject);
				if (parent != null) {
					matches.remove(match);
					((InternalEList<Match>)parent.getSubmatches()).addUnique(match);
				} else {
					parentEObject = getParentEObject(match.getOrigin());
					parent = originEObjectsToMatch.get(parentEObject);
					if (parent != null) {
						matches.remove(match);
						((InternalEList<Match>)parent.getSubmatches()).addUnique(match);
					}
				}
			}
		}
	}
}


/**
 * Helper class to manage two different maps within one class based on a switch boolean.
 *
 * @param <K>
 *            The class used as key in the internal maps.
 * @param <V>
 *            The class used as value in the internal maps.
 */
private class SwitchMap<K, V> {

	/**
	 * Map used when the switch boolean is true.
	 */
	final Map<K, V> trueMap = Maps.newHashMap();

	/**
	 * Map used when the switch boolean is false.
	 */
	final Map<K, V> falseMap = Maps.newHashMap();

	/**
	 * Puts the key-value pair in the map corresponding to the switch.
	 *
	 * @param switcher
	 *            The boolean variable defining which map is to be used.
	 * @param key
	 *            The key which is to be put into a map.
	 * @param value
	 *            The value which is to be put into a map.
	 * @return {@code true} if the key was already contained in the chosen map, {@code false} otherwise.
	 */
	public boolean put(boolean switcher, K key, V value) {
		final Map<K, V> selectedMap = getMap(switcher);
		final boolean isContained = selectedMap.containsKey(key);
		selectedMap.put(key, value);
		return isContained;
	}

	/**
	 * Returns the value mapped to key.
	 *
	 * @param switcher
	 *            The boolean variable defining which map is to be used.
	 * @param key
	 *            The key for which the value is looked up.
	 * @return The value {@link V} if it exists, {@code null} otherwise.
	 */
	public V get(boolean switcher, K key) {
		final Map<K, V> selectedMap = getMap(switcher);
		return selectedMap.get(key);
	}

	/**
	 * Selects the map based on the given boolean.
	 *
	 * @param switcher
	 *            Defined which map is to be used.
	 * @return {@link #trueMap} if {@code switcher} is true, {@link #falseMap} otherwise.
	 */
	private Map<K, V> getMap(boolean switcher) {
		if (switcher) {
			return falseMap;
		} else {
			return trueMap;
		}
	}
}

