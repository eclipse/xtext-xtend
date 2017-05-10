/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.typesystem;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.core.jvmmodel.IXtendJvmAssociations;
import org.eclipse.xtend.core.tests.typesystem.AbstractTestingTypeReferenceOwner;
import org.eclipse.xtend.core.tests.typesystem.TestableExpressionArgumentFactory;
import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.typesystem.arguments.IFeatureCallArgumentSlot;
import org.eclipse.xtext.xbase.typesystem.arguments.IFeatureCallArguments;
import org.eclipse.xtext.xbase.typesystem.arguments.ReorderedFeatureCallArguments;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class ReorderedFeatureCallArgumentsTest extends AbstractTestingTypeReferenceOwner {
  @Inject
  @Extension
  private IXtendJvmAssociations _iXtendJvmAssociations;
  
  @Inject
  private TestableExpressionArgumentFactory factory;
  
  @Test
  public void test_01() {
    final IFeatureCallArguments arguments = this.toArgumentsWithoutReceiver("String s, int i", "[], 1");
    Assert.assertEquals(2, arguments.getArgumentCount());
    Assert.assertTrue(arguments.hasUnprocessedArguments());
    final IFeatureCallArgumentSlot firstSlot = arguments.getNextUnprocessedArgumentSlot();
    Assert.assertFalse(firstSlot.isVarArg());
    Assert.assertFalse(firstSlot.isSuperfluous());
    XExpression _argumentExpression = firstSlot.getArgumentExpression();
    Assert.assertTrue((_argumentExpression instanceof XNumberLiteral));
    Assert.assertEquals("int", firstSlot.getDeclaredType().getSimpleName());
    Assert.assertFalse(arguments.isProcessed(0));
    firstSlot.markProcessed();
    Assert.assertTrue(arguments.isProcessed(0));
    Assert.assertTrue(arguments.hasUnprocessedArguments());
    final IFeatureCallArgumentSlot secondSlot = arguments.getNextUnprocessedArgumentSlot();
    Assert.assertFalse(secondSlot.isVarArg());
    Assert.assertFalse(secondSlot.isSuperfluous());
    XExpression _argumentExpression_1 = secondSlot.getArgumentExpression();
    Assert.assertTrue((_argumentExpression_1 instanceof XClosure));
    Assert.assertEquals("String", secondSlot.getDeclaredType().getSimpleName());
    Assert.assertFalse(arguments.isProcessed(1));
    secondSlot.markProcessed();
    Assert.assertTrue(arguments.isProcessed(1));
    Assert.assertFalse(arguments.hasUnprocessedArguments());
  }
  
  @Test
  public void test_02() {
    final IFeatureCallArguments arguments = this.toArgumentsWithReceiver("String s, int i", "[], 1");
    Assert.assertEquals(3, arguments.getArgumentCount());
    Assert.assertTrue(arguments.hasUnprocessedArguments());
    final IFeatureCallArgumentSlot firstSlot = arguments.getNextUnprocessedArgumentSlot();
    Assert.assertFalse(firstSlot.isVarArg());
    Assert.assertFalse(firstSlot.isSuperfluous());
    XExpression _argumentExpression = firstSlot.getArgumentExpression();
    Assert.assertTrue((_argumentExpression instanceof XNumberLiteral));
    Assert.assertEquals("int", firstSlot.getDeclaredType().getSimpleName());
    Assert.assertTrue(arguments.isProcessed(0));
    Assert.assertFalse(arguments.isProcessed(1));
    firstSlot.markProcessed();
    Assert.assertTrue(arguments.isProcessed(1));
    Assert.assertTrue(arguments.hasUnprocessedArguments());
    final IFeatureCallArgumentSlot secondSlot = arguments.getNextUnprocessedArgumentSlot();
    Assert.assertFalse(secondSlot.isVarArg());
    Assert.assertFalse(secondSlot.isSuperfluous());
    XExpression _argumentExpression_1 = secondSlot.getArgumentExpression();
    Assert.assertTrue((_argumentExpression_1 instanceof XClosure));
    Assert.assertEquals("String", secondSlot.getDeclaredType().getSimpleName());
    Assert.assertFalse(arguments.isProcessed(2));
    secondSlot.markProcessed();
    Assert.assertTrue(arguments.isProcessed(2));
    Assert.assertFalse(arguments.hasUnprocessedArguments());
  }
  
  @Test
  public void test_03() {
    final IFeatureCallArguments arguments = this.toArgumentsWithoutReceiver("String s, int i", "[], 1");
    this.withIndizes(arguments, 1, 0);
  }
  
  @Test
  public void test_04() {
    final IFeatureCallArguments arguments = this.toArgumentsWithoutReceiver("String s, int i, int j, int k, int l, int m", "[], 1, [], 1, []");
    this.withIndizes(arguments, 1, 3, 0, 2, 4);
  }
  
  @Test
  public void test_05() {
    final IFeatureCallArguments arguments = this.toArgumentsWithoutReceiver("String s, int i, int j, int k", "[], 1, [], 1, [], 1");
    this.withIndizes(arguments, 1, 3, 0, 2, 4, 5);
  }
  
  @Test
  public void test_06() {
    final IFeatureCallArguments arguments = this.toArgumentsWithoutReceiver("String s, int i, int j", "[], 1, [], 1, [], 1");
    this.withIndizes(arguments, 1, 0, 2, 3, 4, 5);
  }
  
  @Test
  public void test_07() {
    final IFeatureCallArguments arguments = this.toArgumentsWithoutReceiver("String s, int i, long j, boolean k, float l, double m", "[], 1, [], 1, []");
    this.withTypes(arguments, "int", "boolean", "String", "long", "float");
  }
  
  @Test
  public void test_08() {
    final IFeatureCallArguments arguments = this.toArgumentsWithoutReceiver("String s, int i, long j, boolean k", "[], 1, [], 1, [], 1");
    this.withTypes(arguments, "int", "boolean", "String", "long", null, null);
  }
  
  @Test
  public void test_09() {
    final IFeatureCallArguments arguments = this.toArgumentsWithoutReceiver("String s, int i, long j", "[], 1, [], 1, [], 1");
    this.withTypes(arguments, "int", "String", "long", null, null, null);
  }
  
  @Test
  public void testBug457779_01() {
    final IFeatureCallArguments arguments = this.toArgumentsWithReceiver("String s, int i", "[], 1");
    final XExpression first = arguments.getArgument(0);
    Assert.assertNull(first);
    final LightweightTypeReference firstType = arguments.getDeclaredTypeForLambda(0);
    Assert.assertNull(firstType);
    final XExpression third = arguments.getArgument(1);
    Assert.assertTrue((third instanceof XNumberLiteral));
    final LightweightTypeReference thirdType = arguments.getDeclaredTypeForLambda(1);
    Assert.assertEquals("int", thirdType.getSimpleName());
    final XExpression second = arguments.getArgument(2);
    Assert.assertTrue((second instanceof XClosure));
    final LightweightTypeReference secondType = arguments.getDeclaredTypeForLambda(2);
    Assert.assertEquals("String", secondType.getSimpleName());
    try {
      arguments.getArgument(3);
      Assert.fail("Expected exception");
    } catch (final Throwable _t) {
      if (_t instanceof IndexOutOfBoundsException) {
        final IndexOutOfBoundsException expected = (IndexOutOfBoundsException)_t;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    try {
      arguments.getDeclaredTypeForLambda(3);
      Assert.fail("Expected exception");
    } catch (final Throwable _t_1) {
      if (_t_1 instanceof IndexOutOfBoundsException) {
        final IndexOutOfBoundsException expected_1 = (IndexOutOfBoundsException)_t_1;
      } else {
        throw Exceptions.sneakyThrow(_t_1);
      }
    }
  }
  
  @Test
  public void testBug457779_02() {
    final IFeatureCallArguments arguments = this.toArgumentsWithoutReceiver("String s, int i", "[], 1");
    final XExpression second = arguments.getArgument(0);
    Assert.assertTrue((second instanceof XNumberLiteral));
    final LightweightTypeReference secondType = arguments.getDeclaredTypeForLambda(0);
    Assert.assertEquals("int", secondType.getSimpleName());
    final XExpression first = arguments.getArgument(1);
    Assert.assertTrue((first instanceof XClosure));
    final LightweightTypeReference firstType = arguments.getDeclaredTypeForLambda(1);
    Assert.assertEquals("String", firstType.getSimpleName());
    try {
      arguments.getArgument(2);
      Assert.fail("Expected exception");
    } catch (final Throwable _t) {
      if (_t instanceof IndexOutOfBoundsException) {
        final IndexOutOfBoundsException expected = (IndexOutOfBoundsException)_t;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    try {
      arguments.getDeclaredTypeForLambda(2);
      Assert.fail("Expected exception");
    } catch (final Throwable _t_1) {
      if (_t_1 instanceof IndexOutOfBoundsException) {
        final IndexOutOfBoundsException expected_1 = (IndexOutOfBoundsException)_t_1;
      } else {
        throw Exceptions.sneakyThrow(_t_1);
      }
    }
  }
  
  protected void withIndizes(final IFeatureCallArguments arguments, final int... indexes) {
    final Consumer<Integer> _function = (Integer it) -> {
      Assert.assertTrue(arguments.hasUnprocessedArguments());
      final IFeatureCallArgumentSlot slot = arguments.getNextUnprocessedArgumentSlot();
      final XExpression expression = slot.getArgumentExpression();
      EObject _eContainer = expression.eContainer();
      final XFeatureCall featureCall = ((XFeatureCall) _eContainer);
      Assert.assertEquals((it).intValue(), featureCall.getFeatureCallArguments().indexOf(expression));
      slot.markProcessed();
    };
    ((List<Integer>)Conversions.doWrapArray(indexes)).forEach(_function);
    Assert.assertFalse(arguments.hasUnprocessedArguments());
  }
  
  protected void withTypes(final IFeatureCallArguments arguments, final String... types) {
    final Consumer<String> _function = (String it) -> {
      Assert.assertTrue(arguments.hasUnprocessedArguments());
      final IFeatureCallArgumentSlot slot = arguments.getNextUnprocessedArgumentSlot();
      Assert.assertEquals(Boolean.valueOf((it == null)), Boolean.valueOf(slot.isSuperfluous()));
      if ((it != null)) {
        Assert.assertEquals(it, slot.getDeclaredType().getSimpleName());
      }
      slot.markProcessed();
    };
    ((List<String>)Conversions.doWrapArray(types)).forEach(_function);
    Assert.assertFalse(arguments.hasUnprocessedArguments());
  }
  
  protected IFeatureCallArguments toArgumentsWithoutReceiver(final String signature, final String invocation) {
    return this.toArguments(signature, invocation, false);
  }
  
  protected IFeatureCallArguments toArgumentsWithReceiver(final String signature, final String invocation) {
    return this.toArguments(signature, invocation, true);
  }
  
  protected IFeatureCallArguments toArguments(final String signature, final String invocation, final boolean receiver) {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("def void m(");
      _builder.append(signature);
      _builder.append(") {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("m(");
      _builder.append(invocation, "\t");
      _builder.append(")");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final String functionString = _builder.toString();
      final XtendFunction function = this.function(functionString);
      XExpression _expression = function.getExpression();
      final XBlockExpression body = ((XBlockExpression) _expression);
      XExpression _head = IterableExtensions.<XExpression>head(body.getExpressions());
      final XFeatureCall featureCall = ((XFeatureCall) _head);
      final EList<XExpression> arguments = featureCall.getFeatureCallArguments();
      final JvmOperation operation = this._iXtendJvmAssociations.getDirectlyInferredOperation(function);
      final IFeatureCallArguments result = this.factory.createStandardArguments(arguments, operation.getParameters(), receiver, this.getOwner());
      Class<? extends IFeatureCallArguments> _class = result.getClass();
      boolean _equals = Objects.equal(_class, ReorderedFeatureCallArguments.class);
      Assert.assertTrue(_equals);
      return result;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
