/**
 */
package edu.ustb.sei.mde.conflict.impl;

import edu.ustb.sei.mde.conflict.ConflictPackage;
import edu.ustb.sei.mde.conflict.Tuple;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuple</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link edu.ustb.sei.mde.conflict.impl.TupleImpl#getBranch <em>Branch</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.impl.TupleImpl#getEObject <em>EObject</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.impl.TupleImpl#getEStructuralFeature <em>EStructural Feature</em>}</li>
 *   <li>{@link edu.ustb.sei.mde.conflict.impl.TupleImpl#getPosition <em>Position</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TupleImpl extends MinimalEObjectImpl.Container implements Tuple {
	/**
	 * The default value of the '{@link #getBranch() <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranch()
	 * @generated
	 * @ordered
	 */
	protected static final int BRANCH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getBranch() <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranch()
	 * @generated
	 * @ordered
	 */
	protected int branch = BRANCH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEObject() <em>EObject</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEObject()
	 * @generated
	 * @ordered
	 */
	protected EObject eObject;

	/**
	 * The cached value of the '{@link #getEStructuralFeature() <em>EStructural Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEStructuralFeature()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature eStructuralFeature;

	/**
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> position;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TupleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConflictPackage.Literals.TUPLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getBranch() {
		return branch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBranch(int newBranch) {
		int oldBranch = branch;
		branch = newBranch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConflictPackage.TUPLE__BRANCH, oldBranch, branch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getEObject() {
		if (eObject != null && eObject.eIsProxy()) {
			InternalEObject oldEObject = (InternalEObject)eObject;
			eObject = eResolveProxy(oldEObject);
			if (eObject != oldEObject) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConflictPackage.TUPLE__EOBJECT, oldEObject, eObject));
			}
		}
		return eObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetEObject() {
		return eObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEObject(EObject newEObject) {
		EObject oldEObject = eObject;
		eObject = newEObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConflictPackage.TUPLE__EOBJECT, oldEObject, eObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature getEStructuralFeature() {
		if (eStructuralFeature != null && eStructuralFeature.eIsProxy()) {
			InternalEObject oldEStructuralFeature = (InternalEObject)eStructuralFeature;
			eStructuralFeature = (EStructuralFeature)eResolveProxy(oldEStructuralFeature);
			if (eStructuralFeature != oldEStructuralFeature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConflictPackage.TUPLE__ESTRUCTURAL_FEATURE, oldEStructuralFeature, eStructuralFeature));
			}
		}
		return eStructuralFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature basicGetEStructuralFeature() {
		return eStructuralFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEStructuralFeature(EStructuralFeature newEStructuralFeature) {
		EStructuralFeature oldEStructuralFeature = eStructuralFeature;
		eStructuralFeature = newEStructuralFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConflictPackage.TUPLE__ESTRUCTURAL_FEATURE, oldEStructuralFeature, eStructuralFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getPosition() {
		if (position == null) {
			position = new EDataTypeUniqueEList<Integer>(Integer.class, this, ConflictPackage.TUPLE__POSITION);
		}
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConflictPackage.TUPLE__BRANCH:
				return getBranch();
			case ConflictPackage.TUPLE__EOBJECT:
				if (resolve) return getEObject();
				return basicGetEObject();
			case ConflictPackage.TUPLE__ESTRUCTURAL_FEATURE:
				if (resolve) return getEStructuralFeature();
				return basicGetEStructuralFeature();
			case ConflictPackage.TUPLE__POSITION:
				return getPosition();
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
			case ConflictPackage.TUPLE__BRANCH:
				setBranch((Integer)newValue);
				return;
			case ConflictPackage.TUPLE__EOBJECT:
				setEObject((EObject)newValue);
				return;
			case ConflictPackage.TUPLE__ESTRUCTURAL_FEATURE:
				setEStructuralFeature((EStructuralFeature)newValue);
				return;
			case ConflictPackage.TUPLE__POSITION:
				getPosition().clear();
				getPosition().addAll((Collection<? extends Integer>)newValue);
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
			case ConflictPackage.TUPLE__BRANCH:
				setBranch(BRANCH_EDEFAULT);
				return;
			case ConflictPackage.TUPLE__EOBJECT:
				setEObject((EObject)null);
				return;
			case ConflictPackage.TUPLE__ESTRUCTURAL_FEATURE:
				setEStructuralFeature((EStructuralFeature)null);
				return;
			case ConflictPackage.TUPLE__POSITION:
				getPosition().clear();
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
			case ConflictPackage.TUPLE__BRANCH:
				return branch != BRANCH_EDEFAULT;
			case ConflictPackage.TUPLE__EOBJECT:
				return eObject != null;
			case ConflictPackage.TUPLE__ESTRUCTURAL_FEATURE:
				return eStructuralFeature != null;
			case ConflictPackage.TUPLE__POSITION:
				return position != null && !position.isEmpty();
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
		result.append(" (branch: ");
		result.append(branch);
		result.append(", position: ");
		result.append(position);
		result.append(')');
		return result.toString();
	}

} //TupleImpl
