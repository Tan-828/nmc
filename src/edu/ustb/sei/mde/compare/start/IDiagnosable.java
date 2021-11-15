package edu.ustb.sei.mde.compare.start;

import org.eclipse.emf.common.util.Diagnostic;

/**
 * An element that can hold a diagnostic.
 * 
 * @author <a href="mailto:mikael.barbero@obeo.fr">Mikael Barbero</a>
 */
public interface IDiagnosable {

	/**
	 * Return the diagnostic associated with this scope. For instance, it may contain errors that occurred
	 * during loading of its notifiers.
	 * 
	 * @return the diagnostic
	 */
	Diagnostic getDiagnostic();

	/**
	 * Set the diagnostic to be associated with this scope.
	 * 
	 * @param diagnostic
	 *            the diagnostic
	 */
	void setDiagnostic(Diagnostic diagnostic);
}
