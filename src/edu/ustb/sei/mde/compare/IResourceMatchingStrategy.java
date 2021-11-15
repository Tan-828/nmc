package edu.ustb.sei.mde.compare;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * A {@link StrategyResourceMatcher} will be used to match two or three {@link Resource}s together; depending
 * on whether we are doing a two or three way comparison.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public interface IResourceMatchingStrategy {
	/**
	 * This will be called by the resource matcher in order to determine the matching between <i>n</i>
	 * resources.
	 * 
	 * @param left
	 *            Resources we are to match in the left.
	 * @param right
	 *            Resources we are to match in the right.
	 * @param origin
	 *            Resources we are to match in the origin.
	 * @return The list of mappings this strategy managed to determine.
	 */
	List<MatchResource> matchResources(Iterable<? extends Resource> left, Iterable<? extends Resource> right,
			Iterable<? extends Resource> origin);
}
