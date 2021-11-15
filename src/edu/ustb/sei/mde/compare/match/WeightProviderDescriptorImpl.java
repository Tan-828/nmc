package edu.ustb.sei.mde.compare.match;

import java.util.regex.Pattern;

import edu.ustb.sei.mde.compare.WeightProvider;


/**
 * A simple implementation of {@link WeightProvider.Descriptor} that will delegate its method implementation
 * to values given to its constructor.
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 */
public class WeightProviderDescriptorImpl implements WeightProvider.Descriptor {

	/** The wrapped weight provider. */
	private WeightProvider weightProvider;

	/** The ranking of the weight provider. */
	private int ranking;

	/** The pattern of namespace URI on which this weight provider can be applied. */
	private Pattern nsURI;

	/**
	 * Creates the descriptor from an existing weight provider.
	 * 
	 * @param weightProvider
	 *            the given weight provider.
	 * @param r
	 *            the ranking of the weight provider.
	 * @param nsURI
	 *            the pattern of namespace URI on which this weight provider can be applied.
	 */
	public WeightProviderDescriptorImpl(WeightProvider weightProvider, int r, Pattern nsURI) {
		this.weightProvider = weightProvider;
		this.ranking = r;
		this.nsURI = nsURI;
	}

	/**
	 * {@inheritDoc}
	 */
	public WeightProvider getWeightProvider() {
		return weightProvider;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRanking() {
		return ranking;
	}

	/**
	 * {@inheritDoc}
	 */
	public Pattern getNsURI() {
		return nsURI;
	}
}

