package edu.ustb.sei.mde.compare.match;

import static edu.ustb.sei.mde.compare.start.EMFCompare.DIAGNOSTIC_SOURCE;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMIResource;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import edu.ustb.sei.mde.compare.CompareFactory;
import edu.ustb.sei.mde.compare.Comparison;
import edu.ustb.sei.mde.compare.IEObjectMatcher;
import edu.ustb.sei.mde.compare.Match;
import edu.ustb.sei.mde.compare.EObjectIndex.Side;
import edu.ustb.sei.mde.compare.util.EMFCompareMessages;
import edu.ustb.sei.mde.compare.match.MatchComputationByID;

/**
 * This implementation of an {@link IEObjectMatcher} will create {@link Match}es based on the input EObjects
 * identifiers (either XMI:ID or attribute ID) alone.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class IdentifierEObjectMatcher implements IEObjectMatcher {
	/**
	 * This instance might have a delegate matcher. The delegate matcher will be called when no ID is found
	 * and its results are aggregated with the current matcher.
	 */
	private Optional<IEObjectMatcher> delegate;

	/**
	 * This will be used to determine what represents the "identifier" of an EObject. By default, we will use
	 * the following logic, in order (i.e. if condition 1 is fulfilled, we will not try conditions 2 and 3) :
	 * <ol>
	 * <li>If the given eObject is a proxy, it is uniquely identified by its URI fragment.</li>
	 * <li>If the eObject is located in an XMI resource and has an XMI ID, use this as its unique identifier.
	 * </li>
	 * <li>If the eObject's EClass has an eIdAttribute set, use this attribute's value.</li>
	 * </ol>
	 */
	private Function<EObject, String> idComputation = new DefaultIDFunction();

	/** A diagnostic to be used for reporting on the matches. */
	private BasicDiagnostic diagnostic;

	/**
	 * Creates an ID based matcher without any delegate.
	 */
	public IdentifierEObjectMatcher() {
		this(null, new DefaultIDFunction());
	}

	/**
	 * Creates an ID based matcher with the given delegate when no ID can be found.
	 * 
	 * @param delegateWhenNoID
	 *            the matcher to delegate to when no ID is found.
	 */
	public IdentifierEObjectMatcher(IEObjectMatcher delegateWhenNoID) {
		this(delegateWhenNoID, new DefaultIDFunction());
	}

	/**
	 * Creates an ID based matcher computing the ID with the given function.
	 * 
	 * @param idComputation
	 *            the function used to compute the ID.
	 */
	public IdentifierEObjectMatcher(Function<EObject, String> idComputation) {
		this(null, idComputation);
	}

	/**
	 * Create an ID based matcher with a delegate which is going to be called when no ID is found for a given
	 * EObject. It is computing the ID with the given function
	 * 
	 * @param delegateWhenNoID
	 *            the delegate matcher to use when no ID is found.
	 * @param idComputation
	 *            the function used to compute the ID.
	 */
	public IdentifierEObjectMatcher(IEObjectMatcher delegateWhenNoID,
			Function<EObject, String> idComputation) {
		this.delegate = Optional.fromNullable(delegateWhenNoID);
		this.idComputation = idComputation;
	}

	/**
	 * {@inheritDoc}
	 */
	public void createMatches(Comparison comparison, Iterator<? extends EObject> leftEObjects,
			Iterator<? extends EObject> rightEObjects, Iterator<? extends EObject> originEObjects) {
		final List<EObject> leftEObjectsNoID = Lists.newArrayList();
		final List<EObject> rightEObjectsNoID = Lists.newArrayList();
		final List<EObject> originEObjectsNoID = Lists.newArrayList();

//		diagnostic = new BasicDiagnostic(Diagnostic.OK, DIAGNOSTIC_SOURCE, 0,
//				EMFCompareMessages.getString("IdentifierEObjectMatcher.diagnosticMessage"), null); //$NON-NLS-1$

		// TODO Change API to pass the monitor to matchPerId()
		final Set<Match> matches = matchPerId(leftEObjects, rightEObjects, originEObjects, leftEObjectsNoID,
				rightEObjectsNoID, originEObjectsNoID);

		Iterables.addAll(comparison.getMatches(), matches);

		if (!leftEObjectsNoID.isEmpty() || !rightEObjectsNoID.isEmpty() || !originEObjectsNoID.isEmpty()) {
			if (delegate.isPresent()) {
				doDelegation(comparison, leftEObjectsNoID, rightEObjectsNoID, originEObjectsNoID);
			} else {
				for (EObject eObject : leftEObjectsNoID) {
					Match match = CompareFactory.eINSTANCE.createMatch();
					match.setLeft(eObject);
					matches.add(match);
				}
				for (EObject eObject : rightEObjectsNoID) {
					Match match = CompareFactory.eINSTANCE.createMatch();
					match.setRight(eObject);
					matches.add(match);
				}
				for (EObject eObject : originEObjectsNoID) {
					Match match = CompareFactory.eINSTANCE.createMatch();
					match.setOrigin(eObject);
					matches.add(match);
				}
			}
		}
	}

	/**
	 * Execute matching process for the delegated IEObjectMatcher.
	 * 
	 * @param comparison
	 *            the comparison object that contains the matches
	 * @param monitor
	 *            the monitor instance to track the matching progress
	 * @param leftEObjectsNoID
	 *            remaining left objects after matching
	 * @param rightEObjectsNoID
	 *            remaining right objects after matching
	 * @param originEObjectsNoID
	 *            remaining origin objects after matching
	 */
	protected void doDelegation(Comparison comparison, final List<EObject> leftEObjectsNoID,
			final List<EObject> rightEObjectsNoID, final List<EObject> originEObjectsNoID) {
		delegate.get().createMatches(comparison, leftEObjectsNoID.iterator(), rightEObjectsNoID.iterator(),
				originEObjectsNoID.iterator());
	}

	/**
	 * Matches the EObject per ID.
	 * 
	 * @param leftEObjects
	 *            the objects to match (left side).
	 * @param rightEObjects
	 *            the objects to match (right side).
	 * @param originEObjects
	 *            the objects to match (origin side).
	 * @param leftEObjectsNoID
	 *            remaining left objects after matching
	 * @param rightEObjectsNoID
	 *            remaining right objects after matching
	 * @param originEObjectsNoID
	 *            remaining origin objects after matching
	 * @return the match built in the process.
	 */
	protected Set<Match> matchPerId(Iterator<? extends EObject> leftEObjects,
			Iterator<? extends EObject> rightEObjects, Iterator<? extends EObject> originEObjects,
			final List<EObject> leftEObjectsNoID, final List<EObject> rightEObjectsNoID,
			final List<EObject> originEObjectsNoID) {

		MatchComputationByID computation = new MatchComputationByID(leftEObjects, rightEObjects, originEObjects,
				leftEObjectsNoID, rightEObjectsNoID, originEObjectsNoID, idComputation, diagnostic);
		computation.compute();
		return computation.getMatches();
	}



	/**
	 * The default function used to retrieve IDs from EObject. You might want to extend or compose with it if
	 * you want to reuse its behavior.
	 */
	public static class DefaultIDFunction implements Function<EObject, String> {
		/**
		 * Return an ID for an EObject, null if not found.
		 * 
		 * @param eObject
		 *            The EObject for which we need an identifier.
		 * @return The identifier for that EObject if we could determine one. <code>null</code> if no
		 *         condition (see description above) is fulfilled for the given eObject.
		 */
		public String apply(EObject eObject) {
			final String identifier;
			if (eObject == null) {
				identifier = null;
			} else if (eObject.eIsProxy()) {
				identifier = ((InternalEObject)eObject).eProxyURI().fragment();
			} else {
				final Resource eObjectResource = eObject.eResource();
				final String xmiID;
				if (eObjectResource instanceof XMIResource) {
					xmiID = ((XMIResource)eObjectResource).getID(eObject);
				} else {
					xmiID = null;
				}
				if (xmiID != null) {
					identifier = xmiID;
				} else {
					identifier = EcoreUtil.getID(eObject);
				}
			}
			return identifier;
		}
	}
}






