/**
 * Copyright (c) 2012, 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.tests.quickfix;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.quickassist.IQuickAssistProcessor;
import org.eclipse.jface.text.quickassist.QuickAssistAssistant;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.TextInvocationContext;
import org.eclipse.xtend.ide.tests.WorkbenchTestHelper;
import org.eclipse.xtend.ide.tests.XtendIDEInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.XtextSourceViewer;
import org.eclipse.xtext.ui.editor.reconciler.XtextReconciler;
import org.eclipse.xtext.ui.refactoring.ui.SyncUtil;
import org.eclipse.xtext.ui.testing.AbstractMultiQuickfixTest;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
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
  @Inject
  @Extension
  private SyncUtil _syncUtil;
  
  private static final String SINGLE_EQUALS_NULL_IN_EXPRESSION = new Function0<String>() {
    @Override
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("def foo(Object x) {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("return if(x == null) 0 else 1");
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
  
  private XtextEditor xtextEditor;
  
  @Override
  public void setUp() throws Exception {
    super.setUp();
    this.xtextEditor = this.openEditor(this.dslFile("\n"));
  }
  
  @Override
  public IFile dslFile(final String projectName, final String fileName, final String fileExtension, final CharSequence content) {
    try {
      IFile _xblockexpression = null;
      {
        WorkbenchTestHelper.createPluginProject(projectName);
        _xblockexpression = super.dslFile(projectName, ("src/foo/" + fileName), fileExtension, content);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSingleEqualsNullQuickfixInExpression() throws Exception {
    this.xtextEditor.getDocument().set(EqualsWithNullMultiQuickfixTest.SINGLE_EQUALS_NULL_IN_EXPRESSION);
    this._syncUtil.waitForReconciler(this.xtextEditor);
    int _indexOf = EqualsWithNullMultiQuickfixTest.SINGLE_EQUALS_NULL_IN_EXPRESSION.indexOf("==");
    final int offset = (_indexOf + 1);
    final ICompletionProposal[] proposals = this.computeQuickAssistProposals(offset);
    Assert.assertEquals(1, ((List<ICompletionProposal>)Conversions.doWrapArray(proposals)).size());
  }
  
  /**
   * TODO This is duplicated in SpellingQuickfixTest and could possibly be refactored
   *      into AbstractMultiQuickfixTest
   */
  protected ICompletionProposal[] computeQuickAssistProposals(final int offset) {
    IReconciler _adapter = this.getSourceViewer().<IReconciler>getAdapter(IReconciler.class);
    final XtextReconciler reconciler = ((XtextReconciler) _adapter);
    IReconcilingStrategy _reconcilingStrategy = reconciler.getReconcilingStrategy("");
    final IReconcilingStrategyExtension reconcilingStrategy = ((IReconcilingStrategyExtension) _reconcilingStrategy);
    reconcilingStrategy.initialReconcile();
    IQuickAssistAssistant _quickAssistAssistant = this.getSourceViewer().getQuickAssistAssistant();
    final QuickAssistAssistant quickAssistAssistant = ((QuickAssistAssistant) _quickAssistAssistant);
    final IQuickAssistProcessor quickAssistProcessor = quickAssistAssistant.getQuickAssistProcessor();
    XtextSourceViewer _sourceViewer = this.getSourceViewer();
    TextInvocationContext _textInvocationContext = new TextInvocationContext(_sourceViewer, offset, (-1));
    return quickAssistProcessor.computeQuickAssistProposals(_textInvocationContext);
  }
  
  protected XtextSourceViewer getSourceViewer() {
    ISourceViewer _internalSourceViewer = this.xtextEditor.getInternalSourceViewer();
    return ((XtextSourceViewer) _internalSourceViewer);
  }
}
