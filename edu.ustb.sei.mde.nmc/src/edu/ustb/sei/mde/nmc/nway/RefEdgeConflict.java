package edu.ustb.sei.mde.nmc.nway;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class RefEdgeConflict {
	List<Tuple4<Integer, EObject, EReference, EObject>> first;
	ConflictKind firstKind;
	List<Tuple4<Integer, EObject, EReference, EObject>> second;
	ConflictKind secondKind;
	String information;
	
	public RefEdgeConflict(List<Tuple4<Integer, EObject, EReference, EObject>> first, ConflictKind firstKind,
			List<Tuple4<Integer, EObject, EReference, EObject>> second, ConflictKind secondKind, String information) {
		super();
		this.first = first;
		this.firstKind = firstKind;
		this.second = second;
		this.secondKind = secondKind;
		this.information = information;
	}

	public List<Tuple4<Integer, EObject, EReference, EObject>> getFirst() {
		return first;
	}

	public void setFirst(List<Tuple4<Integer, EObject, EReference, EObject>> first) {
		this.first = first;
	}

	public ConflictKind getFirstKind() {
		return firstKind;
	}

	public void setFirstKind(ConflictKind firstKind) {
		this.firstKind = firstKind;
	}

	public List<Tuple4<Integer, EObject, EReference, EObject>> getSecond() {
		return second;
	}

	public void setSecond(List<Tuple4<Integer, EObject, EReference, EObject>> second) {
		this.second = second;
	}

	public ConflictKind getSecondKind() {
		return secondKind;
	}

	public void setSecondKind(ConflictKind secondKind) {
		this.secondKind = secondKind;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public String toString() {
		return "RefEdgeConflict "
				+ "\n[first=" + first + ", "
				+ "\n\nfirstKind=" + firstKind + ", "
				+ "\n\nsecond=" + second + ", "
				+ "\n\nsecondKind="+ secondKind + ", "
				+ "\n\ninformation=" + information + "]";
	}
	
}
