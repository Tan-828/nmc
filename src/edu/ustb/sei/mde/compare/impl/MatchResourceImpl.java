package edu.ustb.sei.mde.compare.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import edu.ustb.sei.mde.compare.ComparePackage;
import edu.ustb.sei.mde.compare.Comparison;
import edu.ustb.sei.mde.compare.MatchResource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Match Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.compare.impl.MatchResourceImpl#getLeftURI <em>Left URI</em>}</li>
 *   <li>{@link org.eclipse.emf.compare.impl.MatchResourceImpl#getRightURI <em>Right URI</em>}</li>
 *   <li>{@link org.eclipse.emf.compare.impl.MatchResourceImpl#getOriginURI <em>Origin URI</em>}</li>
 *   <li>{@link org.eclipse.emf.compare.impl.MatchResourceImpl#getLeft <em>Left</em>}</li>
 *   <li>{@link org.eclipse.emf.compare.impl.MatchResourceImpl#getRight <em>Right</em>}</li>
 *   <li>{@link org.eclipse.emf.compare.impl.MatchResourceImpl#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.eclipse.emf.compare.impl.MatchResourceImpl#getComparison <em>Comparison</em>}</li>
 *   <li>{@link org.eclipse.emf.compare.impl.MatchResourceImpl#getLocationChanges <em>Location Changes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
//Supressing warnings : generated code
@SuppressWarnings("all")
public class MatchResourceImpl extends MinimalEObjectImpl.Container implements MatchResource {
	/**
	 * The default value of the '{@link #getLeftURI() <em>Left URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftURI()
	 * @generated
	 * @ordered
	 */
	protected static final String LEFT_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLeftURI() <em>Left URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftURI()
	 * @generated
	 * @ordered
	 */
	protected String leftURI = LEFT_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getRightURI() <em>Right URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightURI()
	 * @generated
	 * @ordered
	 */
	protected static final String RIGHT_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRightURI() <em>Right URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightURI()
	 * @generated
	 * @ordered
	 */
	protected String rightURI = RIGHT_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getOriginURI() <em>Origin URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginURI()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGIN_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOriginURI() <em>Origin URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginURI()
	 * @generated
	 * @ordered
	 */
	protected String originURI = ORIGIN_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getLeft() <em>Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeft()
	 * @generated
	 * @ordered
	 */
	protected static final Resource LEFT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLeft() <em>Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeft()
	 * @generated
	 * @ordered
	 */
	protected Resource left = LEFT_EDEFAULT;

	/**
	 * The default value of the '{@link #getRight() <em>Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRight()
	 * @generated
	 * @ordered
	 */
	protected static final Resource RIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRight() <em>Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRight()
	 * @generated
	 * @ordered
	 */
	protected Resource right = RIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigin()
	 * @generated
	 * @ordered
	 */
	protected static final Resource ORIGIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigin()
	 * @generated
	 * @ordered
	 */
	protected Resource origin = ORIGIN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLocationChanges() <em>Location Changes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocationChanges()
	 * @generated
	 * @ordered
	 * @since 3.2
	 */

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MatchResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComparePackage.Literals.MATCH_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLeftURI() {
		return leftURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftURI(String newLeftURI) {
		String oldLeftURI = leftURI;
		leftURI = newLeftURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComparePackage.MATCH_RESOURCE__LEFT_URI,
					oldLeftURI, leftURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRightURI() {
		return rightURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightURI(String newRightURI) {
		String oldRightURI = rightURI;
		rightURI = newRightURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComparePackage.MATCH_RESOURCE__RIGHT_URI,
					oldRightURI, rightURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOriginURI() {
		return originURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginURI(String newOriginURI) {
		String oldOriginURI = originURI;
		originURI = newOriginURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComparePackage.MATCH_RESOURCE__ORIGIN_URI,
					oldOriginURI, originURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resource getLeft() {
		return left;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeft(Resource newLeft) {
		Resource oldLeft = left;
		left = newLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComparePackage.MATCH_RESOURCE__LEFT,
					oldLeft, left));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resource getRight() {
		return right;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRight(Resource newRight) {
		Resource oldRight = right;
		right = newRight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComparePackage.MATCH_RESOURCE__RIGHT,
					oldRight, right));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resource getOrigin() {
		return origin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrigin(Resource newOrigin) {
		Resource oldOrigin = origin;
		origin = newOrigin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComparePackage.MATCH_RESOURCE__ORIGIN,
					oldOrigin, origin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Comparison getComparison() {
		if (eContainerFeatureID() != ComparePackage.MATCH_RESOURCE__COMPARISON)
			return null;
		return (Comparison)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComparison(Comparison newComparison, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newComparison, ComparePackage.MATCH_RESOURCE__COMPARISON,
				msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComparison(Comparison newComparison) {
		if (newComparison != eInternalContainer()
				|| (eContainerFeatureID() != ComparePackage.MATCH_RESOURCE__COMPARISON && newComparison != null)) {
			if (EcoreUtil.isAncestor(this, newComparison))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newComparison != null)
				msgs = ((InternalEObject)newComparison).eInverseAdd(this,
						ComparePackage.COMPARISON__MATCHED_RESOURCES, Comparison.class, msgs);
			msgs = basicSetComparison(newComparison, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComparePackage.MATCH_RESOURCE__COMPARISON,
					newComparison, newComparison));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComparePackage.MATCH_RESOURCE__COMPARISON:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetComparison((Comparison)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ComparePackage.MATCH_RESOURCE__COMPARISON:
				return eInternalContainer().eInverseRemove(this,
						ComparePackage.COMPARISON__MATCHED_RESOURCES, Comparison.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (leftURI: "); //$NON-NLS-1$
		result.append(leftURI);
		result.append(", rightURI: "); //$NON-NLS-1$
		result.append(rightURI);
		result.append(", originURI: "); //$NON-NLS-1$
		result.append(originURI);
		result.append(", left: "); //$NON-NLS-1$
		result.append(left);
		result.append(", right: "); //$NON-NLS-1$
		result.append(right);
		result.append(", origin: "); //$NON-NLS-1$
		result.append(origin);
		result.append(')');
		return result.toString();
	}

} //MatchResourceImpl

