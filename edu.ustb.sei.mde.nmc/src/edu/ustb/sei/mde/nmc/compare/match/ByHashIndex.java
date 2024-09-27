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


public class ByHashIndex implements EObjectIndex{
	private Map<String, EObjectIndex> allIndexes;

	// tlt
	public ByHashIndex() {
		this.allIndexes = Maps.newHashMap();
	}
	
	@Override
	public Iterable<EObject> getValuesStillThere(Side side) {
		List<Iterable<EObject>> allLists = Lists.newArrayList();
		for (EObjectIndex typeSpecificIndex : allIndexes.values()) {
			allLists.add(typeSpecificIndex.getValuesStillThere(side));
		}
		return Iterables.concat(allLists);
	}

	@Override
	public void remove(EObject eObj, Side side) {
		EObjectIndex typeSpecificIndex = getOrCreate(eObj);
		typeSpecificIndex.remove(eObj, side);
		
	}

	//getOrCreate·½·¨
	private EObjectIndex getOrCreate(EObject obj) {
		String key = eClassKey(obj);
		EObjectIndex found = allIndexes.get(key);
		if (found == null) {
			found = new ProximityIndex();	// change this
			allIndexes.put(key, found);
		}
		return found;
	}

	private String eClassKey(EObject obj) {
		EClass clazz = obj.eClass();
		if (clazz.getEPackage() != null) {
			return clazz.getEPackage().getNsURI() + ":" + clazz.getName(); //$NON-NLS-1$
		}
		return clazz.getName();
	}

	@Override
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
