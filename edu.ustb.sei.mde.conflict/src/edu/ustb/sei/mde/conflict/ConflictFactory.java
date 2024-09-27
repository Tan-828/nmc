/**
 */
package edu.ustb.sei.mde.conflict;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see edu.ustb.sei.mde.conflict.ConflictPackage
 * @generated
 */
public interface ConflictFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConflictFactory eINSTANCE = edu.ustb.sei.mde.conflict.impl.ConflictFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Conflicts</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conflicts</em>'.
	 * @generated
	 */
	Conflicts createConflicts();

	/**
	 * Returns a new object of class '<em>Conflict</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conflict</em>'.
	 * @generated
	 */
	Conflict createConflict();

	/**
	 * Returns a new object of class '<em>Tuple</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tuple</em>'.
	 * @generated
	 */
	Tuple createTuple();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ConflictPackage getConflictPackage();

} //ConflictFactory
