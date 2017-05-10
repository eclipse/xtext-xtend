/**
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.typesystem;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.xtend.core.jvmmodel.IXtendJvmAssociations;
import org.eclipse.xtend.core.tests.typesystem.AbstractTestingTypeReferenceOwner;
import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.eclipse.xtext.xbase.typesystem.conformance.TypeConformanceComputer;
import org.eclipse.xtext.xbase.typesystem.references.AnyTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.FunctionTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.ITypeReferenceOwner;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.ParameterizedTypeReference;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow
 */
@SuppressWarnings("all")
public class CommonSuperTypeTest extends AbstractTestingTypeReferenceOwner {
  @Inject
  @Extension
  private IXtendJvmAssociations _iXtendJvmAssociations;
  
  public Object isSuperTypeOf(final String superType, final String... types) {
    return this.isSuperTypeOf(Pair.<String, String>of(superType, null), types);
  }
  
  public Object isSuperTypeOf(final Pair<String, String> superTypeAndParam, final String... types) {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("def ");
      {
        boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(superTypeAndParam.getValue());
        boolean _not = (!_isNullOrEmpty);
        if (_not) {
          _builder.append("<");
          String _value = superTypeAndParam.getValue();
          _builder.append(_value);
          _builder.append("> ");
        }
      }
      _builder.append("void method(");
      {
        boolean _hasElements = false;
        for(final String type : types) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(", ", "");
          }
          String _fixup = this.fixup(type);
          _builder.append(_fixup);
          _builder.append(" t");
        }
      }
      _builder.append(") {}");
      final String signature = _builder.toString();
      final XtendFunction function = this.function(signature.toString());
      final JvmOperation operation = this._iXtendJvmAssociations.getDirectlyInferredOperation(function);
      final Function1<JvmFormalParameter, LightweightTypeReference> _function = (JvmFormalParameter it) -> {
        return this.toLightweightTypeReference(it.getParameterType());
      };
      List<LightweightTypeReference> _map = ListExtensions.<JvmFormalParameter, LightweightTypeReference>map(operation.getParameters(), _function);
      final ArrayList<LightweightTypeReference> typeReferences = new ArrayList<LightweightTypeReference>(_map);
      final TypeConformanceComputer conformanceComputer = this.getServices().getTypeConformanceComputer();
      LightweightTypeReference computedSuperType = conformanceComputer.getCommonSuperType(typeReferences, this.getOwner());
      String _simpleName = null;
      if (computedSuperType!=null) {
        _simpleName=computedSuperType.getSimpleName();
      }
      Assert.assertEquals(superTypeAndParam.getKey(), _simpleName);
      computedSuperType = this.getServices().getTypeConformanceComputer().getCommonSuperType(IterableExtensions.<LightweightTypeReference>toList(Iterables.<LightweightTypeReference>concat(typeReferences, typeReferences)), this.getOwner());
      String _simpleName_1 = null;
      if (computedSuperType!=null) {
        _simpleName_1=computedSuperType.getSimpleName();
      }
      Assert.assertEquals(superTypeAndParam.getKey(), _simpleName_1);
      computedSuperType = this.getServices().getTypeConformanceComputer().getCommonSuperType(ListExtensions.<LightweightTypeReference>reverseView(typeReferences), this.getOwner());
      String _simpleName_2 = null;
      if (computedSuperType!=null) {
        _simpleName_2=computedSuperType.getSimpleName();
      }
      Assert.assertEquals(superTypeAndParam.getKey(), _simpleName_2);
      boolean _isPrimitiveOrVoid = this.isPrimitiveOrVoid(computedSuperType);
      boolean _not_1 = (!_isPrimitiveOrVoid);
      if (_not_1) {
        List<AnyTypeReference> _newImmutableList = CollectionLiterals.<AnyTypeReference>newImmutableList(this.getOwner().newAnyTypeReference(), this.getOwner().newAnyTypeReference());
        computedSuperType = this.getServices().getTypeConformanceComputer().getCommonSuperType(IterableExtensions.<LightweightTypeReference>toList(Iterables.<LightweightTypeReference>concat(typeReferences, _newImmutableList)), this.getOwner());
        String _simpleName_3 = null;
        if (computedSuperType!=null) {
          _simpleName_3=computedSuperType.getSimpleName();
        }
        Assert.assertEquals(superTypeAndParam.getKey(), _simpleName_3);
      }
      if ((computedSuperType != null)) {
        final Procedure1<LightweightTypeReference> _function_1 = (LightweightTypeReference superType) -> {
          final Consumer<LightweightTypeReference> _function_2 = (LightweightTypeReference it) -> {
            LightweightTypeReference _commonSuperType = conformanceComputer.getCommonSuperType(Collections.<LightweightTypeReference>unmodifiableList(CollectionLiterals.<LightweightTypeReference>newArrayList(it, superType)), superType.getOwner());
            String _simpleName_4 = null;
            if (_commonSuperType!=null) {
              _simpleName_4=_commonSuperType.getSimpleName();
            }
            Assert.assertEquals(superTypeAndParam.getKey(), _simpleName_4);
          };
          typeReferences.forEach(_function_2);
        };
        ObjectExtensions.<LightweightTypeReference>operator_doubleArrow(computedSuperType, _function_1);
      }
      if ((computedSuperType != null)) {
        for (final LightweightTypeReference subType : typeReferences) {
          Assert.assertTrue(computedSuperType.isAssignableFrom(subType));
        }
      }
      return computedSuperType;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private boolean isPrimitiveOrVoid(final LightweightTypeReference computedSuperType) {
    boolean _xblockexpression = false;
    {
      if ((computedSuperType == null)) {
        return false;
      }
      _xblockexpression = (computedSuperType.isPrimitiveVoid() || computedSuperType.isPrimitive());
    }
    return _xblockexpression;
  }
  
  public void isFunctionAndEquivalentTo(final Object reference, final String type) {
    Assert.assertTrue((reference instanceof FunctionTypeReference));
    Assert.assertEquals(type, this.getEquivalent(((FunctionTypeReference) reference)));
  }
  
  public String getEquivalent(final ParameterizedTypeReference type) {
    boolean _isEmpty = type.getTypeArguments().isEmpty();
    if (_isEmpty) {
      return type.getType().getSimpleName();
    }
    StringConcatenation _builder = new StringConcatenation();
    String _simpleName = type.getType().getSimpleName();
    _builder.append(_simpleName);
    _builder.append("<");
    final Function1<LightweightTypeReference, CharSequence> _function = (LightweightTypeReference it) -> {
      return it.getSimpleName();
    };
    String _join = IterableExtensions.<LightweightTypeReference>join(type.getTypeArguments(), ", ", _function);
    _builder.append(_join);
    _builder.append(">");
    return _builder.toString();
  }
  
  protected String fixup(final String type) {
    String _elvis = null;
    String _replace = null;
    if (type!=null) {
      _replace=type.replace("$$Procedure", "org.eclipse.xtext.xbase.lib.Procedures.Procedure");
    }
    String _replace_1 = null;
    if (_replace!=null) {
      _replace_1=_replace.replace("$Procedure", "org.eclipse.xtext.xbase.lib.Procedures$Procedure");
    }
    String _replace_2 = null;
    if (_replace_1!=null) {
      _replace_2=_replace_1.replace("$Function<", "com.google.common.base.Function<");
    }
    String _replace_3 = null;
    if (_replace_2!=null) {
      _replace_3=_replace_2.replace("$Predicate<", "com.google.common.base.Predicate<");
    }
    String _replace_4 = null;
    if (_replace_3!=null) {
      _replace_4=_replace_3.replace("$$Function", "org.eclipse.xtext.xbase.lib.Functions.Function");
    }
    String _replace_5 = null;
    if (_replace_4!=null) {
      _replace_5=_replace_4.replace("$Function", "org.eclipse.xtext.xbase.lib.Functions$Function");
    }
    if (_replace_5 != null) {
      _elvis = _replace_5;
    } else {
      _elvis = "Object";
    }
    return _elvis;
  }
  
  @Test
  public void testCommonSuperType_01() {
    this.isSuperTypeOf("Serializable & CharSequence", "String", "StringBuilder");
  }
  
  @Test
  public void testCommonSuperType_02() {
    this.isSuperTypeOf("CharSequence", "String", "StringBuilder", "CharSequence");
  }
  
  @Test
  public void testCommonSuperType_03() {
    this.isSuperTypeOf("Object", "String", "StringBuilder", "CharSequence", "Object");
  }
  
  @Test
  public void testCommonSuperType_04() {
    this.isSuperTypeOf("String", "String", "String");
  }
  
  @Test
  public void testCommonSuperType_05() {
    this.isSuperTypeOf("String", "String");
  }
  
  @Test
  public void testCommonSuperType_06() {
    this.isSuperTypeOf("Serializable", "String", "StringBuilder", "java.io.Serializable");
  }
  
  @Test
  public void testCommonSuperType_07() {
    this.isSuperTypeOf("Appendable & CharSequence", "StringBuilder", "java.nio.CharBuffer");
  }
  
  @Test
  public void testCommonSuperType_08() {
    this.isSuperTypeOf("Collection<String>", "java.util.Set<String>", "java.util.List<String>");
  }
  
  @Test
  public void testCommonSuperType_09() {
    this.isSuperTypeOf("Collection", "java.util.Set<String>", "java.util.List");
  }
  
  @Test
  public void testCommonSuperType_10() {
    this.isSuperTypeOf("Collection<? extends CharSequence>", "java.util.Set<String>", "java.util.List<CharSequence>");
  }
  
  @Test
  public void testCommonSuperType_11() {
    this.isSuperTypeOf("Comparable<?> & Serializable", "String", "Integer");
  }
  
  @Test
  public void testCommonSuperType_12() {
    this.isSuperTypeOf("Number & Comparable<?>", "Double", "Integer");
  }
  
  @Test
  public void testCommonSuperType_13() {
    this.isSuperTypeOf("AbstractStringBuilder & Serializable", "StringBuilder", "StringBuffer");
  }
  
  @Test
  public void testCommonSuperType_14() {
    this.isSuperTypeOf(Pair.<String, String>of("Serializable & CharSequence", "T extends CharSequence & java.io.Serializable, V extends CharSequence & java.io.Serializable"), "T", "V");
  }
  
  @Test
  public void testCommonSuperType_15() {
    this.isSuperTypeOf(Pair.<String, String>of("Serializable & CharSequence", "T extends CharSequence & java.io.Serializable, V extends java.io.Serializable & CharSequence"), "T", "V");
  }
  
  @Test
  public void testCommonSuperType_16() {
    this.isSuperTypeOf(Pair.<String, String>of("T", "T extends CharSequence & java.io.Serializable"), "T", "T");
  }
  
  @Test
  public void testCommonSuperType_17() {
    this.isSuperTypeOf(Pair.<String, String>of("Serializable & CharSequence", "T extends CharSequence & java.io.Serializable & Comparable, V extends java.io.Serializable & CharSequence"), "T", "V");
  }
  
  @Test
  public void testCommonSuperType_18() {
    this.isSuperTypeOf(Pair.<String, String>of("T", "T, V extends T"), "T", "V");
  }
  
  @Test
  public void testCommonSuperType_19() {
    this.isSuperTypeOf(Pair.<String, String>of("Collection<String>", "T extends java.util.List<String>, V extends T"), "java.util.Set<String>", "V");
  }
  
  @Test
  public void testCommonSuperType_20() {
    this.isSuperTypeOf(Pair.<String, String>of("Object", "T extends CharSequence & java.io.Serializable, V extends Appendable & Comparable"), "T", "V");
  }
  
  @Test
  public void testCommonSuperType_21() {
    this.isSuperTypeOf(Pair.<String, String>of("Object", "T extends CharSequence & java.io.Serializable"), "T", "CharSequence", "java.io.Serializable");
  }
  
  @Test
  public void testCommonSuperType_22() {
    this.isSuperTypeOf(Pair.<String, String>of("CharSequence", "T extends CharSequence & java.io.Serializable"), "T", "CharSequence");
  }
  
  @Test
  public void testCommonSuperType_23() {
    try {
      this.function("def void m() {}");
      ITypeReferenceOwner _owner = this.getOwner();
      AnyTypeReference _anyTypeReference = new AnyTypeReference(_owner);
      ITypeReferenceOwner _owner_1 = this.getOwner();
      AnyTypeReference _anyTypeReference_1 = new AnyTypeReference(_owner_1);
      final List<LightweightTypeReference> types = CollectionLiterals.<LightweightTypeReference>newImmutableList(_anyTypeReference, _anyTypeReference_1);
      final LightweightTypeReference superType = this.getServices().getTypeConformanceComputer().getCommonSuperType(types, this.getOwner());
      Assert.assertEquals("null", superType.getSimpleName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCommonSuperType_24() {
    this.isSuperTypeOf("Collection<? extends AbstractStringBuilder & Serializable>", "java.util.List<StringBuilder>", "java.util.Set<StringBuffer>");
  }
  
  @Test
  public void testCommonSuperType_25() {
    this.isSuperTypeOf("Iterable", "java.util.List<StringBuilder>", "org.eclipse.xtend.core.tests.typesystem.RawIterable");
  }
  
  @Test
  public void testCommonSuperType_26() {
    this.isSuperTypeOf("Iterable", "java.util.List<StringBuilder>", "org.eclipse.xtend.core.tests.typesystem.CharIterable");
  }
  
  @Test
  public void testCommonSuperType_27() {
    this.isSuperTypeOf("Iterable<? extends CharSequence>", "java.util.List<StringBuilder>", "org.eclipse.xtend.core.tests.typesystem.CharIterable<CharSequence>");
  }
  
  @Test
  public void testCommonSuperType_28() {
    this.isSuperTypeOf("Number[] & Comparable<?>[]", "Integer[]", "Double[]");
  }
  
  @Test
  public void testCommonSuperType_29() {
    this.isSuperTypeOf("Serializable & Cloneable", "int[]", "double[]");
  }
  
  @Test
  public void testCommonSuperType_30() {
    this.isSuperTypeOf("Serializable & Cloneable", "int[][]", "int[]");
  }
  
  @Test
  public void testCommonSuperType_31() {
    this.isSuperTypeOf("Object[]", "CharSequence[][]", "CharSequence[]");
  }
  
  @Test
  public void testCommonSuperType_32() {
    this.isSuperTypeOf("Number[]", "Number[]", "Integer[]", "Double[]");
  }
  
  @Test
  public void testCommonSuperType_33() {
    this.isSuperTypeOf(Pair.<String, String>of("T[]", "T, V extends T"), "V[]", "T[]");
  }
  
  @Test
  public void testCommonSuperType_34() {
    this.isSuperTypeOf(Pair.<String, String>of("Iterable<? extends CharSequence>", "T extends CharSequence, V extends T"), "Iterable<CharSequence>", "Iterable<V>");
  }
  
  @Test
  public void testCommonSuperType_35() {
    this.isSuperTypeOf("Iterable<? extends CharSequence>", "Iterable<CharSequence>", "Iterable<? extends CharSequence>");
  }
  
  @Test
  public void testCommonSuperType_36() {
    this.isSuperTypeOf("Iterable<? super CharSequence>", "Iterable<CharSequence>", "Iterable<? super CharSequence>");
  }
  
  @Test
  public void testCommonSuperType_37() {
    this.isSuperTypeOf("Iterable<?>", "Iterable<? super CharSequence>", "Iterable<? extends CharSequence>");
  }
  
  @Test
  public void testCommonSuperType_38() {
    this.isSuperTypeOf(Pair.<String, String>of("CharSequence[]", "T extends CharSequence, V extends T"), "V[]", "String[]");
  }
  
  @Test
  public void testCommonSuperType_39() {
    this.isSuperTypeOf("Number[][][] & Comparable<?>[][][]", "Integer[][][]", "Double[][][]");
  }
  
  @Test
  public void testCommonSuperType_40() {
    this.isSuperTypeOf("Serializable[]", "Number[][]", "Number[]");
  }
  
  @Test
  public void testCommonSuperType_41() {
    this.isSuperTypeOf("Comparable<?> & Serializable", "String", "int");
  }
  
  @Test
  public void testCommonSuperType_42() {
    this.isSuperTypeOf("AbstractList<String> & Serializable & Cloneable", "java.util.LinkedList<String>", "java.util.ArrayList<String>");
  }
  
  @Test
  public void testCommonSuperType_43() {
    this.isSuperTypeOf(((String) null), "String", "void");
  }
  
  @Test
  public void testCommonSuperType_44() {
    this.isSuperTypeOf("void", "void", "void");
  }
  
  @Test
  public void testCommonSuperType_45() {
    this.isSuperTypeOf("Class<?>", "Class<Void>", "Class<String>");
  }
  
  @Test
  public void testCommonSuperType_46() {
    this.isSuperTypeOf("Collection<?>", "java.util.Collection<Void>", "java.util.List<String>");
  }
  
  @Test
  public void testCommonSuperType_47() {
    this.isSuperTypeOf("long", "long", "int");
  }
  
  @Test
  public void testCommonSuperType_48() {
    this.isSuperTypeOf("double", "long", "double");
  }
  
  @Test
  public void testCommonSuperType_49() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("()=>void", "()=>void", "()=>void"), "Procedure0");
  }
  
  @Test
  public void testCommonSuperType_50() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("()=>long", "()=>long", "()=>long"), "Function0<? extends Long>");
  }
  
  @Test
  public void testCommonSuperType_51() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("()=>Number & Comparable<?>", "()=>int", "()=>long"), "Function0<? extends Number & Comparable<?>>");
  }
  
  @Test
  public void testCommonSuperType_52() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("()=>Number & Comparable<?>", "()=>Integer", "()=>Long"), "Function0<? extends Number & Comparable<?>>");
  }
  
  @Test
  public void testCommonSuperType_53() {
    this.isSuperTypeOf("Object", "()=>void", "()=>Void");
  }
  
  @Test
  public void testCommonSuperType_54() {
    this.isSuperTypeOf("Object", "(String)=>void", "()=>void");
  }
  
  @Test
  public void testCommonSuperType_55() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("(String)=>Integer", "(String)=>Integer", "(String)=>Integer"), "Function1<? super String, ? extends Integer>");
  }
  
  @Test
  public void testCommonSuperType_56() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("(String, String)=>int", "java.util.Comparator<String>", "(String, String)=>int"), "Function2<? super String, ? super String, ? extends Integer>");
  }
  
  @Test
  public void testCommonSuperType_57() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("(String)=>Integer", "(String)=>Integer", "(Object)=>Integer"), "Function1<? super String, ? extends Integer>");
  }
  
  @Test
  public void testCommonSuperType_58() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("(String)=>Object", "(String)=>Appendable", "(CharSequence)=>CharSequence"), "Function1<? super String, ?>");
  }
  
  @Test
  public void testCommonSuperType_59() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("(String, String)=>int", "java.util.Comparator<? super String>", "(String, String)=>int"), "Function2<? super String, ? super String, ? extends Integer>");
  }
  
  @Test
  public void testCommonSuperType_60() {
    this.isSuperTypeOf("Object", "java.util.Comparator<? extends String>", "(String, String)=>int");
  }
  
  @Test
  public void testCommonSuperType_61() {
    this.isSuperTypeOf("Comparator<String>", "java.util.Comparator<String>", "(CharSequence, CharSequence)=>int");
  }
  
  @Test
  public void testCommonSuperType_62() {
    this.isSuperTypeOf("Comparator<? super String>", "java.util.Comparator<? super String>", "(CharSequence, CharSequence)=>int");
  }
  
  @Test
  public void testCommonSuperType_63() {
    this.isSuperTypeOf("Object", "java.util.Comparator<? extends String>", "(CharSequence, CharSequence)=>int");
  }
  
  @Test
  public void testCommonSuperType_64() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("(String)=>Object", "(String)=>String", "(Object)=>Object"), "Function1<? super String, ?>");
  }
  
  @Test
  public void testCommonSuperType_65() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("(String)=>Object", "$Function1<? super Object, ? extends Object>", "$Function1<? super String, ? extends String>"), "Function1<? super String, ?>");
  }
  
  @Test
  public void testCommonSuperType_66() {
    this.isSuperTypeOf("Function1<?, ?>", "$Function1<Object, Object>", "$Function1<String, String>");
  }
  
  @Test
  public void testCommonSuperType_67() {
    this.isSuperTypeOf("Comparable<?> & Serializable", "Integer", "String");
  }
  
  @Test
  public void testCommonSuperType_68() {
    this.isSuperTypeOf("Comparable<?> & Serializable", "Integer", "String", "String");
  }
  
  @Test
  public void testCommonSuperType_69() {
    this.isSuperTypeOf("Iterable<?>", "Iterable<?>", "Iterable<? extends CharSequence>");
  }
  
  @Test
  public void testCommonSuperType_70() {
    this.isSuperTypeOf("Iterable<?>", "Iterable<? super CharSequence>", "Iterable<?>");
  }
  
  @Test
  public void testCommonSuperType_71() {
    this.isFunctionAndEquivalentTo(this.isSuperTypeOf("(String)=>Object", "$$Function1<? super Object, ? extends Object>", "$$Function1<? super String, ? extends String>"), "Function1<? super String, ?>");
  }
  
  @Test
  public void testCommonSuperType_72() {
    this.isSuperTypeOf("Function1<?, ?>", "$$Function1<Object, Object>", "$$Function1<String, String>");
  }
  
  @Test
  public void testCommonSuperType_73() {
    this.isSuperTypeOf("Iterable<?>", "Iterable<? super CharSequence>", "Iterable<String>");
  }
  
  @Test
  public void testCommonSuperType_74() {
    this.isSuperTypeOf("Boolean", "boolean", "Boolean");
  }
  
  @Test
  public void testCommonSuperType_75() {
    this.isSuperTypeOf("Boolean", "Boolean", "boolean");
  }
  
  @Test
  public void testCommonSuperType_76() {
    this.isSuperTypeOf("Super<Number>$Inner<Number>", "test.InnerClasses.Super<Number>.Inner<Number>", "test.InnerClasses.Super<Number>.SubInner<Number>");
  }
  
  @Test
  public void testCommonSuperType_77() {
    this.isSuperTypeOf("Super<Number>$Inner<Number>", "test.InnerClasses.Super<Number>.SubInner<Number>", "test.InnerClasses.Sub<Number>.SubInner2<String>");
  }
  
  @Test
  public void testCommonSuperType_78() {
    this.isSuperTypeOf("Super<Number>$Inner<Number>", "test.InnerClasses.Super<Number>.SubInner<Number>", "test.InnerClasses.Sub<Number>.SubInner2<Number>");
  }
  
  @Test
  public void testCommonSuperType_79() {
    this.isSuperTypeOf("Super<Number>$Inner<Number>[]", "test.InnerClasses.Super<Number>.SubInner<Number>[]", "test.InnerClasses.Sub<Number>.SubInner2<Number>[]");
  }
  
  @Test
  public void testCommonSuperType_80() {
    this.isSuperTypeOf("Iterable<? extends Class<?>>", "Iterable<Class<Number>>", "Iterable<Class<CharSequence>>");
  }
  
  @Test
  public void testCommonSuperType_81() {
    this.isSuperTypeOf("Class<?>", "Class<Number>", "Class<CharSequence>");
  }
}
