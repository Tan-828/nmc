/**
 */
package edu.ustb.sei.mde.conflict.impl;

import edu.ustb.sei.mde.conflict.Conflict;
import edu.ustb.sei.mde.conflict.ConflictFactory;
import edu.ustb.sei.mde.conflict.ConflictPackage;
import edu.ustb.sei.mde.conflict.Conflicts;
import edu.ustb.sei.mde.conflict.Tuple;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConflictPackageImpl extends EPackageImpl implements ConflictPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conflictsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conflictEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see edu.ustb.sei.mde.conflict.ConflictPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ConflictPackageImpl() {
		super(eNS_URI, ConflictFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link ConflictPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ConflictPackage init() {
		if (isInited) return (ConflictPackage)EPackage.Registry.INSTANCE.getEPackage(ConflictPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredConflictPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ConflictPackageImpl theConflictPackage = registeredConflictPackage instanceof ConflictPackageImpl ? (ConflictPackageImpl)registeredConflictPackage : new ConflictPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theConflictPackage.createPackageContents();

		// Initialize created meta-data
		theConflictPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theConflictPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ConflictPackage.eNS_URI, theConflictPackage);
		return theConflictPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConflicts() {
		return conflictsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConflicts_Conflicts() {
		return (EReference)conflictsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConflicts_BaseURI() {
		return (EAttribute)conflictsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConflicts_BranchURIs() {
		return (EAttribute)conflictsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConflict() {
		return conflictEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConflict_Tuples() {
		return (EReference)conflictEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConflict_Information() {
		return (EAttribute)conflictEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTuple() {
		return tupleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTuple_Branch() {
		return (EAttribute)tupleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTuple_EObject() {
		return (EReference)tupleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTuple_EStructuralFeature() {
		return (EReference)tupleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTuple_Position() {
		return (EAttribute)tupleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConflictFactory getConflictFactory() {
		return (ConflictFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		conflictsEClass = createEClass(CONFLICTS);
		createEReference(conflictsEClass, CONFLICTS__CONFLICTS);
		createEAttribute(conflictsEClass, CONFLICTS__BASE_URI);
		createEAttribute(conflictsEClass, CONFLICTS__BRANCH_UR_IS);

		conflictEClass = createEClass(CONFLICT);
		createEReference(conflictEClass, CONFLICT__TUPLES);
		createEAttribute(conflictEClass, CONFLICT__INFORMATION);

		tupleEClass = createEClass(TUPLE);
		createEAttribute(tupleEClass, TUPLE__BRANCH);
		createEReference(tupleEClass, TUPLE__EOBJECT);
		createEReference(tupleEClass, TUPLE__ESTRUCTURAL_FEATURE);
		createEAttribute(tupleEClass, TUPLE__POSITION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(conflictsEClass, Conflicts.class, "Conflicts", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConflicts_Conflicts(), this.getConflict(), null, "conflicts", null, 0, -1, Conflicts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConflicts_BaseURI(), ecorePackage.getEString(), "baseURI", null, 0, 1, Conflicts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConflicts_BranchURIs(), ecorePackage.getEString(), "branchURIs", null, 0, -1, Conflicts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conflictEClass, Conflict.class, "Conflict", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConflict_Tuples(), this.getTuple(), null, "tuples", null, 0, -1, Conflict.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConflict_Information(), ecorePackage.getEString(), "information", null, 0, 1, Conflict.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tupleEClass, Tuple.class, "Tuple", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTuple_Branch(), ecorePackage.getEInt(), "branch", null, 0, 1, Tuple.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTuple_EObject(), ecorePackage.getEObject(), null, "eObject", null, 0, 1, Tuple.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTuple_EStructuralFeature(), ecorePackage.getEStructuralFeature(), null, "eStructuralFeature", null, 0, 1, Tuple.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTuple_Position(), ecorePackage.getEInt(), "position", null, 0, -1, Tuple.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ConflictPackageImpl
