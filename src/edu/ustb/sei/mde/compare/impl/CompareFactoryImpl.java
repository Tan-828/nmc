package edu.ustb.sei.mde.compare.impl;

import java.lang.Iterable;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import edu.ustb.sei.mde.compare.CompareFactory;
import edu.ustb.sei.mde.compare.ComparePackage;
import edu.ustb.sei.mde.compare.Comparison;
import edu.ustb.sei.mde.compare.Match;
import edu.ustb.sei.mde.compare.MatchResource;
import edu.ustb.sei.mde.compare.internal.ComparisonSpec;
import edu.ustb.sei.mde.compare.internal.MatchSpec;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
@SuppressWarnings("unused")
// generated code, removing warnings
public class CompareFactoryImpl extends EFactoryImpl implements CompareFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static CompareFactory init() {
		try {
			CompareFactory theCompareFactory = (CompareFactory)EPackage.Registry.INSTANCE
					.getEFactory(ComparePackage.eNS_URI);
			if (theCompareFactory != null) {
				return theCompareFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CompareFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CompareFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Comparison createComparison() {
		return new ComparisonSpec();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public MatchResource createMatchResource() {
		MatchResourceImpl matchResource = new MatchResourceImpl();
		return matchResource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Match createMatch() {
		return new MatchSpec();
	}

} // CompareFactoryImpl

