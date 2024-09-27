package edu.ustb.sei.mde.nmc.compare;

/**
 * A factory that instantiate IEqualityHelper.
 * 
 * @author <a href="mailto:mikael.barbero@obeo.fr">Mikael Barbero</a>
 */
public interface IEqualityHelperFactory {

	/**
	 * Returns a new {@link IEqualityHelper}.
	 * 
	 * @return a new {@link IEqualityHelper}.
	 */
	IEqualityHelper createEqualityHelper();

}

