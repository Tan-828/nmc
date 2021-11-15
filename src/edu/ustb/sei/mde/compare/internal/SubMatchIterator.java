package edu.ustb.sei.mde.compare.internal;

import java.util.Iterator;

import org.eclipse.emf.common.util.AbstractTreeIterator;

import edu.ustb.sei.mde.compare.Match;

/**
 * A custom TreeIterator that will iterate over the Match->submatch tree.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class SubMatchIterator extends AbstractTreeIterator<Match> {
	/** Generated SUID. */
	private static final long serialVersionUID = -1789806135599824529L;

	/**
	 * Constructs an iterator given the root of its tree.
	 * 
	 * @param start
	 *            Starting match of the tree we'll iterate over.
	 */
	public SubMatchIterator(Match start) {
		super(start, false);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.common.util.AbstractTreeIterator#getChildren(java.lang.Object)
	 */
	@Override
	protected Iterator<? extends Match> getChildren(Object obj) {
		return ((Match)obj).getSubmatches().iterator();
	}
}

