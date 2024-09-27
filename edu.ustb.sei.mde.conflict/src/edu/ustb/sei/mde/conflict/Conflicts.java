/**
 */
package edu.ustb.sei.mde.conflict;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conflicts</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link edu.ustb.sei.mde.conflict.Conflicts#getConflicts <em>Conflicts</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.Conflicts#getBaseURI <em>Base URI</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.Conflicts#getBranchURIs <em>Branch UR Is</em>}</li>
 * </ul>
 *
 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getConflicts()
 * @model
 * @generated
 */
public interface Conflicts extends EObject {
	/**
	 * Returns the value of the '<em><b>Conflicts</b></em>' containment reference list.
	 * The list contents are of type {@link edu.ustb.sei.mde.conflict.Conflict}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conflicts</em>' containment reference list.
	 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getConflicts_Conflicts()
	 * @model containment="true"
	 * @generated
	 */
	EList<Conflict> getConflicts();

	/**
	 * Returns the value of the '<em><b>Base URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base URI</em>' attribute.
	 * @see #setBaseURI(String)
	 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getConflicts_BaseURI()
	 * @model
	 * @generated
	 */
	String getBaseURI();

	/**
	 * Sets the value of the '{@link edu.ustb.sei.mde.conflict.Conflicts#getBaseURI <em>Base URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base URI</em>' attribute.
	 * @see #getBaseURI()
	 * @generated
	 */
	void setBaseURI(String value);

	/**
	 * Returns the value of the '<em><b>Branch UR Is</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch UR Is</em>' attribute list.
	 * @see edu.ustb.sei.mde.conflict.ConflictPackage#getConflicts_BranchURIs()
	 * @model
	 * @generated
	 */
	EList<String> getBranchURIs();

} // Conflicts
