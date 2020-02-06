/** 
 * Copyright (c) 2015, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.javaconverter

import com.google.inject.Inject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.jdt.core.dom.ASTParser
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtext.common.types.descriptions.ClasspathScanner

/** 
 * @author dhuebner - Initial contribution and API
 */
class ASTParserFactory {

	protected final String minParserApiLevel = "1.6"
	
	@Inject ClasspathScanner classpathScanner

	def final protected createDefaultJavaParser(String javaVersion) {
		var ASTParser parser
		val options = JavaCore.getOptions()
		try {
			parser = ASTParser.newParser(asJLS(javaVersion))
			JavaCore.setComplianceOptions(javaVersion, options)
		} catch (IllegalArgumentException e) {
			parser = ASTParser.newParser(asJLS(minParserApiLevel))
			JavaCore.setComplianceOptions(minParserApiLevel, options)
		}

		options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED)
		parser.compilerOptions = options
		parser.statementsRecovery = true
		parser.resolveBindings = true
		parser.bindingsRecovery = true
		return parser
	}

	def static int asJLS(String javaVersion) {
		switch (javaVersion) {
			case "1.7": 4
			case "1.8": 8
			case "11": 11
			// TODO update to 12 later
			case "12" : 11
			// TODO update to 13 later
			case "13" : 11
			default: 3
		}
	}

	/**
	 * @param context Contextual object from where to get the classpath entries (e.g. IProject or Module or <code>null</code>)
	 */
	def ASTParserWrapper createJavaParser(Object context) {
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

	@Data
	static class ASTParserWrapper {
		String targetLevel
		ASTParser parser

		def createAST() {
			parser.createAST(null)
		}

		def setKind(int i) {
			parser.kind = i
		}

		def setSource(char[] cs) {
			parser.source = cs
		}

		def setUnitName(String string) {
			parser.unitName = string
		}

	}
}
