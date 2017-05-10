package org.eclipse.xtend.core.tests.macro;

import java.lang.reflect.Constructor;
import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester;
import org.eclipse.xtend.core.tests.macro.AbstractActiveAnnotationTest;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class ImmutableAnnotationTest extends AbstractActiveAnnotationTest {
  @Test
  public void defSimpleTest() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.eclipse.xtend.core.tests.macro.Immutable");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Immutable class MyClass {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String foo");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final IAcceptor<XtendCompilerTester.CompilationResult> _function = (XtendCompilerTester.CompilationResult it) -> {
      final Class<?> clazz = it.getCompiledClass();
      final Constructor<?>[] constr = clazz.getDeclaredConstructors();
      Assert.assertEquals(1, constr.length);
      Assert.assertEquals(1, IterableExtensions.<Constructor<?>>head(((Iterable<Constructor<?>>)Conversions.doWrapArray(constr))).getParameterTypes().length);
    };
    this._xtendCompilerTester.compile(_builder, _function);
  }
}
