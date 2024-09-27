/**
 */
package edu.ustb.sei.mde.conflict;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link edu.ustb.sei.mde.conflict.Tuple#getBranch <em>Branch</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.Tuple#getEObject <em>EObject</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.Tuple#getEStructuralFeature <em>EStructural Feature</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.Tuple#getPosition <em>Position</em>}</li>
 * </ul>
 *
 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getTuple()
 * @model
 * @generated
 */
public interface Tuple extends EObject {
	/**
	 * Returns the value of the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch</em>' attribute.
	 * @see #setBranch(int)
	 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getTuple_Branch()
	 * @model
	 * @generated
	 */
	int getBranch();

	/**
	 * Sets the value of the '{@link edu.ustb.sei.mde.conflict.Tuple#getBranch <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branch</em>' attribute.
	 * @see #getBranch()
	 * @generated
	 */
	void setBranch(int value);

	/**
	 * Returns the value of the '<em><b>EObject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EObject</em>' reference.
	 * @see #setEObject(EObject)
	 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getTuple_EObject()
	 * @model
	 * @generated
	 */
	EObject getEObject();

	/**
	 * Sets the value of the '{@link edu.ustb.sei.mde.conflict.Tuple#getEObject <em>EObject</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EObject</em>' reference.
	 * @see #getEObject()
	 * @generated
	 */
	void setEObject(EObject value);

	/**
	 * Returns the value of the '<em><b>EStructural Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EStructural Feature</em>' reference.
	 * @see #setEStructuralFeature(EStructuralFeature)
	 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getTuple_EStructuralFeature()
	 * @model
	 * @generated
	 */
	EStructuralFeature getEStructuralFeature();

	/**
	 * Sets the value of the '{@link edu.ustb.sei.mde.conflict.Tuple#getEStructuralFeature <em>EStructural Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EStructural Feature</em>' reference.
	 * @see #getEStructuralFeature()
	 * @generated
	 */
	void setEStructuralFeature(EStructuralFeature value);

	/**
	 * Returns the value of the '<em><b>Position</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' attribute list.
	 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getTuple_Position()
	 * @model
	 * @generated
	 */
	EList<Integer> getPosition();

} // Tuple
