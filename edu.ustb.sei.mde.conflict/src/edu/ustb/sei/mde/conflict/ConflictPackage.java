/**
 */
package edu.ustb.sei.mde.conflict;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see edu.ustb.sei.mde.conflict.ConflictFactory
 * @model kind="package"
 * @generated
 */
public interface ConflictPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "conflict";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.ustb.edu.cn/sei/mde/nmc/conflict";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "conflict";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConflictPackage eINSTANCE = edu.ustb.sei.mde.conflict.impl.ConflictPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.ustb.sei.mde.conflict.impl.ConflictsImpl <em>Conflicts</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.ustb.sei.mde.conflict.impl.ConflictsImpl
	 * @see edu.ustb.sei.mde.conflict.impl.ConflictPackageImpl#getConflicts()
	 * @generated
	 */
	int CONFLICTS = 0;

	/**
	 * The feature id for the '<em><b>Conflicts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTS__CONFLICTS = 0;

	/**
	 * The feature id for the '<em><b>Base URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTS__BASE_URI = 1;

	/**
	 * The feature id for the '<em><b>Branch UR Is</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTS__BRANCH_UR_IS = 2;

	/**
	 * The number of structural features of the '<em>Conflicts</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Conflicts</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICTS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.ustb.sei.mde.conflict.impl.ConflictImpl <em>Conflict</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.ustb.sei.mde.conflict.impl.ConflictImpl
	 * @see edu.ustb.sei.mde.conflict.impl.ConflictPackageImpl#getConflict()
	 * @generated
	 */
	int CONFLICT = 1;

	/**
	 * The feature id for the '<em><b>Tuples</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICT__TUPLES = 0;

	/**
	 * The feature id for the '<em><b>Information</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICT__INFORMATION = 1;

	/**
	 * The number of structural features of the '<em>Conflict</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Conflict</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFLICT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.ustb.sei.mde.conflict.impl.TupleImpl <em>Tuple</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.ustb.sei.mde.conflict.impl.TupleImpl
	 * @see edu.ustb.sei.mde.conflict.impl.ConflictPackageImpl#getTuple()
	 * @generated
	 */
	int TUPLE = 2;

	/**
	 * The feature id for the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE__BRANCH = 0;

	/**
	 * The feature id for the '<em><b>EObject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE__EOBJECT = 1;

	/**
	 * The feature id for the '<em><b>EStructural Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE__ESTRUCTURAL_FEATURE = 2;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE__POSITION = 3;

	/**
	 * The number of structural features of the '<em>Tuple</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Tuple</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link edu.ustb.sei.mde.conflict.Conflicts <em>Conflicts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conflicts</em>'.
	 * @see edu.ustb.sei.mde.conflict.Conflicts
	 * @generated
	 */
	EClass getConflicts();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.ustb.sei.mde.conflict.Conflicts#getConflicts <em>Conflicts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conflicts</em>'.
	 * @see edu.ustb.sei.mde.conflict.Conflicts#getConflicts()
	 * @see #getConflicts()
	 * @generated
	 */
	EReference getConflicts_Conflicts();

	/**
	 * Returns the meta object for the attribute '{@link edu.ustb.sei.mde.conflict.Conflicts#getBaseURI <em>Base URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base URI</em>'.
	 * @see edu.ustb.sei.mde.conflict.Conflicts#getBaseURI()
	 * @see #getConflicts()
	 * @generated
	 */
	EAttribute getConflicts_BaseURI();

	/**
	 * Returns the meta object for the attribute list '{@link edu.ustb.sei.mde.conflict.Conflicts#getBranchURIs <em>Branch UR Is</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Branch UR Is</em>'.
	 * @see edu.ustb.sei.mde.conflict.Conflicts#getBranchURIs()
	 * @see #getConflicts()
	 * @generated
	 */
	EAttribute getConflicts_BranchURIs();

	/**
	 * Returns the meta object for class '{@link edu.ustb.sei.mde.conflict.Conflict <em>Conflict</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conflict</em>'.
	 * @see edu.ustb.sei.mde.conflict.Conflict
	 * @generated
	 */
	EClass getConflict();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.ustb.sei.mde.conflict.Conflict#getTuples <em>Tuples</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tuples</em>'.
	 * @see edu.ustb.sei.mde.conflict.Conflict#getTuples()
	 * @see #getConflict()
	 * @generated
	 */
	EReference getConflict_Tuples();

	/**
	 * Returns the meta object for the attribute '{@link edu.ustb.sei.mde.conflict.Conflict#getInformation <em>Information</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Information</em>'.
	 * @see edu.ustb.sei.mde.conflict.Conflict#getInformation()
	 * @see #getConflict()
	 * @generated
	 */
	EAttribute getConflict_Information();

	/**
	 * Returns the meta object for class '{@link edu.ustb.sei.mde.conflict.Tuple <em>Tuple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple</em>'.
	 * @see edu.ustb.sei.mde.conflict.Tuple
	 * @generated
	 */
	EClass getTuple();

	/**
	 * Returns the meta object for the attribute '{@link edu.ustb.sei.mde.conflict.Tuple#getBranch <em>Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Branch</em>'.
	 * @see edu.ustb.sei.mde.conflict.Tuple#getBranch()
	 * @see #getTuple()
	 * @generated
	 */
	EAttribute getTuple_Branch();

	/**
	 * Returns the meta object for the reference '{@link edu.ustb.sei.mde.conflict.Tuple#getEObject <em>EObject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EObject</em>'.
	 * @see edu.ustb.sei.mde.conflict.Tuple#getEObject()
	 * @see #getTuple()
	 * @generated
	 */
	EReference getTuple_EObject();

	/**
	 * Returns the meta object for the reference '{@link edu.ustb.sei.mde.conflict.Tuple#getEStructuralFeature <em>EStructural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EStructural Feature</em>'.
	 * @see edu.ustb.sei.mde.conflict.Tuple#getEStructuralFeature()
	 * @see #getTuple()
	 * @generated
	 */
	EReference getTuple_EStructuralFeature();

	/**
	 * Returns the meta object for the attribute list '{@link edu.ustb.sei.mde.conflict.Tuple#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Position</em>'.
	 * @see edu.ustb.sei.mde.conflict.Tuple#getPosition()
	 * @see #getTuple()
	 * @generated
	 */
	EAttribute getTuple_Position();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConflictFactory getConflictFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link edu.ustb.sei.mde.conflict.impl.ConflictsImpl <em>Conflicts</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.ustb.sei.mde.conflict.impl.ConflictsImpl
		 * @see edu.ustb.sei.mde.conflict.impl.ConflictPackageImpl#getConflicts()
		 * @generated
		 */
		EClass CONFLICTS = eINSTANCE.getConflicts();

		/**
		 * The meta object literal for the '<em><b>Conflicts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFLICTS__CONFLICTS = eINSTANCE.getConflicts_Conflicts();

		/**
		 * The meta object literal for the '<em><b>Base URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFLICTS__BASE_URI = eINSTANCE.getConflicts_BaseURI();

		/**
		 * The meta object literal for the '<em><b>Branch UR Is</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFLICTS__BRANCH_UR_IS = eINSTANCE.getConflicts_BranchURIs();

		/**
		 * The meta object literal for the '{@link edu.ustb.sei.mde.conflict.impl.ConflictImpl <em>Conflict</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.ustb.sei.mde.conflict.impl.ConflictImpl
		 * @see edu.ustb.sei.mde.conflict.impl.ConflictPackageImpl#getConflict()
		 * @generated
		 */
		EClass CONFLICT = eINSTANCE.getConflict();

		/**
		 * The meta object literal for the '<em><b>Tuples</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFLICT__TUPLES = eINSTANCE.getConflict_Tuples();

		/**
		 * The meta object literal for the '<em><b>Information</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFLICT__INFORMATION = eINSTANCE.getConflict_Information();

		/**
		 * The meta object literal for the '{@link edu.ustb.sei.mde.conflict.impl.TupleImpl <em>Tuple</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.ustb.sei.mde.conflict.impl.TupleImpl
		 * @see edu.ustb.sei.mde.conflict.impl.ConflictPackageImpl#getTuple()
		 * @generated
		 */
		EClass TUPLE = eINSTANCE.getTuple();

		/**
		 * The meta object literal for the '<em><b>Branch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUPLE__BRANCH = eINSTANCE.getTuple_Branch();

		/**
		 * The meta object literal for the '<em><b>EObject</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUPLE__EOBJECT = eINSTANCE.getTuple_EObject();

		/**
		 * The meta object literal for the '<em><b>EStructural Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUPLE__ESTRUCTURAL_FEATURE = eINSTANCE.getTuple_EStructuralFeature();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUPLE__POSITION = eINSTANCE.getTuple_Position();

	}

} //ConflictPackage
