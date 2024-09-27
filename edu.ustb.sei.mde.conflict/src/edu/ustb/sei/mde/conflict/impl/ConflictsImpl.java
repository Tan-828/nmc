/**
 */
package edu.ustb.sei.mde.conflict.impl;

import edu.ustb.sei.mde.conflict.Conflict;
import edu.ustb.sei.mde.conflict.ConflictPackage;
import edu.ustb.sei.mde.conflict.Conflicts;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conflicts</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link edu.ustb.sei.mde.conflict.impl.ConflictsImpl#getConflicts <em>Conflicts</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.impl.ConflictsImpl#getBaseURI <em>Base URI</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.impl.ConflictsImpl#getBranchURIs <em>Branch UR Is</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConflictsImpl extends MinimalEObjectImpl.Container implements Conflicts {
	/**
	 * The cached value of the '{@link #getConflicts() <em>Conflicts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConflicts()
	 * @generated
	 * @ordered
	 */
	protected EList<Conflict> conflicts;

	/**
	 * The default value of the '{@link #getBaseURI() <em>Base URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseURI()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBaseURI() <em>Base URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseURI()
	 * @generated
	 * @ordered
	 */
	protected String baseURI = BASE_URI_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBranchURIs() <em>Branch UR Is</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranchURIs()
	 * @generated
	 * @ordered
	 */
	protected EList<String> branchURIs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConflictsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConflictPackage.Literals.CONFLICTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Conflict> getConflicts() {
		if (conflicts == null) {
			conflicts = new EObjectContainmentEList<Conflict>(Conflict.class, this, ConflictPackage.CONFLICTS__CONFLICTS);
		}
		return conflicts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBaseURI() {
		return baseURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseURI(String newBaseURI) {
		String oldBaseURI = baseURI;
		baseURI = newBaseURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConflictPackage.CONFLICTS__BASE_URI, oldBaseURI, baseURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getBranchURIs() {
		if (branchURIs == null) {
			branchURIs = new EDataTypeUniqueEList<String>(String.class, this, ConflictPackage.CONFLICTS__BRANCH_UR_IS);
		}
		return branchURIs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConflictPackage.CONFLICTS__CONFLICTS:
				return ((InternalEList<?>)getConflicts()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConflictPackage.CONFLICTS__CONFLICTS:
				return getConflicts();
			case ConflictPackage.CONFLICTS__BASE_URI:
				return getBaseURI();
			case ConflictPackage.CONFLICTS__BRANCH_UR_IS:
				return getBranchURIs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConflictPackage.CONFLICTS__CONFLICTS:
				getConflicts().clear();
				getConflicts().addAll((Collection<? extends Conflict>)newValue);
				return;
			case ConflictPackage.CONFLICTS__BASE_URI:
				setBaseURI((String)newValue);
				return;
			case ConflictPackage.CONFLICTS__BRANCH_UR_IS:
				getBranchURIs().clear();
				getBranchURIs().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ConflictPackage.CONFLICTS__CONFLICTS:
				getConflicts().clear();
				return;
			case ConflictPackage.CONFLICTS__BASE_URI:
				setBaseURI(BASE_URI_EDEFAULT);
				return;
			case ConflictPackage.CONFLICTS__BRANCH_UR_IS:
				getBranchURIs().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ConflictPackage.CONFLICTS__CONFLICTS:
				return conflicts != null && !conflicts.isEmpty();
			case ConflictPackage.CONFLICTS__BASE_URI:
				return BASE_URI_EDEFAULT == null ? baseURI != null : !BASE_URI_EDEFAULT.equals(baseURI);
			case ConflictPackage.CONFLICTS__BRANCH_UR_IS:
				return branchURIs != null && !branchURIs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (baseURI: ");
		result.append(baseURI);
		result.append(", branchURIs: ");
		result.append(branchURIs);
		result.append(')');
		return result.toString();
	}

} //ConflictsImpl
