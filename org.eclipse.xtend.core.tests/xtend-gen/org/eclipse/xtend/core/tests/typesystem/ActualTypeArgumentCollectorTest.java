/**
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.typesystem;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend.core.jvmmodel.IXtendJvmAssociations;
import org.eclipse.xtend.core.tests.typesystem.AbstractTestingTypeReferenceOwner;
import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.util.Triple;
import org.eclipse.xtext.util.Tuples;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.eclipse.xtext.xbase.typesystem.references.ITypeReferenceOwner;
import org.eclipse.xtext.xbase.typesystem.references.LightweightBoundTypeArgument;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import org.eclipse.xtext.xbase.typesystem.util.ActualTypeArgumentCollector;
import org.eclipse.xtext.xbase.typesystem.util.BoundTypeArgumentSource;
import org.eclipse.xtext.xbase.typesystem.util.VarianceInfo;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow
 */
@SuppressWarnings("all")
public class ActualTypeArgumentCollectorTest extends AbstractTestingTypeReferenceOwner {
  @Inject
  @Extension
  private IXtendJvmAssociations _iXtendJvmAssociations;
  
  public Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> mappedBy(final String typeParameters, final String... alternatingTypeReferences) {
    final JvmOperation operation = this.operation(typeParameters, alternatingTypeReferences);
    EList<JvmTypeParameter> _typeParameters = operation.getTypeParameters();
    ITypeReferenceOwner _owner = this.getOwner();
    final ActualTypeArgumentCollector collector = new ActualTypeArgumentCollector(_typeParameters, BoundTypeArgumentSource.INFERRED, _owner);
    int _size = ((List<String>)Conversions.doWrapArray(alternatingTypeReferences)).size();
    int _minus = (_size - 1);
    IntegerRange _withStep = new IntegerRange(0, _minus).withStep(2);
    for (final Integer i : _withStep) {
      collector.populateTypeParameterMapping(this.toLightweightTypeReference(operation.getParameters().get((i).intValue()).getParameterType()), this.toLightweightTypeReference(operation.getParameters().get(((i).intValue() + 1)).getParameterType()));
    }
    return collector.getTypeParameterMapping();
  }
  
