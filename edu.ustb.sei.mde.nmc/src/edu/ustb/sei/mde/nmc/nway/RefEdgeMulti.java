package edu.ustb.sei.mde.nmc.nway;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class RefEdgeMulti {
	EReference type;
	List<EObject> sourceIndex;
	List<List<EObject>> targetsIndex;
	
	public RefEdgeMulti(EReference type, List<EObject> sourceIndex, List<List<EObject>> targetsIndex) {
		super();
		this.type = type;
		this.sourceIndex = sourceIndex;
		this.targetsIndex = targetsIndex;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sourceIndex == null) ? 0 : sourceIndex.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RefEdgeMulti other = (RefEdgeMulti) obj;
		if (sourceIndex == null) {
			if (other.sourceIndex != null)
				return false;
		} else if (!sourceIndex.equals(other.sourceIndex))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	public EReference getType() {
		return type;
	}
	public void setType(EReference type) {
		this.type = type;
	}
	public List<EObject> getSourceIndex() {
		return sourceIndex;
	}
	public void setSourceIndex(List<EObject> sourceIndex) {
		this.sourceIndex = sourceIndex;
	}
	public List<List<EObject>> getTargetsIndex() {
		return targetsIndex;
	}
	public void setTargetsIndex(List<List<EObject>> targetsIndex) {
		this.targetsIndex = targetsIndex;
	}

}
