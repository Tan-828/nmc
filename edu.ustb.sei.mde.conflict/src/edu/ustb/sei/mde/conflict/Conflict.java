/**
 */
package edu.ustb.sei.mde.conflict;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conflict</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link edu.ustb.sei.mde.conflict.Conflict#getTuples <em>Tuples</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.Conflict#getInformation <em>Information</em>}</li>
 * </ul>
 *
 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getConflict()
 * @model
 * @generated
 */
public interface Conflict extends EObject {
	/**
	 * Returns the value of the '<em><b>Tuples</b></em>' containment reference list.
	 * The list contents are of type {@link edu.ustb.sei.mde.conflict.Tuple}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuples</em>' containment reference list.
	 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getConflict_Tuples()
	 * @model containment="true"
	 * @generated
	 */
	EList<Tuple> getTuples();

	/**
	 * Returns the value of the '<em><b>Information</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Information</em>' attribute.
	 * @see #setInformation(String)
	 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getConflict_Information()
	 * @model
	 * @generated
	 */
	String getInformation();

	/**
	 * Sets the value of the '{@link edu.ustb.sei.mde.conflict.Conflict#getInformation <em>Information</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Information</em>' attribute.
	 * @see #getInformation()
	 * @generated
	 */
	void setInformation(String value);

} // Conflict
