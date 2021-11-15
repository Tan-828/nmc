package edu.ustb.sei.mde.compare.match;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import edu.ustb.sei.mde.compare.EObjectIndex;

public class ByHashIndex implements EObjectIndex{

	@Override
	public Iterable<EObject> getValuesStillThere(Side side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(EObject eObj, Side side) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void index(EObject eObj, Side side) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<EObject> getLefts(EObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<EObject> getRights(EObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<EObject> getOrigins(EObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
