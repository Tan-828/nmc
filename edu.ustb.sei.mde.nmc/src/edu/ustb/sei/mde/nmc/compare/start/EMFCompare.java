package edu.ustb.sei.mde.nmc.compare.start;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.apache.commons.collections4.map.MultiKeyMap;
import org.eclipse.emf.ecore.EObject;

import edu.ustb.sei.mde.nmc.compare.Comparison;
import edu.ustb.sei.mde.nmc.compare.IComparisonScope;
import edu.ustb.sei.mde.nmc.compare.IMatchEngine;

/**
 * This class serves as the main entry point of a comparison. When all that is
 * wanted is a basic comparison of two or three notifiers, a comparison using
 * all of the default configuration can be launched through
 * <code>EMFCompare.builder().build().compare(EMFCompare.createDefaultScope(left, right, origin))</code>.
 * <p>
 * When in need of a more customized comparison, the API can be used through
 * chained calls. For example, if you need to compare two notifiers
 * ({@code left} and {@code right}) while ignoring their identifiers, with a
 * given progress monitor (call it {@code progress}), you can do so through :
 * <code>
 * EMFCompare.builder().setMatchEngine(DefaultMatchEngine.create(UseIdentifiers.NEVER)).build().compare(EMFCompare.createDefaultScope(left, right), new BasicMonitor())
 * </code>.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class EMFCompare {

	/**
	 * The value for diagnostics coming from EMF compare.
	 * 
	 * @since 3.2
	 */
	public static final String DIAGNOSTIC_SOURCE = "edu.ustb.lesley.compare"; //$NON-NLS-1$

	/** The registry we'll use to create a match engine for this comparison. */
	private final IMatchEngine.Factory.Registry matchEngineFactoryRegistry;
	
	// lyt
	protected IMatchEngine matchEngine;

	/**
	 * Creates a new EMFCompare object able to compare Notifier with the help of
	 * given engines.
	 * 
	 * @param matchEngineFactoryRegistry   {@link IMatchEngine.Factory.Registry} to
	 *                                     use to find a match engine factory to
	 *                                     compute comparison
	 * @param diffEngine                   IDiffEngine to use to compute comparison
	 * @param reqEngine                    IReqEngine to use to compute comparison
	 * @param equiEngine                   IEquiEngine to use to compute comparison
	 * @param conflictDetector             IConflictDetector to use to compute
	 *                                     comparison
	 * @param postProcessorFactoryRegistry PostProcessorRegistry to use to find an
	 *                                     IPostProcessor
	 */
	protected EMFCompare(IMatchEngine.Factory.Registry matchEngineFactoryRegistry) {
		this.matchEngineFactoryRegistry = checkNotNull(matchEngineFactoryRegistry);
	}

	/**
	 * Launches the comparison with the given scope and reporting progress to the
	 * given {@code monitor}.
	 * 
	 * @param scope   the scope to compare, must not be {@code null}.
	 * @param monitor the monitor to report progress to, must not be {@code null}.
	 *                {@code done()} will be called on it. If the monitor is
	 *                cancelled, the result may be {@code null} (in rare cases) or
	 *                contain a Diagnostic that indicates cancellation. <b>Note:</b>
	 *                The given monitor is expected to use 10 ticks for 100%.
	 * @return The result of the comparison, which is never null but may be empty if
	 *         the monitor has been canceled immediately after entering this method.
	 *         The returned comparison will contain a relevant diagnostic indicating
	 *         if the comparison has been canceled or if problems have occurred
	 *         during its computation. Consequently, it is necessary to check the
	 *         diagnostic of the returned comparison before using it.
	 * @throws ComparisonCanceledException If the comparison is cancelled at any
	 *                                     time.
	 */
	public Comparison compare(IComparisonScope scope) {
		checkNotNull(scope);

		Comparison comparison = null;
		
		comparison = getMatchEngine().match(scope);
		
		return comparison;
	}
		
	// lyt
	public IMatchEngine getMatchEngine() {
		if(matchEngine == null) {
			matchEngine = matchEngineFactoryRegistry.getMatchEngineFactory().getMatchEngine();
		} 
		return matchEngine;
	}
	
	// lyt
	public void compareADD(Comparison comparison, List<EObject> leftEObjects, List<EObject> rightEObjects, MultiKeyMap<EObject, Double> distanceMap) {
		
		getMatchEngine().matchADD(comparison, leftEObjects, rightEObjects, distanceMap);
		
	}	

	/**
	 * Creates a new builder to configure the creation of a new EMFCompare object.
	 * 
	 * @return a new builder.
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * A Builder pattern to instantiate EMFCompare objects.
	 * 
	 * @author <a href="mailto:mikael.barbero@obeo.fr">Mikael Barbero</a>
	 */
	public static class Builder {

		/** The registry we'll use to create a match engine for this comparison. */
		protected IMatchEngine.Factory.Registry matchEngineFactoryRegistry;

		/**
		 * Creates a new builder object.
		 */
		protected Builder() {
		}

		/**
		 * Sets the IMatchEngine.Factory.Registry to be used to find a match engine
		 * factory to compute comparison.
		 * 
		 * @param mefr the IMatchEngine.Factory.Registry to be used to find a match
		 *             engine factory to compute comparison.
		 * @return this same builder to allow chained call.
		 */
		public Builder setMatchEngineFactoryRegistry(IMatchEngine.Factory.Registry mefr) {
			this.matchEngineFactoryRegistry = checkNotNull(mefr);
			return this;
		}

		/**
		 * Instantiates and return an EMFCompare object configured with the previously
		 * given engines.
		 * 
		 * @return an EMFCompare object configured with the previously given engines
		 */
		public EMFCompare build() {
			if (matchEngineFactoryRegistry == null) {
				matchEngineFactoryRegistry = MatchEngineFactoryRegistryImpl.createStandaloneInstance();
			}
			return new EMFCompare(this.matchEngineFactoryRegistry);
		}
	}

}
