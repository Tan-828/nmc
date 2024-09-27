package edu.ustb.sei.mde.nmc.compare.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.cache.CacheBuilder;

import edu.ustb.sei.mde.nmc.compare.IEqualityHelper;
import edu.ustb.sei.mde.nmc.compare.Match;
import edu.ustb.sei.mde.nmc.compare.impl.ComparisonImpl;
import edu.ustb.sei.mde.nmc.compare.match.DefaultMatchEngine;
import edu.ustb.sei.mde.nmc.compare.match.EqualityHelper;

/**
 * This specialization of the {@link ComparisonImpl} class allows us to define the derived features and
 * operations implementations.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ComparisonSpec extends ComparisonImpl {
	/** Keeps a reference to our match cross referencer. */
	private MatchCrossReferencer matchCrossReferencer;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.impl.ComparisonImpl#getMatch(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public Match getMatch(EObject element) {
		if (element != null) {
			if (matchCrossReferencer == null) {
				matchCrossReferencer = new MatchCrossReferencer();
				eAdapters().add(matchCrossReferencer);
			}

			final Collection<EStructuralFeature.Setting> settings = matchCrossReferencer
					.getNonNavigableInverseReferences(element, false);

			if (!settings.isEmpty()) {
				// Cast to List and Match without testing.
				// The matchCrossReferencer will only return a List of Settings for Matches
				// #getNonNavigableInverseReferences
				// This method is in the general case called O(n^2) times, so small performance improvements
				// (iterator overhead) here have a big overall impact.
				return (Match)((List<Setting>)settings).get(0).getEObject();
			}
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.impl.ComparisonImpl#getEqualityHelper()
	 */
	@Override
	public IEqualityHelper getEqualityHelper() {
		IEqualityHelper ret = (IEqualityHelper)EcoreUtil.getExistingAdapter(this, IEqualityHelper.class);			
		if (ret == null) {				
			ret = new EqualityHelper(EqualityHelper.createDefaultCache(CacheBuilder.newBuilder()
					.maximumSize(DefaultMatchEngine.DEFAULT_EOBJECT_URI_CACHE_MAX_SIZE)));				
			this.eAdapters().add(ret);
			ret.setTarget(this);
		}
		return ret;
	}
}

