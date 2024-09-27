/**
 */
package edu.ustb.sei.mde.conflict.impl;

import edu.ustb.sei.mde.conflict.Conflict;
import edu.ustb.sei.mde.conflict.ConflictPackage;
import edu.ustb.sei.mde.conflict.Tuple;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conflict</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link edu.ustb.sei.mde.conflict.impl.ConflictImpl#getTuples <em>Tuples</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.impl.ConflictImpl#getInformation <em>Information</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConflictImpl extends MinimalEObjectImpl.Container implements Conflict {
	/**
	 * The cached value of the '{@link #getTuples() <em>Tuples</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTuples()
	 * @generated
	 * @ordered
	 */
	protected EList<Tuple> tuples;

	/**
	 * The default value of the '{@link #getInformation() <em>Information</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInformation()
	 * @generated
	 * @ordered
	 */
	protected static final String INFORMATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInformation() <em>Information</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInformation()
	 * @generated
	 * @ordered
	 */
	protected String information = INFORMATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConflictImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConflictPackage.Literals.CONFLICT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Tuple> getTuples() {
		if (tuples == null) {
			tuples = new EObjectContainmentEList<Tuple>(Tuple.class, this, ConflictPackage.CONFLICT__TUPLES);
		}
		return tuples;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInformation() {
		return information;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInformation(String newInformation) {
		String oldInformation = information;
		information = newInformation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConflictPackage.CONFLICT__INFORMATION, oldInformation, information));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConflictPackage.CONFLICT__TUPLES:
				return ((InternalEList<?>)getTuples()).basicRemove(otherEnd, msgs);
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
			case ConflictPackage.CONFLICT__TUPLES:
				return getTuples();
			case ConflictPackage.CONFLICT__INFORMATION:
				return getInformation();
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
			case ConflictPackage.CONFLICT__TUPLES:
				getTuples().clear();
				getTuples().addAll((Collection<? extends Tuple>)newValue);
				return;
			case ConflictPackage.CONFLICT__INFORMATION:
				setInformation((String)newValue);
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
			case ConflictPackage.CONFLICT__TUPLES:
				getTuples().clear();
				return;
			case ConflictPackage.CONFLICT__INFORMATION:
				setInformation(INFORMATION_EDEFAULT);
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
			case ConflictPackage.CONFLICT__TUPLES:
				return tuples != null && !tuples.isEmpty();
			case ConflictPackage.CONFLICT__INFORMATION:
				return INFORMATION_EDEFAULT == null ? information != null : !INFORMATION_EDEFAULT.equals(information);
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
		result.append(" (information: ");
		result.append(information);
		result.append(')');
		return result.toString();
	}

} //ConflictImpl
