package edu.ustb.sei.mde.compare.util;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import edu.ustb.sei.mde.compare.Comparison;
import edu.ustb.sei.mde.compare.Match;
import edu.ustb.sei.mde.compare.diff.DifferenceSource;

/**
 * This utility class provides common methods for navigation over and querying of the Comparison model.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class ComparisonUtil {

	/** Hides default constructor. */
	private ComparisonUtil() {
		// prevents instantiation
	}

	/**
	 * Determines the side of the given {@link Match} which represents the model state the other side will be
	 * changed to.
	 * 
	 * @param match
	 *            The match whose side is returned.
	 * @param source
	 *            The source from which side the differences are determined.
	 * @param mergeRightToLeft
	 *            The direction of the merge.
	 * @return The side of the given {@code match} which represents the desired model state in regards to the
	 *         given {@link DifferenceSource} and {@code MergeDirection}.
	 */
	public static EObject getExpectedSide(Match match, DifferenceSource source, boolean mergeRightToLeft) {
		EObject result = null;
		// Bug 458818: prevent NPE if match is null
		if (match != null) {
			final Comparison comparison = match.getComparison();
			if (comparison.isThreeWay() && mergeRightToLeft == (source == DifferenceSource.LEFT)
					&& match.getOrigin() != null) {
				result = match.getOrigin();
			} else if (mergeRightToLeft) {
				result = match.getRight();
			} else {
				result = match.getLeft();
			}
		}
		return result;
	}

	/**
	 * Determines if the given {@link EObject} is contained directly within a FeatureMap by checking the
	 * {@link EAnnotation}s.
	 *
	 * @param object
	 *            The object to check.
	 * @return {@true} if the {@code object} is directly contained within a FeatureMap.
	 */
	public static boolean isContainedInFeatureMap(EObject object) {
		final EAnnotation annotation = object.eContainingFeature()
				.getEAnnotation(ExtendedMetaData.ANNOTATION_URI);
		if (annotation != null) {
			final String groupKind = ExtendedMetaData.FEATURE_KINDS[ExtendedMetaData.GROUP_FEATURE];
			return annotation.getDetails().containsKey(groupKind);
		}
		return false;
	}

	/**
	 * Checks if both resources are platform resources and only one exists.
	 * 
	 * @param leftResource
	 *            the first resource to check.
	 * @param rightResource
	 *            the second resource to check.
	 * @return true if both resources are platform resources and only one exists, false otherwise.
	 */
	public static boolean bothArePlatformResourcesAndOnlyOneExists(Resource leftResource,
			Resource rightResource) {
		boolean existingPlatformResources = false;
		if (leftResource != null && rightResource != null) {
			final ResourceSet leftResourceSet = leftResource.getResourceSet();
			final ResourceSet rightResourceSet = rightResource.getResourceSet();
			if (leftResourceSet != null && rightResourceSet != null) {
				final URI leftURI = leftResource.getURI();
				final URI rightURI = rightResource.getURI();
				if (leftURI.isPlatformResource() && rightURI.isPlatformResource()) {
					boolean baseExists = leftResourceSet.getURIConverter().exists(leftURI,
							Collections.emptyMap());
					boolean changedExists = rightResourceSet.getURIConverter().exists(rightURI,
							Collections.emptyMap());
					existingPlatformResources = (baseExists && !changedExists)
							|| (!baseExists && changedExists);
				}
			}
		}
		return existingPlatformResources;
	}

	/**
	 * Checks if both resources have resource set.
	 * 
	 * @param leftResource
	 *            the first resource to check.
	 * @param rightResource
	 *            the second resource to check.
	 * @return true if both resources have resource set, false otherwise.
	 */
	public static boolean bothResourceHaveResourceSet(Resource leftResource, Resource rightResource) {
		if (leftResource != null && rightResource != null) {
			final ResourceSet leftResourceSet = leftResource.getResourceSet();
			final ResourceSet rightResourceSet = rightResource.getResourceSet();
			if (leftResourceSet != null && rightResourceSet != null) {
				return true;
			}
		}
		return false;
	}
}

