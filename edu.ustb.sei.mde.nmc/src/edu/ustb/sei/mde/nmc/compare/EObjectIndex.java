package edu.ustb.sei.mde.nmc.compare;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;

/**
 * An EObjectIndex has for responsability to store/remove EObjects and return the closest EObject from another
 * one (each one being registered with a different Side.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public interface EObjectIndex {
	/**
	 * return the list of EObjects of a given side still available in the index.
	 * 
	 * @param side
	 *            the side we are looking for.
	 * @return the list of EObjects of a given side still available in the index.
	 */
	Iterable<EObject> getValuesStillThere(Side side);

	/**
	 * Remove an object from the index.
	 * 
	 * @param eObj
	 *            object to remove.
	 * @param side
	 *            Side in which this object was.
	 */
	void remove(EObject eObj, Side side);

	/**
	 * Register an Object in the index with the given side.
	 * 
	 * @param eObj
	 *            the {@link EObject} to register.
	 * @param side
	 *            the side in which it should be registered.
	 */
	void index(EObject eObj, Side side);
		
	// lyt
	Set<EObject> getLefts(EObject obj);
	
	Set<EObject> getRights(EObject obj);
	
	Set<EObject> getOrigins(EObject obj);
	
	
	/**
	 * An enumeration used in the API to specify sides.
	 */
	enum Side {
		/**
		 * the left side.
		 */
		LEFT,
		/**
		 * The right side.
		 */
		RIGHT,
		/**
		 * The origin side (also known as ancestor).
		 */
		ORIGIN
	}

}