  public Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> assertMapping(final Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> mapping, final String typeParamName, final Triple<String, VarianceInfo, VarianceInfo>... mappedTypes) {
    final Set<JvmTypeParameter> allKeys = mapping.keySet();
    for (final JvmTypeParameter key : allKeys) {
      String _simpleName = key.getSimpleName();
      boolean _equals = Objects.equal(_simpleName, typeParamName);
      if (_equals) {
        Assert.assertNotNull(mappedTypes);
        final List<LightweightBoundTypeArgument> mappingData = mapping.get(key);
        final Function1<LightweightBoundTypeArgument, String> _function = (LightweightBoundTypeArgument it) -> {
          StringConcatenation _builder = new StringConcatenation();
          LightweightTypeReference _typeReference = it.getTypeReference();
          _builder.append(_typeReference);
          _builder.append("(");
          VarianceInfo _declaredVariance = it.getDeclaredVariance();
          _builder.append(_declaredVariance);
          _builder.append("/");
          VarianceInfo _actualVariance = it.getActualVariance();
          _builder.append(_actualVariance);
          _builder.append(")");
          return _builder.toString();
        };
        Assert.assertEquals(ListExtensions.<LightweightBoundTypeArgument, String>map(mappingData, _function).toString(), ((List<Triple<String, VarianceInfo, VarianceInfo>>)Conversions.doWrapArray(mappedTypes)).size(), mappingData.size());
        final Function1<LightweightBoundTypeArgument, Triple<String, VarianceInfo, VarianceInfo>> _function_1 = (LightweightBoundTypeArgument it) -> {
          return Tuples.<String, VarianceInfo, VarianceInfo>create(it.getTypeReference().toString(), it.getDeclaredVariance(), it.getActualVariance());
        };
        Assert.assertEquals(IterableExtensions.<Triple<String, VarianceInfo, VarianceInfo>>toList(((Iterable<Triple<String, VarianceInfo, VarianceInfo>>)Conversions.doWrapArray(mappedTypes))), IterableExtensions.<Triple<String, VarianceInfo, VarianceInfo>>toList(ListExtensions.<LightweightBoundTypeArgument, Triple<String, VarianceInfo, VarianceInfo>>map(mappingData, _function_1)));
        return mapping;
      }
    }
    if ((mappedTypes != null)) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("No mapping for ");
      _builder.append(typeParamName);
      _builder.append(" in ");
      final Function1<JvmTypeParameter, String> _function_2 = (JvmTypeParameter it) -> {
        return it.getSimpleName();
      };
      Iterable<String> _map = IterableExtensions.<JvmTypeParameter, String>map(mapping.keySet(), _function_2);
      _builder.append(_map);
      Assert.fail(_builder.toString());
    }
    return mapping;
  }
  
  public Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> assertOrigins(final Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> mapping, final String typeParamName, final int count) {
    final Set<JvmTypeParameter> allKeys = mapping.keySet();
    for (final JvmTypeParameter key : allKeys) {
      String _simpleName = key.getSimpleName();
      boolean _equals = Objects.equal(_simpleName, typeParamName);
      if (_equals) {
        final List<LightweightBoundTypeArgument> mappingData = mapping.get(key);
        final Function1<LightweightBoundTypeArgument, Object> _function = (LightweightBoundTypeArgument it) -> {
          return it.getOrigin();
        };
        Assert.assertEquals(count, IterableExtensions.<Object>toSet(ListExtensions.<LightweightBoundTypeArgument, Object>map(mappingData, _function)).size());
        return mapping;
      }
    }
    return mapping;
  }
  
  protected JvmOperation operation(final String typeParameters, final String... alternatingTypeReferences) {
    try {
      JvmOperation _xblockexpression = null;
      {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("def ");
        {
          boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(typeParameters);
          boolean _not = (!_isNullOrEmpty);
          if (_not) {
            _builder.append("<");
            _builder.append(typeParameters);
            _builder.append(">");
          }
        }
        _builder.append(" void method(");
        final Function1<String, CharSequence> _function = (String it) -> {
          return it;
        };
        String _join = IterableExtensions.<String>join(((Iterable<String>)Conversions.doWrapArray(alternatingTypeReferences)), null, " p, ", " p", _function);
        _builder.append(_join);
        _builder.append(") {}");
        final String signature = _builder.toString();
        final XtendFunction function = this.function(signature.toString());
        final JvmOperation operation = this._iXtendJvmAssociations.getDirectlyInferredOperation(function);
        _xblockexpression = operation;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public Triple<String, VarianceInfo, VarianceInfo> operator_mappedTo(final Pair<String, VarianceInfo> pair, final VarianceInfo third) {
    return Tuples.<String, VarianceInfo, VarianceInfo>create(pair.getKey(), pair.getValue(), third);
  }
  
  @Test
  public void testNoParams_01() {
    Assert.assertTrue(this.mappedBy("", "String", "String").isEmpty());
  }
  
  @Test
  public void testNoParams_02() {
    Assert.assertTrue(this.mappedBy("", "java.util.List<?>", "java.util.ArrayList<String>").isEmpty());
  }
  
  @Test
  public void testUnusedParam() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Object", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUnusedParams_01() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T, T2", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Object", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("Object", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testUnusedParams_02() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends CharSequence, T2", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("Object", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testUnusedParams_03() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends CharSequence, T2 extends T", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testUnusedParams_04() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends T2, T2", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Object", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("Object", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testUnusedParams_05() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends T2, T2 extends CharSequence", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Object", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testUnambiguousMapping_01() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "java.util.List<T>", "java.util.List<String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUnambiguousMapping_02() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "java.util.Map<T, T>", "java.util.Map<String, String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1, _mappedTo_3);
  }
  
  @Test
  public void testUnambiguousMapping_03() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("K, V", "java.util.Map<K, V>", "java.util.Map<String, Integer>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "K", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("Integer", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.INVARIANT);
    this.assertMapping(_assertMapping, "V", _mappedTo_3);
  }
  
  @Test
  public void testUnambiguousMapping_04() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "T", "CharSequence", "T", "CharSequence");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1, _mappedTo_3);
  }
  
  @Test
  public void testUnambiguousMapping_05() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T, T2", "T", "CharSequence", "T2", "CharSequence");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testUsedTwice_01() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "T", "CharSequence", "T", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1, _mappedTo_3);
  }
  
  @Test
  public void testUsedTwice_02() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "T", "String", "T", "CharSequence");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1, _mappedTo_3);
  }
  
  @Test
  public void testBestEffortMapping_01() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "java.util.List<T>", "java.util.Set<String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testBestEffortMapping_02() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "java.util.ArrayList<T>", "java.util.HashSet<String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testBestEffortMapping_03() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "java.util.ArrayList<T>", "Iterable<String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testBestEffortMapping_04() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "java.util.HashMap<java.util.ArrayList<T>, java.util.ArrayList<T>>", "java.util.Map<Iterable<String>, java.util.HashSet<Integer>>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("Integer", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1, _mappedTo_3);
  }
  
  @Test
  public void testBestEffortMapping_05() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "org.eclipse.xtend.core.tests.typesystem.MapType<T>", "java.util.HashMap<String, Integer>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("Integer", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1, _mappedTo_3);
  }
  
  @Test
  public void testInheritanceMapping_01() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("E", "Iterable<E>", "java.util.ArrayList<String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "E", _mappedTo_1);
  }
  
  @Test
  public void testInheritanceMapping_02() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("C", "Comparable<C>", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "C", _mappedTo_1);
  }
  
  @Test
  public void testMappedGenericType_01() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<T>", "Iterable<Iterable<String>>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Iterable<String>", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testMappedArray_01() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "T[]", "String[]");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testMappedArray_02() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "T[]", "String[][]");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String[]", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUpperBound_01() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<? extends T>", "Iterable<String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUpperBound_02() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<? extends T>", "Iterable<? extends String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUpperBound_03() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<? extends T>", "Iterable<? super String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Object", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.IN);
    this.assertOrigins(this.assertMapping(_mappedBy, "T", _mappedTo_1, _mappedTo_3), "T", 1);
  }
  
  @Test
  public void testUpperBound_04() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<T>", "Iterable<? extends String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUpperBound_05() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<Iterable<T>>", "Iterable<Iterable<? extends CharSequence>>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUpperBound_06() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<T>", "Iterable<Iterable<? extends CharSequence>>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Iterable<? extends CharSequence>", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUpperBound_07() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<? extends Iterable<T>>", "Iterable<Iterable<? extends CharSequence>>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUpperBound_08() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<? extends Iterable<T>>", "Iterable<Iterable<CharSequence>>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUpperBound_09() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<? extends Iterable<T>>", "Iterable<? extends Iterable<CharSequence>>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUpperBound_10() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<? extends Iterable<T>>", "Iterable<? super Iterable<CharSequence>>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharSequence", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testUpperBound_11() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "java.util.Map<? extends T, ? extends T>", "java.util.Map<String, Integer>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("Integer", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.INVARIANT);
    this.assertOrigins(this.assertMapping(_mappedBy, "T", _mappedTo_1, _mappedTo_3), "T", 2);
  }
  
  @Test
  public void testLowerBound_01() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<? super T>", "Iterable<String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.IN);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testLowerBound_02() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<? super T>", "Iterable<? super String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.IN);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.IN);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testLowerBound_03() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<? super T>", "Iterable<? extends String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.IN);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testLowerBound_04() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T, T2 extends T", "Iterable<? super T>", "Iterable<? extends String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.IN);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("String", VarianceInfo.IN);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testLowerBound_05() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T, T2 extends T", "java.util.Map<? super T, T>", "java.util.Map<? extends String, Integer>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.IN);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("Integer", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.INVARIANT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1, _mappedTo_3);
    Pair<String, VarianceInfo> _mappedTo_4 = Pair.<String, VarianceInfo>of("String", VarianceInfo.IN);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_5 = this.operator_mappedTo(_mappedTo_4, VarianceInfo.OUT);
    Pair<String, VarianceInfo> _mappedTo_6 = Pair.<String, VarianceInfo>of("Integer", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_7 = this.operator_mappedTo(_mappedTo_6, VarianceInfo.INVARIANT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_5, _mappedTo_7);
  }
  
  @Test
  public void testLowerBound_06() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<T>", "Iterable<? super String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.IN);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testLowerBound_07() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<Iterable<T>>", "java.util.LinkedList<Iterable<? super String>>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.IN);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testLowerBound_08() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T", "Iterable<T>", "Iterable<Iterable<? super String>>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Iterable<? super String>", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testLowerBound_09() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends T2, T2", "Iterable<? super T2>", "Iterable<? extends String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.IN);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("String", VarianceInfo.IN);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testDependentTypeParams_01() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends CharSequence, T2 extends T", "java.util.Map<T, T2>", "java.util.Map<String, String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.INVARIANT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testDependentTypeParams_02() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T, T2 extends T", "Iterable<T>", "Iterable<String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.INVARIANT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testDependentTypeParams_03() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends T2, T2 extends CharSequence", "Iterable<T2>", "Iterable<String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.INVARIANT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testDependentTypeParams_04() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T, T2 extends T, T3 extends T2", "java.util.Map<T, T3>", "java.util.Map<String, Integer>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("String", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.INVARIANT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping_1 = this.assertMapping(_assertMapping, "T2", _mappedTo_3);
    Pair<String, VarianceInfo> _mappedTo_4 = Pair.<String, VarianceInfo>of("Integer", VarianceInfo.INVARIANT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_5 = this.operator_mappedTo(_mappedTo_4, VarianceInfo.INVARIANT);
    this.assertMapping(_assertMapping_1, "T3", _mappedTo_5);
  }
  
  @Test
  public void testCircularTypeParams_01() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends T", "String", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Object", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testCircularTypeParams_02() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends Iterable<T>", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Iterable<Object>", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testCircularTypeParams_03() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends Iterable<T>", "T", "java.util.List<String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("List<String>", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testCircularTypeParams_04() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends Iterable<T>", "Iterable<? extends T>", "java.util.List<String>");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.INVARIANT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testCircularTypeParams_05() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends Iterable<? extends T>", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Iterable<?>", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testCircularTypeParams_06() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends Iterable<T>", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Iterable<Object>", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testCircularTypeParams_07() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends Iterable<? super T>", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Iterable<?>", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testCircularTypeParams_08() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends org.eclipse.xtend.core.tests.typesystem.CharIterable<T>", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharIterable<CharSequence>", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testCircularTypeParams_09() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends org.eclipse.xtend.core.tests.typesystem.CharIterable<? extends T>", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("CharIterable<? extends CharSequence>", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testCircularTypeParams_10() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends Iterable<T>, T2 extends Iterable<T>", "CharSequence", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Iterable<Object>", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("Iterable<Iterable<Object>>", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testCircularTypeParams_11() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends (Object)=>T", "T", "Object");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("Object", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testCircularTypeParams_12() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends (Object)=>T", "T", "(Object)=>Object");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("(Object)=>Object", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    this.assertMapping(_mappedBy, "T", _mappedTo_1);
  }
  
  @Test
  public void testCircularTypeParams_13() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends T2, T2 extends T", "T2", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
  
  @Test
  public void testCircularTypeParams_14() {
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _mappedBy = this.mappedBy("T extends T2, T2 extends T", "T", "String");
    Pair<String, VarianceInfo> _mappedTo = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_1 = this.operator_mappedTo(_mappedTo, VarianceInfo.OUT);
    Map<JvmTypeParameter, List<LightweightBoundTypeArgument>> _assertMapping = this.assertMapping(_mappedBy, "T", _mappedTo_1);
    Pair<String, VarianceInfo> _mappedTo_2 = Pair.<String, VarianceInfo>of("String", VarianceInfo.OUT);
    Triple<String, VarianceInfo, VarianceInfo> _mappedTo_3 = this.operator_mappedTo(_mappedTo_2, VarianceInfo.OUT);
    this.assertMapping(_assertMapping, "T2", _mappedTo_3);
  }
}
