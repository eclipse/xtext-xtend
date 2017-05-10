/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.javaconverter;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Hashtable;
import java.util.List;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * @author dhuebner - Initial contribution and API
 */
@SuppressWarnings("all")
public class ASTParserFactory {
  @Data
  public static class ASTParserWrapper {
    private final String targetLevel;
    
    private final ASTParser parser;
    
    public ASTNode createAST() {
      return this.parser.createAST(null);
    }
    
    public void setKind(final int i) {
      this.parser.setKind(i);
    }
    
    public void setSource(final char[] cs) {
      this.parser.setSource(cs);
    }
    
    public void setUnitName(final String string) {
      this.parser.setUnitName(string);
    }
    
    public ASTParserWrapper(final String targetLevel, final ASTParser parser) {
      super();
      this.targetLevel = targetLevel;
      this.parser = parser;
    }
    
    @Override
    @Pure
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.targetLevel== null) ? 0 : this.targetLevel.hashCode());
      result = prime * result + ((this.parser== null) ? 0 : this.parser.hashCode());
      return result;
    }
    
    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      ASTParserFactory.ASTParserWrapper other = (ASTParserFactory.ASTParserWrapper) obj;
      if (this.targetLevel == null) {
        if (other.targetLevel != null)
          return false;
      } else if (!this.targetLevel.equals(other.targetLevel))
        return false;
      if (this.parser == null) {
        if (other.parser != null)
          return false;
      } else if (!this.parser.equals(other.parser))
        return false;
      return true;
    }
    
    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("targetLevel", this.targetLevel);
      b.add("parser", this.parser);
      return b.toString();
    }
    
    @Pure
    public String getTargetLevel() {
      return this.targetLevel;
    }
    
    @Pure
    public ASTParser getParser() {
      return this.parser;
    }
  }
  
  protected final String minParserApiLevel = "1.6";
  
  protected final ASTParser createDefaultJavaParser(final String javaVersion) {
    ASTParser parser = null;
    final Hashtable<String, String> options = JavaCore.getOptions();
    try {
      parser = ASTParser.newParser(ASTParserFactory.asJLS(javaVersion));
      JavaCore.setComplianceOptions(javaVersion, options);
    } catch (final Throwable _t) {
      if (_t instanceof IllegalArgumentException) {
        final IllegalArgumentException e = (IllegalArgumentException)_t;
        parser = ASTParser.newParser(ASTParserFactory.asJLS(this.minParserApiLevel));
        JavaCore.setComplianceOptions(this.minParserApiLevel, options);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);
    parser.setCompilerOptions(options);
    parser.setStatementsRecovery(true);
    parser.setResolveBindings(true);
    parser.setBindingsRecovery(true);
    return parser;
  }
  
  public static int asJLS(final String javaVersion) {
    int _switchResult = (int) 0;
    if (javaVersion != null) {
      switch (javaVersion) {
        case "1.7":
          _switchResult = 4;
          break;
        case "1.8":
          _switchResult = 8;
          break;
        default:
          _switchResult = 3;
          break;
      }
    } else {
      _switchResult = 3;
    }
    return _switchResult;
  }
  
  /**
   * @param context Contextual object from where to get the classpath entries (e.g. IProject or Module or <code>null</code>)
   */
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
    final ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
    final Function1<URL, String> _function = (URL it) -> {
      return it.getFile();
    };
    final Function1<String, Boolean> _function_1 = (String it) -> {
      return Boolean.valueOf(new File(it).exists());
    };
    final Iterable<String> cpEntries = IterableExtensions.<String>filter(ListExtensions.<URL, String>map(((List<URL>)Conversions.doWrapArray(((URLClassLoader) sysClassLoader).getURLs())), _function), _function_1);
    parser.setEnvironment(((String[])Conversions.unwrapArray(cpEntries, String.class)), null, null, true);
  }
}
