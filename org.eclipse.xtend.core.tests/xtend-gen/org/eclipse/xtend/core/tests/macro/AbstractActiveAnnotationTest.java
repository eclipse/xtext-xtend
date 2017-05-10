package org.eclipse.xtend.core.tests.macro;

import com.google.inject.Inject;
import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester;
import org.eclipse.xtend.core.tests.AbstractXtendTestCase;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public abstract class AbstractActiveAnnotationTest extends AbstractXtendTestCase {
  @Extension
  protected XtendCompilerTester _xtendCompilerTester;
  
  @Inject
  public void doInject(final XtendCompilerTester compilerTester) {
    this._xtendCompilerTester = compilerTester;
    this._xtendCompilerTester.setJavaCompilerClassPath(this.getClass().getClassLoader());
  }
}
