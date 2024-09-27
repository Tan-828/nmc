package edu.ustb.sei.mde.nmc.compare.match;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import edu.ustb.sei.mde.nmc.compare.EObjectIndex;

/**
 * An implementation of EObjectIndex which segregates given EObjects using their type and then delegate to
 * other indexes.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class ByTypeIndex implements EObjectIndex {
	/**
	 * All the type specific indexes, created on demand.
	 */
	private Map<String, EObjectIndex> allIndexes;

	// lyt
	public ByTypeIndex() {
		this.allIndexes = Maps.newHashMap();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.eobject.EObjectIndex#getValuesStillThere(org.eclipse.emf.compare.match.eobject.EObjectIndex.Side)
	 */
	public Iterable<EObject> getValuesStillThere(Side side) {
		List<Iterable<EObject>> allLists = Lists.newArrayList();
		for (EObjectIndex typeSpecificIndex : allIndexes.values()) {
			allLists.add(typeSpecificIndex.getValuesStillThere(side));
		}
		return Iterables.concat(allLists);
	}

	// lyt
	private EObjectIndex getOrCreate(EObject obj) {
		String key = eClassKey(obj);
		EObjectIndex found = allIndexes.get(key);
		if (found == null) {
			found = new ProximityIndex();	// change this
			allIndexes.put(key, found);
		}
		return found;
	}

	/**
	 * Compute a key identifying the EClass of the given EObject.
	 * 
	 * @param obj
	 *            any eObject.
	 * @return a key for its EClass.
	 */
	private String eClassKey(EObject obj) {
		EClass clazz = obj.eClass();
		if (clazz.getEPackage() != null) {
			return clazz.getEPackage().getNsURI() + ":" + clazz.getName(); //$NON-NLS-1$
		}
		return clazz.getName();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.eobject.EObjectIndex#remove(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.compare.match.eobject.EObjectIndex.Side)
	 */
	public void remove(EObject obj, Side side) {
		EObjectIndex typeSpecificIndex = getOrCreate(obj);
		typeSpecificIndex.remove(obj, side);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.eobject.EObjectIndex#index(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.compare.match.eobject.EObjectIndex.Side)
	 */
	public void index(EObject eObjs, Side side) {
		EObjectIndex typeSpecificIndex = getOrCreate(eObjs);
		typeSpecificIndex.index(eObjs, side);
	}

	@Override
	public Set<EObject> getLefts(EObject obj) {
		EObjectIndex typeSpecificIndex = getOrCreate(obj);
		return typeSpecificIndex.getLefts(obj);
	}

	@Override
	public Set<EObject> getRights(EObject obj) {
		EObjectIndex typeSpecificIndex = getOrCreate(obj);
		return typeSpecificIndex.getRights(obj);
	}

	@Override
	public Set<EObject> getOrigins(EObject obj) {
		EObjectIndex typeSpecificIndex =getOrCreate(obj);
		return typeSpecificIndex.getOrigins(obj);
	}

}

