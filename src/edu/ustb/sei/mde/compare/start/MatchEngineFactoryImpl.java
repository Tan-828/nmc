package edu.ustb.sei.mde.compare.start;

import edu.ustb.sei.mde.compare.EqualityHelperExtensionProvider;
import edu.ustb.sei.mde.compare.IComparisonScope;
import edu.ustb.sei.mde.compare.IEObjectMatcher;
import edu.ustb.sei.mde.compare.IMatchEngine;
import edu.ustb.sei.mde.compare.WeightProvider;
import edu.ustb.sei.mde.compare.match.DefaultMatchEngine;
import edu.ustb.sei.mde.compare.match.EqualityHelperExtensionProviderDescriptorRegistryImpl;
import edu.ustb.sei.mde.compare.match.UseIdentifiers;
import edu.ustb.sei.mde.compare.match.WeightProviderDescriptorRegistryImpl;

/**
 * The default implementation of the {@link IMatchEngine.Factory.Registry}.
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 * @since 3.0
 */
public class MatchEngineFactoryImpl implements IMatchEngine.Factory {

	/** The match engine created by this factory. */
	protected IMatchEngine matchEngine;

	/** Ranking of this match engine. */
	private int ranking;

	/** A match engine needs a WeightProvider in case of this match engine do not use identifiers. */
	private WeightProvider.Descriptor.Registry weightProviderRegistry;

	/** A match engine may need a specific equality helper extension provider. */
	private EqualityHelperExtensionProvider.Descriptor.Registry equalityHelperExtensionProviderRegistry;

	/** Tells this factory whether the engines it creates should use identifiers or not. */
	private UseIdentifiers shouldUseIdentifiers;

	/**
	 * Constructor that instantiate a {@link DefaultMatchEngine}. This match engine will use a the standalone
	 * weight provider registry {@link WeightProviderDescriptorRegistryImpl.createStandaloneInstance()}.
	 */
	public MatchEngineFactoryImpl() {
		this(UseIdentifiers.WHEN_AVAILABLE, WeightProviderDescriptorRegistryImpl.createStandaloneInstance(),
				EqualityHelperExtensionProviderDescriptorRegistryImpl.createStandaloneInstance());
	}

	/**
	 * Constructor that instantiate a {@link DefaultMatchEngine} that will use identifiers as specified by the
	 * given {@code useIDs} enumeration. This match engine will use a the standalone weight provider registry
	 * {@link WeightProviderDescriptorRegistryImpl.createStandaloneInstance()}.
	 * 
	 * @param useIDs
	 *            the kinds of matcher to use.
	 */
	public MatchEngineFactoryImpl(UseIdentifiers useIDs) {
		this(useIDs, WeightProviderDescriptorRegistryImpl.createStandaloneInstance(),
				EqualityHelperExtensionProviderDescriptorRegistryImpl.createStandaloneInstance());
	}

	/**
	 * Constructor that instantiate a {@link DefaultMatchEngine} that will use identifiers as specified by the
	 * given {@code useIDs} enumeration.
	 * 
	 * @param useIDs
	 *            the kinds of matcher to use.
	 * @param weightProviderRegistry
	 *            A match engine needs a WeightProvider in case of this match engine do not use identifiers.
	 */
	public MatchEngineFactoryImpl(UseIdentifiers useIDs,
			WeightProvider.Descriptor.Registry weightProviderRegistry) {
		this(useIDs, weightProviderRegistry,
				EqualityHelperExtensionProviderDescriptorRegistryImpl.createStandaloneInstance());
	}

	/**
	 * Constructor that instantiate a {@link DefaultMatchEngine} that will use identifiers as specified by the
	 * given {@code useIDs} enumeration.
	 * 
	 * @param useIDs
	 *            the kinds of matcher to use.
	 * @param weightProviderRegistry
	 *            A match engine needs a WeightProvider in case of this match engine do not use identifiers.
	 * @param equalityHelperExtensionProviderRegistry
	 *            A match engine may need a Equality Helper Extension.
	 */
	public MatchEngineFactoryImpl(UseIdentifiers useIDs,
			WeightProvider.Descriptor.Registry weightProviderRegistry,
			EqualityHelperExtensionProvider.Descriptor.Registry equalityHelperExtensionProviderRegistry) {
		this.shouldUseIdentifiers = useIDs;
		this.weightProviderRegistry = weightProviderRegistry;
		this.equalityHelperExtensionProviderRegistry = equalityHelperExtensionProviderRegistry;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.IMatchEngine.Factory#getMatchEngine()
	 */
	// lyt
	public IMatchEngine getMatchEngine() {
		if (matchEngine == null) {
			final IEObjectMatcher matcher = DefaultMatchEngine.createDefaultEObjectMatcher(
					shouldUseIdentifiers, weightProviderRegistry, equalityHelperExtensionProviderRegistry);
			matchEngine = new DefaultMatchEngine(matcher);
			
		}
		return matchEngine;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.IMatchEngine.Factory#getRanking()
	 */
	public int getRanking() {
		return ranking;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.IMatchEngine.Factory#setRanking(int)
	 */
	public void setRanking(int r) {
		ranking = r;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.IMatchEngine.Factory#isMatchEngineFactoryFor(org.eclipse.emf.compare.scope.IComparisonScope)
	 */
	public boolean isMatchEngineFactoryFor(IComparisonScope scope) {
		return true;
	}

	/**
	 * The match engine needs a WeightProvider in case of this match engine do not use identifiers.
	 * 
	 * @param registry
	 *            the registry to associate with the match engine.
	 */
	void setWeightProviderRegistry(WeightProvider.Descriptor.Registry registry) {
		this.weightProviderRegistry = registry;
		// TODO remove this condition once the deprecated MatchEngineFactoryImpl(IEObjectMatcher,
		// IComparisonFactory) is removed
		if (shouldUseIdentifiers != null) {
			this.matchEngine = null;
		}
	}

	/**
	 * The match engine may need a Equality Helper Extension.
	 * 
	 * @param equalityHelperExtensionProviderRegistry
	 *            the registry to associate with the match engine.
	 */
	public void setEqualityHelperExtensionProviderRegistry(
			EqualityHelperExtensionProvider.Descriptor.Registry equalityHelperExtensionProviderRegistry) {
		this.equalityHelperExtensionProviderRegistry = equalityHelperExtensionProviderRegistry;
		// TODO remove this condition once the deprecated MatchEngineFactoryImpl(IEObjectMatcher,
		// IComparisonFactory) is removed
		if (shouldUseIdentifiers != null) {
			this.matchEngine = null;
		}
	}

}

