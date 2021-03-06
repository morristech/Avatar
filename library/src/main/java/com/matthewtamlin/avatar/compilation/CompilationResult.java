package com.matthewtamlin.avatar.compilation;

import com.google.auto.value.AutoValue;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.util.Collection;

import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkEachElementIsNotNull;
import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

/**
 * The result of compiling one or more Java file objects.
 *
 * @see JavaFileObject
 */
@AutoValue
public abstract class CompilationResult {
	/**
	 * @return true if the compilation was successful, false otherwise
	 */
	public abstract boolean success();
	
	/**
	 * @return the diagnostics generated during compilation, may be empty, not null
	 */
	public abstract Collection<? extends Diagnostic<? extends JavaFileObject>> diagnostics();
	
	/**
	 * @return the files generated by compilation, may be empty, not null
	 */
	public abstract Collection<? extends JavaFileObject> generatedFiles();
	
	/**
	 * Creates a new CompilationResult.
	 *
	 * @param success
	 * 		whether or not compilation was successful
	 * @param diagnostics
	 * 		the diagnostics generated during compilation, not null, not containing null
	 * @param generatedFiles
	 * 		the files generated by compilation, not null, not containing null
	 *
	 * @return the new CompilationResult, not null
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code success} is null
	 * @throws IllegalArgumentException
	 * 		if {@code diagnostics} is null
	 * @throws IllegalArgumentException
	 * 		if {@code generatedFiles} is null
	 * @throws IllegalArgumentException
	 * 		if {@code diagnostics} contains null
	 * @throws IllegalArgumentException
	 * 		if {@code generatedFiles} contains null
	 */
	public static CompilationResult create(
			final boolean success,
			final Collection<? extends Diagnostic<? extends JavaFileObject>> diagnostics,
			final Collection<? extends JavaFileObject> generatedFiles) {
		
		checkNotNull(success, "Argument \'success\' cannot be null.");
		checkNotNull(diagnostics, "Argument \'diagnostics\' cannot be null.");
		checkNotNull(generatedFiles, "Argument \'generatedFiles\' cannot be null.");
		
		checkEachElementIsNotNull(diagnostics, "Argument \'diagnostics\' cannot contain null.");
		checkEachElementIsNotNull(generatedFiles, "Argument \'generatedFiles\' cannot be null.");
		
		return new AutoValue_CompilationResult(success, diagnostics, generatedFiles);
	}
}