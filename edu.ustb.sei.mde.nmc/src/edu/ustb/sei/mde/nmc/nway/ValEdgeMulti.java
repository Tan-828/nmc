package edu.ustb.sei.mde.nmc.nway;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

public class ValEdgeMulti {
	EAttribute type;
	List<EObject> sourceIndex;
	List<Object> targets;

	public ValEdgeMulti(EAttribute type, List<EObject> sourceIndex, List<Object> targets) {
		super();
		this.type = type;
		this.sourceIndex = sourceIndex;
		this.targets = targets;
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
		ValEdgeMulti other = (ValEdgeMulti) obj;
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

	public EAttribute getType() {
		return type;
	}

	public void setType(EAttribute type) {
		this.type = type;
	}

	public List<EObject> getSourceIndex() {
		return sourceIndex;
	}

	public void setSourceIndex(List<EObject> sourceIndex) {
		this.sourceIndex = sourceIndex;
	}

	public List<Object> getTargets() {
		return targets;
	}

	public void setTargets(List<Object> targets) {
		this.targets = targets;
	}
	
}
