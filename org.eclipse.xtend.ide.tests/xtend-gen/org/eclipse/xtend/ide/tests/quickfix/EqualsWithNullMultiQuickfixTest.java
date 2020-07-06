/**
 * Copyright (c) 2012, 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.tests.quickfix;

import com.google.inject.Inject;
import java.util.Arrays;
import java.util.List;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.xtend.ide.tests.WorkbenchTestHelper;
import org.eclipse.xtend.ide.tests.XtendIDEInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.quickfix.QuickAssistCompletionProposal;
import org.eclipse.xtext.ui.refactoring.ui.SyncUtil;
import org.eclipse.xtext.ui.testing.AbstractMultiQuickfixTest;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Aaron R Miller - Initial contribution and API
 */
@RunWith(XtextRunner.class)
@InjectWith(XtendIDEInjectorProvider.class)
@SuppressWarnings("all")
public class EqualsWithNullMultiQuickfixTest extends AbstractMultiQuickfixTest {
  private static final String MODEL_WITH_EMPTY_CLASS = new Function0<String>() {
    @Override
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Foo {}");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  private static final String MODEL_WITH_EQUALS_NULL_IN_EXPRESSION = new Function0<String>() {
    @Override
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def m(Object a, Object b, Object c) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if(a == null || b == null || c === null) 0 else 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  private static final String MODEL_WITH_EQUALS_NULL_IN_SWITCH = new Function0<String>() {
    @Override
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def m(Object a, Object b, Object c) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("switch true {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("case a == null: 0");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("case b == null: 0");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("case c === null: 0");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("default: 1");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  @Inject
  @Extension
  private SyncUtil _syncUtil;
  
  private XtextEditor xtextEditor;
  
  @Override
  public String getFileName() {
    return "Foo";
  }
  
  @Override
  public IFile dslFile(final CharSequence content) {
    String _projectName = this.getProjectName();
    String _fileName = this.getFileName();
    String _plus = ("src/foo/" + _fileName);
    return super.dslFile(_projectName, _plus, this.getFileExtension(), content);
  }
  
  @Override
  public void setUp() throws Exception {
    super.setUp();
    WorkbenchTestHelper.createPluginProject(this.getProjectName());
    this.xtextEditor = this.openEditor(this.dslFile(EqualsWithNullMultiQuickfixTest.MODEL_WITH_EMPTY_CLASS));
  }
  
  @Test
  public void testEqualsNullQuickfixInExpression() {
    this.xtextEditor.getDocument().set(EqualsWithNullMultiQuickfixTest.MODEL_WITH_EQUALS_NULL_IN_EXPRESSION);
    this._syncUtil.waitForReconciler(this.xtextEditor);
    int _indexOf = EqualsWithNullMultiQuickfixTest.MODEL_WITH_EQUALS_NULL_IN_EXPRESSION.indexOf("==");
    final int offset = (_indexOf + 1);
    final List<ICompletionProposal> proposals = Arrays.<ICompletionProposal>asList(this.computeQuickAssistProposals(offset));
    Assert.assertEquals(1, proposals.size());
    final Function1<ICompletionProposal, Boolean> _function = (ICompletionProposal it) -> {
      return Boolean.valueOf((it instanceof QuickAssistCompletionProposal));
    };
    Assert.assertEquals(1, IterableExtensions.size(IterableExtensions.<ICompletionProposal>filter(proposals, _function)));
  }
  
  @Test
  public void testEqualsNullQuickfixInSwitch() {
    this.xtextEditor.getDocument().set(EqualsWithNullMultiQuickfixTest.MODEL_WITH_EQUALS_NULL_IN_SWITCH);
    this._syncUtil.waitForReconciler(this.xtextEditor);
    int _indexOf = EqualsWithNullMultiQuickfixTest.MODEL_WITH_EQUALS_NULL_IN_SWITCH.indexOf("==");
    final int offset = (_indexOf + 1);
    final List<ICompletionProposal> proposals = Arrays.<ICompletionProposal>asList(this.computeQuickAssistProposals(offset));
    Assert.assertEquals(1, proposals.size());
    final Function1<ICompletionProposal, Boolean> _function = (ICompletionProposal it) -> {
      return Boolean.valueOf((it instanceof QuickAssistCompletionProposal));
    };
    Assert.assertEquals(1, IterableExtensions.size(IterableExtensions.<ICompletionProposal>filter(proposals, _function)));
  }
  
  /**
   * TODO Refactor into AbstractMultiQuickfixTest
   */
  protected ICompletionProposal[] computeQuickAssistProposals(final int offset) {
    return this.computeQuickAssistProposals(this.xtextEditor, offset);
  }
}
