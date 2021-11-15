package edu.ustb.sei.mde.compare.util;

import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;

/**
 * This class will provide a number of Predicates that can be used to retrieve particular {@link Diff}s from
 * an iterable.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 * @deprecated this class provides Guava predicates. Please consider using EMFCompareJavaPredicates instead.
 */
@Deprecated
public final class EMFComparePredicates {
	/**
	 * Predicate used to know if the given EObject is an EGenericType without eTypeArguments. When an
	 * EGenericType has arguments, it seems that the mutually derived references are not applicable in this
	 * case.
	 * 
	 * @return true, if the given EObject is an EGenericType without eTypeArguments, false otherwise.
	 */
	public static final Predicate<? super EObject> IS_EGENERIC_TYPE_WITHOUT_PARAMETERS = new Predicate<EObject>() {
		public boolean apply(EObject input) {
			final boolean isEGenericWithoutParams;
			if (input instanceof EGenericType && ((EGenericType)input).getETypeArguments().isEmpty()) {
				if (input.eContainer() instanceof EGenericType) {
					EGenericType eGenericTypeContainer = (EGenericType)(input.eContainer());
					isEGenericWithoutParams = !(eGenericTypeContainer.getETypeArguments().contains(input)
							|| input.equals(eGenericTypeContainer.getELowerBound())
							|| input.equals(eGenericTypeContainer.getEUpperBound()));
				} else {
					isEGenericWithoutParams = true;
				}
			} else {
				isEGenericWithoutParams = false;
			}
			return isEGenericWithoutParams;
		}
	};

	/**
	 * This class does not need to be instantiated.
	 */
	private EMFComparePredicates() {
		// Hides default constructor
	}
}
