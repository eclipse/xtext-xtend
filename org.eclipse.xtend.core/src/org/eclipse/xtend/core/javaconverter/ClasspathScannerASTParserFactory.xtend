/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.javaconverter

import com.google.inject.Inject
import org.eclipse.jdt.core.dom.ASTParser
import org.eclipse.xtext.common.types.descriptions.ClasspathScanner

/**
 * @author dietrich - Initial contribution and API
 */
class ClasspathScannerASTParserFactory extends ASTParserFactory {
	
	@Inject
	ClasspathScanner classpathScanner = new ClasspathScanner()
	
	/**
	 * @param context Contextual object from where to get the classpath entries (e.g. IProject or Module or <code>null</code>)
	 */
	override ASTParserWrapper createJavaParser(Object context) {
		var targetJavaVersion = System.getProperty("java.specification.version");
		if (targetJavaVersion === null) {
			targetJavaVersion = minParserApiLevel
		}
		val parser = createDefaultJavaParser(targetJavaVersion)
		provideCustomEnvironment(parser)
		return new ASTParserWrapper(targetJavaVersion, parser)
	}
	
	/**
	 * Will be called when the environment can not be derived from a context in {@link #createJavaParser(Object)} 
	 * {@link ASTParser#setEnvironment(String[], String[], String[], boolean)}
	 */
	protected def provideCustomEnvironment(ASTParser parser) {
		val String[] cpEntries = classpathScanner.getSystemClasspath()
		parser.setEnvironment(cpEntries, null, null, true)
	}
	
}