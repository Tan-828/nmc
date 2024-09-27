package edu.ustb.sei.mde.nmc.compare.start;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.Preconditions;

import edu.ustb.sei.mde.nmc.compare.IMatchEngine;
import edu.ustb.sei.mde.nmc.compare.match.UseIdentifiers;
import edu.ustb.sei.mde.nmc.compare.match.WeightProviderDescriptorRegistryImpl;


/**
 * The default implementation of the {@link IMatchEngine.Factory.Registry}.
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 * @since 3.0
 */
public class MatchEngineFactoryRegistryImpl implements IMatchEngine.Factory.Registry {

	/** A map that associates the class name to theirs {@link IMatchEngine.Factory}. */
	private final Map<String, IMatchEngine.Factory> map;

	/**
	 * Constructs the registry.
	 */
	public MatchEngineFactoryRegistryImpl() {
		map = new ConcurrentHashMap<String, IMatchEngine.Factory>();
	}

	/**
	 * Returns a registry filled with the default match engine factory provided by EMF Compare
	 * {@link MatchEngineFactoryImpl}.
	 * 
	 * @return A registry filled with the default match engine factory provided by EMF Compare.
	 */
	public static IMatchEngine.Factory.Registry createStandaloneInstance() {
		final IMatchEngine.Factory.Registry registry = new MatchEngineFactoryRegistryImpl();

		final MatchEngineFactoryImpl matchEngineFactory = new MatchEngineFactoryImpl(
				UseIdentifiers.WHEN_AVAILABLE);
		matchEngineFactory.setRanking(10);
		matchEngineFactory
				.setWeightProviderRegistry(WeightProviderDescriptorRegistryImpl.createStandaloneInstance());
		registry.add(matchEngineFactory);

		return registry;
	}

	// lyt: 如果设置了优先级更高的，map中只有一个
	public IMatchEngine.Factory getMatchEngineFactory(){
		return map.values().iterator().next();		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.IMatchEngine.Factory.Registry#add(org.eclipse.emf.compare.match.IMatchEngine)
	 */
	public IMatchEngine.Factory add(IMatchEngine.Factory filter) {
		Preconditions.checkNotNull(filter);
		return map.put(filter.getClass().getName(), filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.IMatchEngine.Factory.Registry#remove(java.lang.String)
	 */
	public IMatchEngine.Factory remove(String className) {
		return map.remove(className);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.IMatchEngine.Factory.Registry#clear()
	 */
	public void clear() {
		map.clear();
	}

}

