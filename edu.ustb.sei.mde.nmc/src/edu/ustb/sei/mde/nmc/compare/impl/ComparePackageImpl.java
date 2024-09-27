package edu.ustb.sei.mde.nmc.compare.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import edu.ustb.sei.mde.nmc.compare.CompareFactory;
import edu.ustb.sei.mde.nmc.compare.ComparePackage;
import edu.ustb.sei.mde.nmc.compare.Comparison;
import edu.ustb.sei.mde.nmc.compare.IEqualityHelper;
import edu.ustb.sei.mde.nmc.compare.Match;
import edu.ustb.sei.mde.nmc.compare.MatchResource;

public class ComparePackageImpl extends EPackageImpl implements ComparePackage {

	private EClass comparisonEClass = null;

	private EClass matchResourceEClass = null;

	private EClass matchEClass = null;

	private EDataType eIterableEDataType = null;

	private EDataType iEqualityHelperEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with by the
	 * package URI value.
	 */
	private ComparePackageImpl() {
		super(eNS_URI, CompareFactory.eINSTANCE);
	}

	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the Package for this model, and for any
	 * others upon which it depends.
	 */
	public static ComparePackage init() {
		if (isInited)
			return (ComparePackage) EPackage.Registry.INSTANCE.getEPackage(ComparePackage.eNS_URI);

		// Obtain or create and register package
		ComparePackageImpl theComparePackage = (ComparePackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof ComparePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new ComparePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theComparePackage.createPackageContents();

		// Initialize created meta-data
		theComparePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theComparePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ComparePackage.eNS_URI, theComparePackage);
		return theComparePackage;
	}

	public EClass getComparison() {
		return comparisonEClass;
	}

	public EReference getComparison_MatchedResources() {
		return (EReference) comparisonEClass.getEStructuralFeatures().get(0);
	}

	public EReference getComparison_Matches() {
		return (EReference) comparisonEClass.getEStructuralFeatures().get(1);
	}

	public EAttribute getComparison_ThreeWay() {
		return (EAttribute) comparisonEClass.getEStructuralFeatures().get(2); // 这里不能写4，会抛出异常OutofBoundsException
	}

	@Override
	public EClass getMatchResource() {
		return matchResourceEClass;
	}

	@Override
	public EAttribute getMatchResource_LeftURI() {
		return (EAttribute) matchResourceEClass.getEStructuralFeatures().get(0);
	}

	@Override
	public EAttribute getMatchResource_RightURI() {
		return (EAttribute) matchResourceEClass.getEStructuralFeatures().get(1);
	}

	@Override
	public EAttribute getMatchResource_OriginURI() {
		return (EAttribute) matchResourceEClass.getEStructuralFeatures().get(2);
	}

	@Override
	public EAttribute getMatchResource_Left() {
		return (EAttribute) matchResourceEClass.getEStructuralFeatures().get(3);
	}

	@Override
	public EAttribute getMatchResource_Right() {
		return (EAttribute) matchResourceEClass.getEStructuralFeatures().get(4);
	}

	@Override
	public EAttribute getMatchResource_Origin() {
		return (EAttribute) matchResourceEClass.getEStructuralFeatures().get(5);
	}

	@Override
	public EReference getMatchResource_Comparison() {
		return (EReference) matchResourceEClass.getEStructuralFeatures().get(6);
	}

	public EClass getMatch() {
		return matchEClass;
	}

	public EReference getMatch_Submatches() {
		return (EReference) matchEClass.getEStructuralFeatures().get(0);
	}

	public EReference getMatch_Left() {
		return (EReference) matchEClass.getEStructuralFeatures().get(1); // 这里不能写2，会抛出异常OutOfBoundsException
	}

	public EReference getMatch_Right() {
		return (EReference) matchEClass.getEStructuralFeatures().get(2);
	}

	public EReference getMatch_Origin() {
		return (EReference) matchEClass.getEStructuralFeatures().get(3);
	}

	public EDataType getEIterable() {
		return eIterableEDataType;
	}

	public EDataType getIEqualityHelper() {
		return iEqualityHelperEDataType;
	}

