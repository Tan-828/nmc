package edu.ustb.sei.mde.compare.internal;

import com.google.common.collect.ImmutableSet;

import edu.ustb.sei.mde.compare.ComparePackage;
import edu.ustb.sei.mde.compare.Match;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EReference;

/**
 * This implementation of an {@link org.eclipse.emf.ecore.util.ECrossReferenceAdapter} will allow us to only
 * attach ourselves to the Match elements.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class MatchCrossReferencer extends AbstractCompareECrossReferencerAdapter {
	/**
	 * We're only interested in the cross references that come from the Match.left, Match.right and
	 * Match.origin references.
	 */
	private static final ImmutableSet<EReference> INCLUDED_REFERENCES = ImmutableSet.of(
			ComparePackage.Literals.MATCH__LEFT, ComparePackage.Literals.MATCH__RIGHT,
			ComparePackage.Literals.MATCH__ORIGIN);

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#addAdapter(org.eclipse.emf.common.notify.Notifier)
	 */
	@Override
	protected void addAdapter(Notifier notifier) {
		if (notifier instanceof Match) {
			super.addAdapter(notifier);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#isIncluded(org.eclipse.emf.ecore.EReference)
	 */
	@Override
	protected boolean isIncluded(EReference eReference) {
		return INCLUDED_REFERENCES.contains(eReference);
	}
}

