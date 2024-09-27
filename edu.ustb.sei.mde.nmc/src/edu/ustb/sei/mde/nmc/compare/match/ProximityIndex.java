package edu.ustb.sei.mde.nmc.compare.match;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import edu.ustb.sei.mde.nmc.compare.Comparison;
import edu.ustb.sei.mde.nmc.compare.EObjectIndex;
import edu.ustb.sei.mde.nmc.compare.ScopeQuery;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.eclipse.emf.ecore.EObject;

/**
 * This class is responsible for holding several version of EObjects from sides left, right or origins and
 * provides the ability to return the closest instance (from a difference side) of a given EObject. The
 * implementation expects that the queried EObjects have all been indexed before a query is done. The
 * implementation also expects that when you're done with an EObject and if you don't want to get it back in
 * the result of subsequent queries, the EObject is removed. <br>
 * The scalability of this class will highly depend on the given distance function, especially with calls
 * having a max distance of 0. <br>
 * It is also based on the assumption that it is <b> very likely that the EObject has another version with e
 * distance of value 0</b> in the other versions sets.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class ProximityIndex implements EObjectIndex {

	/**
	 * the left objects still present in the index.
	 */
	private Set<EObject> lefts;

	/**
	 * the right objects still present in the index.
	 */
	private Set<EObject> rights;

	/**
	 * the origin objects still present in the index.
	 */
	private Set<EObject> origins;

	// lyt
	public ProximityIndex() {
		this.lefts = Sets.newLinkedHashSet();
		this.rights = Sets.newLinkedHashSet();
		this.origins = Sets.newLinkedHashSet();
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(EObject obj, Side side) {
		switch (side) {
			case RIGHT:
				rights.remove(obj);
				break;
			case LEFT:
				lefts.remove(obj);
				break;
			case ORIGIN:
				origins.remove(obj);
				break;
			default:
				break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void index(EObject eObject, Side side) {
		switch (side) {
			case RIGHT:
				rights.add(eObject);
				break;
			case LEFT:
				lefts.add(eObject);
				break;
			case ORIGIN:
				origins.add(eObject);
				break;

			default:
				break;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Collection<EObject> getValuesStillThere(final Side side) {
		Collection<EObject> result = Collections.emptyList();
		switch (side) {
			case RIGHT:
				result = ImmutableList.copyOf(rights);
				break;
			case LEFT:
				result = ImmutableList.copyOf(lefts);
				break;
			case ORIGIN:
				result = ImmutableList.copyOf(origins);
				break;
			default:
				break;
		}
		return result;
	}

	@Override
	public Set<EObject> getLefts(EObject obj) {
		return lefts;
	}

	@Override
	public Set<EObject> getRights(EObject obj) {
		return rights;
	}

	@Override
	public Set<EObject> getOrigins(EObject obj) {
		return origins;
	}

}

