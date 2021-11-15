package edu.ustb.sei.mde.compare;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import edu.ustb.sei.mde.compare.match.IdentifierEObjectMatcher;

/**
 * An {@link IEObjectMatcher} will be used by the default implementation of the
 * {@link org.eclipse.emf.compare.match.DefaultMatchEngine} in order to determine the mappings between three
 * lists of EObjects coming from the left, right and origin sides.
 * <p>
 * Do take note that the match engine expects {@link IEObjectMatcher}s to return both matching and unmatching
 * EObjects.
 * </p>
 * <p>
 * A default implementation of this interface, matching EObjects through their identifier, can also be
 * subclassed by clients. Sett {@link IdentifierEObjectMatcher}.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 * @see IdentifierEObjectMatcher
 */
public interface IEObjectMatcher {
	/**
	 * This will be called by the match engine to determine matches between EObjects.
	 * <p>
	 * The implementation should update the given comparison object by adding the Matches it detect. These
	 * matches should include both matching and unmatchings EObjects (i.e. EObjects that can be matched in all
	 * three lists, EObjects that cna be matched in only two of the three lists, and EObjects that can only be
	 * found in one of the three.
	 * </p>
	 * 
	 * @param comparison
	 *            the comparison to update.
	 * @param leftEObjects
	 *            An iterator over the EObjects that could be found in the left side.
	 * @param rightEObjects
	 *            An iterator over the EObjects that could be found in the right side.
	 * @param originEObjects
	 *            And iterator over the EObject that may be considered ancestors of the couples that can be
	 *            detected in the left and right sides.
	 * @param monitor
	 *            The monitor to report progress or to check for cancellation
	 */
	void createMatches(Comparison comparison, Iterator<? extends EObject> leftEObjects,
			Iterator<? extends EObject> rightEObjects, Iterator<? extends EObject> originEObjects);
}

