package edu.ustb.sei.mde.nmc.compare.diff;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

/**
 * This will be used to attach the FeatureFilter to its comparison so that it can be used after the diff
 * process.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class FeatureFilterAdapter extends AdapterImpl {
	/** The wrapped feature filter. */
	private final FeatureFilter featureFilter;

	/**
	 * Constructor.
	 * 
	 * @param featureFilter
	 *            The wrapped feature filter.
	 */
	public FeatureFilterAdapter(FeatureFilter featureFilter) {
		this.featureFilter = featureFilter;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return type == FeatureFilterAdapter.class;
	}

	public FeatureFilter getFeatureFilter() {
		return featureFilter;
	}
}

