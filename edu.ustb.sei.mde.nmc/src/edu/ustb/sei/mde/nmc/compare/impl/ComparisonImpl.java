package edu.ustb.sei.mde.nmc.compare.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import edu.ustb.sei.mde.nmc.compare.ComparePackage;
import edu.ustb.sei.mde.nmc.compare.Comparison;
import edu.ustb.sei.mde.nmc.compare.IEqualityHelper;
import edu.ustb.sei.mde.nmc.compare.Match;
import edu.ustb.sei.mde.nmc.compare.MatchResource;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Comparison</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.emf.compare.impl.ComparisonImpl#getMatchedResources
 * <em>Matched Resources</em>}</li>
 * <li>{@link org.eclipse.emf.compare.impl.ComparisonImpl#getMatches
 * <em>Matches</em>}</li>
 * <li>{@link org.eclipse.emf.compare.impl.ComparisonImpl#getConflicts
 * <em>Conflicts</em>}</li>
 * <li>{@link org.eclipse.emf.compare.impl.ComparisonImpl#getEquivalences
 * <em>Equivalences</em>}</li>
 * <li>{@link org.eclipse.emf.compare.impl.ComparisonImpl#isThreeWay <em>Three
 * Way</em>}</li>
 * <li>{@link org.eclipse.emf.compare.impl.ComparisonImpl#getDiagnostic
 * <em>Diagnostic</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
@SuppressWarnings("boxing")
public class ComparisonImpl extends MinimalEObjectImpl.Container implements Comparison {
	/**
	 * The cached value of the '{@link #getMatchedResources() <em>Matched
	 * Resources</em>}' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMatchedResources()
	 * @generated
	 * @ordered
	 */
	protected EList<MatchResource> matchedResources;

	/**
	 * The cached value of the '{@link #getMatches() <em>Matches</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMatches()
	 * @generated
	 * @ordered
	 */
	protected EList<Match> matches;

	/**
	 * The default value of the '{@link #isThreeWay() <em>Three Way</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isThreeWay()
	 * @generated
	 * @ordered
	 */
	protected static final boolean THREE_WAY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isThreeWay() <em>Three Way</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isThreeWay()
	 * @generated
	 * @ordered
	 */
	protected boolean threeWay = THREE_WAY_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComparisonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<MatchResource> getMatchedResources() {
		if (matchedResources == null) {
			matchedResources = new EObjectContainmentWithInverseEList<MatchResource>(MatchResource.class, this,
					ComparePackage.COMPARISON__MATCHED_RESOURCES, ComparePackage.MATCH_RESOURCE__COMPARISON);
		}
		return matchedResources;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Match> getMatches() {
		if (matches == null) {
			matches = new EObjectContainmentEList<Match>(Match.class, this, ComparePackage.COMPARISON__MATCHES);
		}
		return matches;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Match getMatch(EObject element) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IEqualityHelper getEqualityHelper() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isThreeWay() {
		return threeWay;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setThreeWay(boolean newThreeWay) {
		boolean oldThreeWay = threeWay;
		threeWay = newThreeWay;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ComparePackage.COMPARISON__THREE_WAY, oldThreeWay,
					threeWay));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComparePackage.Literals.COMPARISON;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
//		switch (featureID) {
//		case ComparePackage.COMPARISON__MATCHED_RESOURCES:
//			return ((InternalEList<InternalEObject>) (InternalEList<?>) getMatchedResources()).basicAdd(otherEnd, msgs);
//		}
//		return super.eInverseAdd(otherEnd, featureID, msgs);
//	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ComparePackage.COMPARISON__MATCHED_RESOURCES:
			return ((InternalEList<?>) getMatchedResources()).basicRemove(otherEnd, msgs);
		case ComparePackage.COMPARISON__MATCHES:
			return ((InternalEList<?>) getMatches()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ComparePackage.COMPARISON__MATCHED_RESOURCES:
			return matchedResources != null && !matchedResources.isEmpty();
		case ComparePackage.COMPARISON__MATCHES:
			return matches != null && !matches.isEmpty();
		case ComparePackage.COMPARISON__THREE_WAY:
			return threeWay != THREE_WAY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ComparePackage.COMPARISON__MATCHED_RESOURCES:
			return getMatchedResources();
		case ComparePackage.COMPARISON__MATCHES:
			return getMatches();
		case ComparePackage.COMPARISON__THREE_WAY:
			return isThreeWay();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ComparePackage.COMPARISON__MATCHED_RESOURCES:
			getMatchedResources().clear();
			getMatchedResources().addAll((Collection<? extends MatchResource>) newValue);
			return;
		case ComparePackage.COMPARISON__MATCHES:
			getMatches().clear();
			getMatches().addAll((Collection<? extends Match>) newValue);
			return;
		case ComparePackage.COMPARISON__THREE_WAY:
			setThreeWay((Boolean) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
//	@Override
//	public void eUnset(int featureID) {
//		switch (featureID) {
//		case ComparePackage.COMPARISON__MATCHED_RESOURCES:
//			getMatchedResources().clear();
//			return;
//		case ComparePackage.COMPARISON__MATCHES:
//			getMatches().clear();
//			return;
//		case ComparePackage.COMPARISON__THREE_WAY:
//			setThreeWay(THREE_WAY_EDEFAULT);
//			return;
//		}
//		super.eUnset(featureID);
//	}

} // ComparisonImpl
