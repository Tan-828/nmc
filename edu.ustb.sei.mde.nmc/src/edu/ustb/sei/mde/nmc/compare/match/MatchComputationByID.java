package edu.ustb.sei.mde.nmc.compare.match;

import static edu.ustb.sei.mde.nmc.compare.start.EMFCompare.DIAGNOSTIC_SOURCE;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.MultiKeyMap;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.InternalEList;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import edu.ustb.sei.mde.nmc.compare.CompareFactory;
import edu.ustb.sei.mde.nmc.compare.EObjectIndex;
import edu.ustb.sei.mde.nmc.compare.IDFunction;
import edu.ustb.sei.mde.nmc.compare.Match;
import edu.ustb.sei.mde.nmc.compare.EObjectIndex.Side;
import edu.ustb.sei.mde.nmc.compare.match.IdentifierEObjectMatcher;
import edu.ustb.sei.mde.nmc.compare.match.IdentifierEObjectMatcher.DefaultIDFunction;
import edu.ustb.sei.mde.nmc.compare.util.EMFCompareMessages;

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
	 * This will be used to determine what represents the "identifier" of an EObject. By default, we will use
	 * the following logic, in order (i.e. if condition 1 is fulfilled, we will not try conditions 2 and 3) :
	 * <ol>
	 * <li>If the given eObject is a proxy, it is uniquely identified by its URI fragment.</li>
	 * <li>If the eObject is located in an XMI resource and has an XMI ID, use this as its unique identifier.
	 * </li>
	 * <li>If the eObject's EClass has an eIdAttribute set, use this attribute's value.</li>
	 * </ol>
	 */
	private Function<EObject, String> idComputation = new DefaultIDFunction();
	
	/** A diagnostic to be used for reporting on the matches. */
	private BasicDiagnostic diagnostic;
	
	private EObjectIndex index;
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
			final List<EObject> rightEObjectsNoID, final List<EObject> originEObjectsNoID,
			Function<EObject, String> idComputation, BasicDiagnostic diagnostic, EObjectIndex index) {
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
		this.idComputation = idComputation;
		this.diagnostic = diagnostic;
		this.index = index;
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
	public void compute(Map<EObject, String> eObjectsToID, MultiKeyMap<EObject, Double> distanceMap) {
		computeLeftSide(eObjectsToID, distanceMap);
		computeRightSide(eObjectsToID, distanceMap);
		computeOriginSide(eObjectsToID, distanceMap);
		//reorganizeMatches();
	}

	/**
	 * Computes matches for left side.
	 */
	private void computeLeftSide(Map<EObject, String> eObjectsToID, MultiKeyMap<EObject, Double> distanceMap) {
		Iterable<EObject> todo = index.getValuesStillThere(Side.LEFT);
		Iterator<EObject> todoList = todo.iterator();
		while (todoList.hasNext()) {
			EObject left = todoList.next();
			String identifier = eObjectsToID.get(left);
			if (identifier != null) {
				final Match match = CompareFactory.eINSTANCE.createMatch();
				match.setLeft(left);
				//‘› ±≤ª”√Submatches
				// Can we find a parent? Assume we're iterating in containment order
//				final EObject parentEObject = getParentEObject(left);
//				final Match parent = leftEObjectsToMatch.get(parentEObject);
//				if (parent != null) {
//					((InternalEList<Match>)parent.getSubmatches()).addUnique(match);
//				} else {
//					matches.add(match);
//				}
				matches.add(match);
				index.remove(left, Side.LEFT);
				final boolean isAlreadyContained = idProxyMap.put(left.eIsProxy(), identifier, match);
				if (isAlreadyContained) {
					reportDuplicateID(Side.LEFT, left, eObjectsToID);
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
	private void computeRightSide(Map<EObject, String> eObjectsToID, MultiKeyMap<EObject, Double> distanceMap) {
		Iterable<EObject> todo = index.getValuesStillThere(Side.RIGHT);
		Iterator<EObject> todoList = todo.iterator();
		while (todoList.hasNext()) {
			final EObject right = todoList.next();
			// Do we have an existing match?
			String identifier = eObjectsToID.get(right);
			if (identifier != null) {
				Match match = idProxyMap.get(right.eIsProxy(), identifier);
				if (match != null) {
					if (match.getRight() != null) {
						reportDuplicateID(Side.RIGHT, right, eObjectsToID);
					}
					match.setRight(right);
					
					// record: <eObj, best.eObject, 0>
					if(distanceMap != null) {
						distanceMap.put(right, match.getLeft(), Double.valueOf(0));
					}
					
					rightEObjectsToMatch.put(right, match);
				} else {
					// Otherwise, create and place it.
					match = CompareFactory.eINSTANCE.createMatch();
					match.setRight(right);
					// Can we find a parent?
//					final EObject parentEObject = getParentEObject(right);
//					final Match parent = rightEObjectsToMatch.get(parentEObject);
//					if (parent != null) {
//						((InternalEList<Match>)parent.getSubmatches()).addUnique(match);
//					} else {
//						matches.add(match);
//					}
					matches.add(match);
					if(distanceMap != null) {
						distanceMap.put(right, match.getLeft(), Double.MAX_VALUE);
					}
					rightEObjectsToMatch.put(right, match);
					idProxyMap.put(right.eIsProxy(), identifier, match);
				}
				index.remove(right, Side.RIGHT);
			} else {
				rightEObjectsNoID.add(right);
			}
		}
	}

	/**
	 * Computes matches for origin side.
	 */
	private void computeOriginSide(Map<EObject, String> eObjectsToID, MultiKeyMap<EObject, Double> distanceMap) {
		Iterable<EObject> todo = index.getValuesStillThere(Side.ORIGIN);
		Iterator<EObject> todoList = todo.iterator();
		while (todoList.hasNext()) {
			final EObject origin = todoList.next();
			// Do we have an existing match?
			String identifier = eObjectsToID.get(origin);
			if (identifier != null) {
				Match match = idProxyMap.get(origin.eIsProxy(), identifier);
				if (match != null) {
					if (match.getOrigin() != null) {
						reportDuplicateID(Side.ORIGIN, origin, eObjectsToID);
					}
					match.setOrigin(origin);
					
					EObject left = match.getLeft();
					// record: <eObj, best.eObject, 0>
					if(distanceMap != null) {
						if(left != null)
							distanceMap.put(origin, match.getLeft(), Double.valueOf(0));
						else
							distanceMap.put(origin, match.getRight(), Double.valueOf(0));
					}
					originEObjectsToMatch.put(origin, match);
				} else {
					// Otherwise, create and place it.
					match = CompareFactory.eINSTANCE.createMatch();
					match.setOrigin(origin);
					// Can we find a parent?
//					final EObject parentEObject = getParentEObject(origin);
//					final Match parent = originEObjectsToMatch.get(parentEObject);
//					if (parent != null) {
//						((InternalEList<Match>)parent.getSubmatches()).addUnique(match);
//					} else {
//						matches.add(match);
//					}
					matches.add(match);
					if(distanceMap != null) {
						distanceMap.put(origin, match.getLeft(), Double.MAX_VALUE);
						distanceMap.put(origin, match.getRight(), Double.MAX_VALUE);
					}
					idProxyMap.put(origin.eIsProxy(), identifier, match);
					originEObjectsToMatch.put(origin, match);
					index.remove(origin, Side.ORIGIN);
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
	
	/**
	 * This method is used to determine the parent objects during matching. The default implementation of this
	 * method returns the eContainer of the given {@code eObject}. Can be overwritten by clients to still
	 * allow proper matching when using a more complex architecture.
	 * 
	 * @param eObject
	 *            The {@link EObject} for which the parent object is to determine.
	 * @return The parent of the given {@code eObject}.
	 * @since 3.2
	 */
	protected EObject getParentEObject(EObject eObject) {
		final EObject parent;
		if (eObject != null) {
			parent = eObject.eContainer();
		} else {
			parent = null;
		}
		return parent;
	}
	
	/**
	 * Adds a warning diagnostic to the comparison for the duplicate ID.
	 * 
	 * @param side
	 *            the side where the duplicate ID was found
	 * @param eObject
	 *            the element with the duplicate ID
	 */
	private void reportDuplicateID(Side side, EObject eObject, Map<EObject, String> eObjectsToID) {
		final String duplicateID = eObjectsToID.get(eObject);
		final String sideName = side.name().toLowerCase();
		final String uriString = getUriString(eObject);
		final String message;
		if (uriString != null) {
			message = EMFCompareMessages.getString("IdentifierEObjectMatcher.duplicateIdWithResource", //$NON-NLS-1$
					duplicateID, sideName, uriString);
		} else {
			message = EMFCompareMessages.getString("IdentifierEObjectMatcher.duplicateId", //$NON-NLS-1$
					duplicateID, sideName);
		}
		diagnostic.add(new BasicDiagnostic(Diagnostic.WARNING, DIAGNOSTIC_SOURCE, 0, message, null));
	}
	
	/**
	 * Returns a String representation of the URI of the given {@code eObject}'s resource.
	 * <p>
	 * If the {@code eObject}'s resource or its URI is <code>null</code>, this method returns
	 * <code>null</code>.
	 * </p>
	 * 
	 * @param eObject
	 *            The {@link EObject} for which's resource the string representation of its URI is determined.
	 * @return A String representation of the given {@code eObject}'s resource URI.
	 */
	private String getUriString(EObject eObject) {
		String uriString = null;
		final Resource resource = eObject.eResource();
		if (resource != null && resource.getURI() != null) {
			final URI uri = resource.getURI();
			if (uri.isPlatform()) {
				uriString = uri.toPlatformString(true);
			} else {
				uriString = uri.toString();
			}
		}
		return uriString;
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
class SwitchMap<K, V> {

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