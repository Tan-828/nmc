package edu.ustb.sei.mde.nmc.compare.match;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import edu.ustb.sei.mde.nmc.compare.IEqualityHelper;
import edu.ustb.sei.mde.nmc.compare.IEqualityHelperFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * Default implementation of {@link IEqualityHelperFactory}.
 * 
 * @author <a href="mailto:mikael.barbero@obeo.fr">Mikael Barbero</a>
 */
public class DefaultEqualityHelperFactory implements IEqualityHelperFactory {
	/** CacheBuilder that will be used to instantiate a cache. */
	private final CacheBuilder<Object, Object> cacheBuilder;

	/**
	 * Default constructor.
	 */
	public DefaultEqualityHelperFactory() {
		this(CacheBuilder.newBuilder().maximumSize(DefaultMatchEngine.DEFAULT_EOBJECT_URI_CACHE_MAX_SIZE));
	}

	/**
	 * Creates a factory with the given CacheBuilder.
	 * 
	 * @param cacheBuilder
	 *            The cache builder to use to instantiate an {@link EqualityHelper}.
	 */
	public DefaultEqualityHelperFactory(CacheBuilder<Object, Object> cacheBuilder) {
		this.cacheBuilder = cacheBuilder;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.IEqualityHelperFactory#createEqualityHelper()
	 */
	public IEqualityHelper createEqualityHelper() {
		LoadingCache<EObject, URI> cache = EqualityHelper.createDefaultCache(getCacheBuilder());
		IEqualityHelper equalityHelper = new EqualityHelper(cache);
		return equalityHelper;
	}

	/**
	 * Returns the cache builder that should be used by this factory to create its equality helpers.
	 * 
	 * @return The cache builder that should be used by this factory to create its equality helpers.
	 */
	protected CacheBuilder<Object, Object> getCacheBuilder() {
		return cacheBuilder;
	}
}

