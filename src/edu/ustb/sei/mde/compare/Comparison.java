package edu.ustb.sei.mde.compare;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Comparison</b></em>'. <!-- end-user-doc --> <!-- begin-model-doc -->
 * This will act as the "root" of a comparison. It will reference one match for
 * every root of the input models, along with the differences detected for each
 * of them. <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.compare.Comparison#getMatchedResources <em>Matched
 * Resources</em>}</li>
 * <li>{@link org.eclipse.emf.compare.Comparison#getMatches
 * <em>Matches</em>}</li>
 * <li>{@link org.eclipse.emf.compare.Comparison#getConflicts
 * <em>Conflicts</em>}</li>
 * <li>{@link org.eclipse.emf.compare.Comparison#getEquivalences
 * <em>Equivalences</em>}</li>
 * <li>{@link org.eclipse.emf.compare.Comparison#isThreeWay <em>Three
 * Way</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.emf.compare.ComparePackage#getComparison()
 * @model
 * @generated
 */
public interface Comparison extends EObject {
	/**
	 * Returns the value of the '<em><b>Matched Resources</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.emf.compare.MatchResource}. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> This contains the mappings for each
	 * compared Resource. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Matched Resources</em>' containment reference
	 *         list.
	 * @see org.eclipse.emf.compare.ComparePackage#getComparison_MatchedResources()
	 * @model containment="true"
	 * @generated
	 */
	EList<MatchResource> getMatchedResources();

	/**
	 * Returns the value of the '<em><b>Matches</b></em>' containment reference
	 * list. The list contents are of type {@link org.eclipse.emf.compare.Match}.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> This
	 * contains the match tree "mimicking" the input models' hierarchy. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Matches</em>' containment reference list.
	 * @see org.eclipse.emf.compare.ComparePackage#getComparison_Matches()
	 * @model containment="true"
	 * @generated
	 */
	EList<Match> getMatches();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Finds
	 * and return the Match for the given EObject.
	 * 
	 * @param element The EObject for which we seek the match. <!-- end-model-doc
	 *                -->
	 * @model
	 * @generated
	 */
	Match getMatch(EObject element);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation" dataType="org.eclipse.emf.compare.IEqualityHelper"
	 * @generated
	 */
	IEqualityHelper getEqualityHelper();

	/**
	 * Returns the value of the '<em><b>Three Way</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Three Way</em>' attribute.
	 * @see #setThreeWay(boolean)
	 * @see org.eclipse.emf.compare.ComparePackage#getComparison_ThreeWay()
	 * @model
	 * @generated
	 */
	boolean isThreeWay();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.compare.Comparison#isThreeWay
	 * <em>Three Way</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Three Way</em>' attribute.
	 * @see #isThreeWay()
	 * @generated
	 */
	void setThreeWay(boolean value);

} // Comparison
