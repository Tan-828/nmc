package edu.ustb.sei.mde.compare;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import edu.ustb.sei.mde.compare.impl.ComparePackageImpl;


public interface ComparePackage extends EPackage{

	/** The package name. */
	String eNAME = "compare"; 
	
	/** The package namespace URI. */
	String eNS_URI = "http://edu.ustb.lesley.compare";
	
	/** The package namespace name. */
	String eNS_PREFIX = "compare";
	
	/** The singleton instance of the package. */
	ComparePackage eINSTANCE = ComparePackageImpl.init();
	
	/** The meta object id for the Comparison class. */
	int COMPARISON = 0;
	
	/** The feature id for the Matched Resources containment reference list. */
	int COMPARISON__MATCHED_RESOURCES = 0;
	
	/**  The feature id for the Matches containment reference list. */
	int COMPARISON__MATCHES = 1;
		
	/** The feature id for the Three Way attribute. */
	int COMPARISON__THREE_WAY = 2;	// lyt: ²»Ð´³É4	
	
	/** The meta object id for the Match Resource class. */
	int MATCH_RESOURCE = 1;
	
	/** The feature id for the Left URI attribute.  */
	int MATCH_RESOURCE__LEFT_URI = 0;
	
	/** The feature id for the Right URI attribute. */
	int MATCH_RESOURCE__RIGHT_URI = 1;
	
	/** The feature id for the Origin URI attribute. */
	int MATCH_RESOURCE__ORIGIN_URI = 2;
	
	/** The feature id for the Left attribute. */
	int MATCH_RESOURCE__LEFT = 3;
	
	/** The feature id for the Right attribute. */
	int MATCH_RESOURCE__RIGHT = 4;
	
	/** The feature id for the Origin attribute. */
	int MATCH_RESOURCE__ORIGIN = 5;
	
	/** The feature id for the Comparison container reference. */
	int MATCH_RESOURCE__COMPARISON = 6;
	
	/** The meta object id for the Match class. */
	int MATCH = 2;

	/** The feature id for the Submatches containment reference list. */
	int MATCH__SUBMATCHES = 0;
	
	/** The feature id for the Left reference. */
	int MATCH__LEFT = 1;
	
	/** The feature id for the Right reference. */
	int MATCH__RIGHT = 2;
	
	/** The feature id for the Origin reference. */
	int MATCH__ORIGIN = 3;
	
	/** The meta object id for the EIterable data type. */
	int EITERABLE = 3;
	
	/** The meta object id for the IEquality Helper data type. */
	int IEQUALITY_HELPER = 4;
	
	/** Returns the meta object for class Comparison. */
	EClass getComparison();
	
	/** Returns the meta object for the containment reference list */
	EReference getComparison_MatchedResources();
	
	/** Returns the meta object for the containment reference list Matches */
	EReference getComparison_Matches();
	
	/** Returns the meta object for the attribute Three Way. */
	EAttribute getComparison_ThreeWay();
	
	/** Returns the meta object for class Match Resource. */
	EClass getMatchResource();
	
	/** Returns the meta object for the attribute Left URI. */
	EAttribute getMatchResource_LeftURI();
	
	/** Returns the meta object for the attribute Right URI. */
	EAttribute getMatchResource_RightURI();
	
	/** Returns the meta object for the attribute Origin URI. */
	EAttribute getMatchResource_OriginURI();
	
	/** Returns the meta object for the attribute Left. */
	EAttribute getMatchResource_Left();

	/** Returns the meta object for the attribute Right. */
	EAttribute getMatchResource_Right();
	
	/** Returns the meta object for the attribute Origin. */
	EAttribute getMatchResource_Origin();
	
	/** Returns the meta object for the container reference Comparison. */
	EReference getMatchResource_Comparison();
	
	/** Returns the meta object for class Match. */
	EClass getMatch();
	
	/** Returns the meta object for the containment reference list Submatches. */
	EReference getMatch_Submatches();
	
	/** Returns the meta object for the reference Left. */
	EReference getMatch_Left();
	
	/** Returns the meta object for the reference Right. */
	EReference getMatch_Right();
	
	/** Returns the meta object for the reference Origin. */
	EReference getMatch_Origin();
	
	/** Returns the meta object for data type EIterable. */
	EDataType getEIterable();
	
	/** Returns the meta object for data type IEquality Helper. */
	EDataType getIEqualityHelper();
	
	/** Returns the factory that creates the instances of the model. */
	CompareFactory getCompareFactory();
		
	/** Defines literals for the meta objects that represent */
	interface Literals {
		
		/** The meta object literal for the Comparison class. */
		EClass COMPARISON = eINSTANCE.getComparison();
		
		/** The meta object literal for the Matched Resources containment reference list feature. */
		EReference COMPARISON__MATCHED_RESOURCES = eINSTANCE.getComparison_MatchedResources();
		
		/** The meta object literal for the Matches containment reference list feature. */
		EReference COMPARISON__MATCHES = eINSTANCE.getComparison_Matches();
		
		/** The meta object literal for the Three Way attribute feature. */
		EAttribute COMPARISON__THREE_WAY = eINSTANCE.getComparison_ThreeWay();
		
		/** The meta object literal for the Match Resource class. */
		EClass MATCH_RESOURCE = eINSTANCE.getMatchResource();
		
		/** The meta object literal for the Left URI attribute feature. */
		EAttribute MATCH_RESOURCE__LEFT_URI = eINSTANCE.getMatchResource_LeftURI();
		
		/** The meta object literal for the Right URI attribute feature. */
		EAttribute MATCH_RESOURCE__RIGHT_URI = eINSTANCE.getMatchResource_RightURI();
		
		/** The meta object literal for the Origin URI attribute feature. */
		EAttribute MATCH_RESOURCE__ORIGIN_URI = eINSTANCE.getMatchResource_OriginURI();
		
		/** The meta object literal for the Left attribute feature. */
		EAttribute MATCH_RESOURCE__LEFT = eINSTANCE.getMatchResource_Left();
		
		/** The meta object literal for the Right attribute feature. */
		EAttribute MATCH_RESOURCE__RIGHT = eINSTANCE.getMatchResource_Right();
		
		/** The meta object literal for the Origin attribute feature. */
		EAttribute MATCH_RESOURCE__ORIGIN = eINSTANCE.getMatchResource_Origin();
		
		/** The meta object literal for the Comparison container reference feature. */
		EReference MATCH_RESOURCE__COMPARISON = eINSTANCE.getMatchResource_Comparison();

		/** The meta object literal for the Match class. */
		EClass MATCH = eINSTANCE.getMatch();
		
		/** The meta object literal for the Submatches containment reference list feature. */
		EReference MATCH__SUBMATCHES = eINSTANCE.getMatch_Submatches();
		
		/** The meta object literal for the Left reference feature. */
		EReference MATCH__LEFT = eINSTANCE.getMatch_Left();
		
		/** The meta object literal for the Right reference feature. */
		EReference MATCH__RIGHT = eINSTANCE.getMatch_Right();
		
		/** The meta object literal for the Origin reference feature. */
		EReference MATCH__ORIGIN = eINSTANCE.getMatch_Origin();
		
		/** The meta object literal for the EIterable data type. */
		EDataType EITERABLE = eINSTANCE.getEIterable();
		
		/** The meta object literal for the IEquality Helper data type. */
		EDataType IEQUALITY_HELPER = eINSTANCE.getIEqualityHelper();
	}
		
}
