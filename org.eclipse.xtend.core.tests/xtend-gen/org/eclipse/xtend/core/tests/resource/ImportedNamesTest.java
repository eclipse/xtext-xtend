package org.eclipse.xtend.core.tests.resource;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtend.core.tests.AbstractXtendTestCase;
import org.eclipse.xtend.core.xtend.XtendFile;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class ImportedNamesTest extends AbstractXtendTestCase {
  @Inject
  private IResourceDescription.Manager resourceDescriptionManager;
  
  private final List<String> primitives = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("boolean", "int", "char", "byte", "short", "long", "float", "double", "void"));
  
  @Test
  public void testPrimitivesNotIncluded() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package testPackage");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import hubbabubba.*");
      _builder.newLine();
      _builder.append("import java.util.*");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class TestCase {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("String x;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("int i;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("boolean b;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("char c;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("short s;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("long l;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("byte t;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("float f;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("double d;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<Object> l;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def void foo() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (x == i == b == c == s == l == t == f == d) {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("println(\"never happens\")");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XtendFile file = this.file(_builder.toString());
      final IResourceDescription description = this.resourceDescriptionManager.getResourceDescription(file.eResource());
      final Function1<QualifiedName, Boolean> _function = (QualifiedName it) -> {
        return Boolean.valueOf(this.primitives.contains(it.getLastSegment()));
      };
      Assert.assertFalse(IterableExtensions.<QualifiedName>exists(description.getImportedNames(), _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testExtendedInterfaces() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import java.util.List");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Foo implements List {");
      _builder.newLine();
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XtendFile file = this.file(_builder.toString());
      final IResourceDescription description = this.resourceDescriptionManager.getResourceDescription(file.eResource());
      final Iterable<QualifiedName> importedNames = description.getImportedNames();
      final Function1<QualifiedName, Boolean> _function = (QualifiedName it) -> {
        return Boolean.valueOf(it.getLastSegment().equals("collection"));
      };
      Assert.assertTrue(("" + importedNames), IterableExtensions.<QualifiedName>exists(importedNames, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testAnonymousClass() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("val foo = new Runnable() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("override run() {}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XtendFile file = this.file(_builder.toString());
      final IResourceDescription description = this.resourceDescriptionManager.getResourceDescription(file.eResource());
      final Iterable<QualifiedName> importedNames = description.getImportedNames();
      final Function1<QualifiedName, Boolean> _function = (QualifiedName it) -> {
        return Boolean.valueOf(it.getLastSegment().equals("runnable"));
      };
      Assert.assertTrue(("" + importedNames), IterableExtensions.<QualifiedName>exists(importedNames, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testNestedTypesIncluded() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("val foo = types.StaticOuterClass.StaticMiddleClass.StaticInnerClass.CONSTANT");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XtendFile file = this.file(_builder.toString());
      final IResourceDescription description = this.resourceDescriptionManager.getResourceDescription(file.eResource());
      final Iterable<QualifiedName> importedNames = description.getImportedNames();
      final Function1<QualifiedName, Boolean> _function = (QualifiedName it) -> {
        String _string = it.toString();
        String _lowerCase = "types.StaticOuterClass$StaticMiddleClass$StaticInnerClass".toLowerCase();
        return Boolean.valueOf(Objects.equal(_string, _lowerCase));
      };
      Assert.assertTrue(("" + importedNames), IterableExtensions.<QualifiedName>exists(importedNames, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testNestedTypesIncludedOnUnresolvedFeatures() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("val foo = types.StaticOuterClass.Unknown.StaticInnerClass.CONSTANT");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XtendFile file = this.file(_builder.toString());
      final IResourceDescription description = this.resourceDescriptionManager.getResourceDescription(file.eResource());
      final Iterable<QualifiedName> importedNames = description.getImportedNames();
      final Function1<QualifiedName, Boolean> _function = (QualifiedName it) -> {
        String _string = it.toString();
        String _lowerCase = "types.StaticOuterClass$Unknown".toLowerCase();
        return Boolean.valueOf(Objects.equal(_string, _lowerCase));
      };
      Assert.assertTrue(("" + importedNames), IterableExtensions.<QualifiedName>exists(importedNames, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testNestedTypesIncludedOnUnresolvedFeatures_02() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("val foo = types.StaticOuterClass.StaticMiddleClass.Unknown");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XtendFile file = this.file(_builder.toString());
      final IResourceDescription description = this.resourceDescriptionManager.getResourceDescription(file.eResource());
      final Iterable<QualifiedName> importedNames = description.getImportedNames();
      final Function1<QualifiedName, Boolean> _function = (QualifiedName it) -> {
        String _string = it.toString();
        String _lowerCase = "types.StaticOuterClass$StaticMiddleClass$Unknown".toLowerCase();
        return Boolean.valueOf(Objects.equal(_string, _lowerCase));
      };
      Assert.assertTrue(("" + importedNames), IterableExtensions.<QualifiedName>exists(importedNames, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
