/**
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.javaconverter;

import com.google.inject.Inject;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.xtend.core.javaconverter.ASTParserFactory;
import org.eclipse.xtext.common.types.descriptions.ClasspathScanner;

/**
 * @author dietrich - Initial contribution and API
 */
@SuppressWarnings("all")
public class ClasspathScannerASTParserFactory extends ASTParserFactory {
  @Inject
  private ClasspathScanner classpathScanner = new ClasspathScanner();
  
  /**
   * @param context Contextual object from where to get the classpath entries (e.g. IProject or Module or <code>null</code>)
   */
  @Override
  public ASTParserFactory.ASTParserWrapper createJavaParser(final Object context) {
    String targetJavaVersion = System.getProperty("java.specification.version");
    if ((targetJavaVersion == null)) {
      targetJavaVersion = this.minParserApiLevel;
    }
    final ASTParser parser = this.createDefaultJavaParser(targetJavaVersion);
    this.provideCustomEnvironment(parser);
    return new ASTParserFactory.ASTParserWrapper(targetJavaVersion, parser);
  }
  
  /**
   * Will be called when the environment can not be derived from a context in {@link #createJavaParser(Object)}
   * {@link ASTParser#setEnvironment(String[], String[], String[], boolean)}
   */
  protected void provideCustomEnvironment(final ASTParser parser) {
    final String[] cpEntries = this.classpathScanner.getSystemClasspath();
    parser.setEnvironment(cpEntries, null, null, true);
  }
}
