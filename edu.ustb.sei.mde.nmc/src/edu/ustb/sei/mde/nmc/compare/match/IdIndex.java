package edu.ustb.sei.mde.nmc.compare.match;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import edu.ustb.sei.mde.nmc.compare.EObjectIndex;

public class IdIndex implements EObjectIndex {

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
	


	//twb
	
	public IdIndex() {
		this.lefts = Sets.newLinkedHashSet();
		this.rights = Sets.newLinkedHashSet();
		this.origins = Sets.newLinkedHashSet();
	}
	
	@Override
	public Iterable<EObject> getValuesStillThere(Side side) {
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
	public void remove(EObject eObj, Side side) {
		// TODO Auto-generated method stub
		switch (side) {
		case RIGHT:
			rights.remove(eObj);
			break;
		case LEFT:
			lefts.remove(eObj);
			break;
		case ORIGIN:
			origins.remove(eObj);
			break;
		default:
			break;
	}

	}

	@Override
	public void index(EObject eObj, Side side) {
		// TODO Auto-generated method stub
		switch (side) {
		case RIGHT:
			rights.add(eObj);
			break;
		case LEFT:
			lefts.add(eObj);
			break;
		case ORIGIN:
			origins.add(eObj);
			break;

		default:
			break;
	}

	}
	
	public void setIdentifier(EObject eObj, String id) {
		
	}

	@Override
	public Set<EObject> getLefts(EObject obj) {
		// TODO Auto-generated method stub
		return lefts;
	}

	@Override
	public Set<EObject> getRights(EObject obj) {
		// TODO Auto-generated method stub
		return rights;
	}

	@Override
	public Set<EObject> getOrigins(EObject obj) {
		// TODO Auto-generated method stub
		return origins;
	}

}
