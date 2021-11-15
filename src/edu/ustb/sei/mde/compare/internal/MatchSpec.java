package edu.ustb.sei.mde.compare.internal;

import org.eclipse.emf.ecore.EObject;

import edu.ustb.sei.mde.compare.Comparison;
import edu.ustb.sei.mde.compare.Match;
import edu.ustb.sei.mde.compare.impl.MatchImpl;

/**
 * This specialization of the {@link MatchImpl} class allows us to define the derived features and operations
 * implementations.
 * 
 * @author <a href="mailto:mikael.barbero@obeo.fr">Mikael Barbero</a>
 */
public class MatchSpec extends MatchImpl {
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.impl.MatchImpl#getComparison()
	 */
	@Override
	public Comparison getComparison() {
		Comparison ret = null;

		EObject container = eContainer();
		while (!(container instanceof Comparison) && container != null) {
			container = container.eContainer();
		}

		if (container != null) {
			ret = (Comparison)container;
		}

		return ret;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.impl.MatchImpl#getAllSubmatches()
	 */
	@Override
	public Iterable<Match> getAllSubmatches() {
		return new SubMatchIterable(this);
	}

	/**
	 * Returns whether the given object is the same object as the {@link #left}, {@link #right}, or
	 * {@link #origin}. It is used by {@link EqualityHelper#matchingValues(Object, Object)} and
	 * {@link EqualityHelper#matchingValues(EObject, EObject)}.
	 * 
	 * @param object
	 *            the object in question
	 * @return whether the given object is the same object as the left, right, or origin.
	 */
	public boolean matches(Object object) {
		return object == left || object == right || object == origin;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#toString()
	 */
//	@SuppressWarnings("nls")
//	@Override
//	public String toString() {
//		// @formatter:off
//		return Objects.toStringHelper(this).add("left", EObjectUtil.getLabel(getLeft()))
//				.add("right", EObjectUtil.getLabel(getRight()))
//				.add("origin", EObjectUtil.getLabel(getOrigin()))
//				.add("#differences", Integer.valueOf(getDifferences().size()))
//				.add("#submatches", Integer.valueOf(getSubmatches().size())).toString();
//		// @formatter:on
//	}
}

