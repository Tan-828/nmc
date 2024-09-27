/**
 */
package edu.ustb.sei.mde.conflict.impl;

import edu.ustb.sei.mde.conflict.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConflictFactoryImpl extends EFactoryImpl implements ConflictFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ConflictFactory init() {
		try {
			ConflictFactory theConflictFactory = (ConflictFactory)EPackage.Registry.INSTANCE.getEFactory(ConflictPackage.eNS_URI);
			if (theConflictFactory != null) {
				return theConflictFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ConflictFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConflictFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ConflictPackage.CONFLICTS: return createConflicts();
			case ConflictPackage.CONFLICT: return createConflict();
			case ConflictPackage.TUPLE: return createTuple();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Conflicts createConflicts() {
		ConflictsImpl conflicts = new ConflictsImpl();
		return conflicts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Conflict createConflict() {
		ConflictImpl conflict = new ConflictImpl();
		return conflict;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tuple createTuple() {
		TupleImpl tuple = new TupleImpl();
		return tuple;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConflictPackage getConflictPackage() {
		return (ConflictPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ConflictPackage getPackage() {
		return ConflictPackage.eINSTANCE;
	}

} //ConflictFactoryImpl
