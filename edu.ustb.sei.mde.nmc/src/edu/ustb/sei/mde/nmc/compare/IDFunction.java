package edu.ustb.sei.mde.nmc.compare;

import java.util.Map;

import org.apache.commons.collections4.map.MultiKeyMap;
import org.eclipse.emf.ecore.EObject;

public interface IDFunction extends HashFunction{

	void compute(Map<EObject, String> eObjectsToID, MultiKeyMap<EObject, Double> distanceMap);
}