	@Override
	public CompareFactory getCompareFactory() {
		return (CompareFactory) getEFactoryInstance();
	}

	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to
	 * have no affect on any invocation but its first.
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		comparisonEClass = createEClass(COMPARISON);
		createEReference(comparisonEClass, COMPARISON__MATCHED_RESOURCES);
		createEReference(comparisonEClass, COMPARISON__MATCHES);
		createEAttribute(comparisonEClass, COMPARISON__THREE_WAY);

		matchResourceEClass = createEClass(MATCH_RESOURCE);
		createEAttribute(matchResourceEClass, MATCH_RESOURCE__LEFT_URI);
		createEAttribute(matchResourceEClass, MATCH_RESOURCE__RIGHT_URI);
		createEAttribute(matchResourceEClass, MATCH_RESOURCE__ORIGIN_URI);
		createEAttribute(matchResourceEClass, MATCH_RESOURCE__LEFT);
		createEAttribute(matchResourceEClass, MATCH_RESOURCE__RIGHT);
		createEAttribute(matchResourceEClass, MATCH_RESOURCE__ORIGIN);
		createEReference(matchResourceEClass, MATCH_RESOURCE__COMPARISON);

		matchEClass = createEClass(MATCH);
		createEReference(matchEClass, MATCH__SUBMATCHES);
		createEReference(matchEClass, MATCH__LEFT);
		createEReference(matchEClass, MATCH__RIGHT);
		createEReference(matchEClass, MATCH__ORIGIN);

		// Create data types
		eIterableEDataType = createEDataType(EITERABLE);
		iEqualityHelperEDataType = createEDataType(IEQUALITY_HELPER);
	}

	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is
	 * guarded to have no affect on any invocation but its first.
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters
		addETypeParameter(eIterableEDataType, "T");

		// Initialize classes and features; add operations and parameters
		initEClass(comparisonEClass, Comparison.class, "Comparison", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComparison_MatchedResources(), this.getMatchResource(), this.getMatchResource_Comparison(),
				"matchedResources", null, 0, -1, Comparison.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComparison_Matches(), this.getMatch(), null, "matches", null, 0, -1, Comparison.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComparison_ThreeWay(), ecorePackage.getEBoolean(), "threeWay", null, 0, 1, Comparison.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(comparisonEClass, this.getMatch(), "getMatch", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, theEcorePackage.getEObject(), "element", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(comparisonEClass, this.getIEqualityHelper(), "getEqualityHelper", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(matchResourceEClass, MatchResource.class, "MatchResource", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMatchResource_LeftURI(), ecorePackage.getEString(), "leftURI", null, 1, 1, //$NON-NLS-1$
				MatchResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatchResource_RightURI(), ecorePackage.getEString(), "rightURI", null, 1, 1, //$NON-NLS-1$
				MatchResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatchResource_OriginURI(), ecorePackage.getEString(), "originURI", null, 0, 1, //$NON-NLS-1$
				MatchResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatchResource_Left(), theEcorePackage.getEResource(), "left", null, 0, 1, MatchResource.class, //$NON-NLS-1$
				IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatchResource_Right(), theEcorePackage.getEResource(), "right", null, 0, 1, //$NON-NLS-1$
				MatchResource.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatchResource_Origin(), theEcorePackage.getEResource(), "origin", null, 0, 1, //$NON-NLS-1$
				MatchResource.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getMatchResource_Comparison(), this.getComparison(), this.getComparison_MatchedResources(),
				"comparison", null, 0, 1, MatchResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
				!IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(matchEClass, Match.class, "Match", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMatch_Submatches(), this.getMatch(), null, "submatches", null, 0, -1, Match.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMatch_Left(), ecorePackage.getEObject(), null, "left", null, 0, 1, Match.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getMatch_Right(), ecorePackage.getEObject(), null, "right", null, 0, 1, Match.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMatch_Origin(), ecorePackage.getEObject(), null, "origin", null, 0, 1, Match.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(matchEClass, this.getComparison(), "getComparison", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(matchEClass, null, "getAllSubmatches", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		EGenericType g1 = createEGenericType(this.getEIterable());
		EGenericType g2 = createEGenericType(this.getMatch());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		// Initialize data types
		initEDataType(eIterableEDataType, Iterable.class, "EIterable", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(iEqualityHelperEDataType, IEqualityHelper.class, "IEqualityHelper", !IS_SERIALIZABLE, //$NON-NLS-1$
				!IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

	}

}
