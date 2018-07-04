/**
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.typesystem;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend.core.tests.AbstractXtendTestCase;
import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XTryCatchFinallyExpression;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Test;

/**
 * @author epoell - Initial contribution and API
 */
@SuppressWarnings("all")
public class TryWithResourcesTest extends AbstractXtendTestCase {
  private final String input = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("def normalTry(String path) {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("val br = new BufferedReader(new FileReader(path));");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//TryCatchFinallyExpression without Resources");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("try");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return br.readLine()");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("finally");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if(br !== null)");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("br.close()");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  private final String input2 = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("def foo(String path) {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("try (val fr = new FileReader(path); val buffy = new BufferedReader(fr)) ");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("buffy.readLine()");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("finally");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("fr.close()");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  private final XBlockExpression block = new Function0<XBlockExpression>() {
    public XBlockExpression apply() {
      try {
        XtendFunction _function = TryWithResourcesTest.this.function(TryWithResourcesTest.this.input);
        return ((XBlockExpression) _function);
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
  }.apply();
  
  private final EList<XExpression> expressions = this.block.getExpressions();
  
  @Test
  public void internalLinking_Types() {
    for (final XExpression exp : this.expressions) {
      if (((exp instanceof XTryCatchFinallyExpression) && IterableExtensions.isNullOrEmpty(((XTryCatchFinallyExpression) exp).getResources()))) {
      }
    }
    final Object typeFr = null;
    final Object typeBuffy = null;
  }
}
