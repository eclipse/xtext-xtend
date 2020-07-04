/**
 * Copyright (c) 2012, 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.tests.quickfix;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import java.util.Arrays;
import java.util.List;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.quickassist.IQuickAssistProcessor;
import org.eclipse.jface.text.quickassist.QuickAssistAssistant;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.TextInvocationContext;
import org.eclipse.xtend.ide.tests.XtendIDEInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.XtextSourceViewer;
import org.eclipse.xtext.ui.editor.reconciler.XtextReconciler;
import org.eclipse.xtext.ui.testing.AbstractMultiQuickfixTest;
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
  private static final String MODEL_WITH_SPELLING_QUICKFIX_IN_STRINGS = "\'String Speling Error\' Foo {  } \n";
  
  private static final String SINGLE_EQUALS_NULL_IN_EXPRESSION = new Function0<String>() {
    @Override
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def foo(Object x) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return if(x == null) 0 else 1");
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
    IFile dslFile = this.dslFile("\n");
    this.xtextEditor = this.openEditor(dslFile);
  }
  
  @Test
  public void testSpellingQuickfixInString() throws Exception {
    this.xtextEditor.getDocument().set(EqualsWithNullMultiQuickfixTest.MODEL_WITH_SPELLING_QUICKFIX_IN_STRINGS);
    ICompletionProposal[] quickAssistProposals = this.computeQuickAssistProposals(0);
    List<ICompletionProposal> proposals = Arrays.<ICompletionProposal>asList(quickAssistProposals);
    this.assertSpellingQuickfixProposals(proposals);
  }
  
  @Test
  public void testSingleEqualsNullQuickfixInExpression() throws Exception {
    this.xtextEditor.getDocument().set(EqualsWithNullMultiQuickfixTest.SINGLE_EQUALS_NULL_IN_EXPRESSION);
    ICompletionProposal[] quickAssistProposals = this.computeQuickAssistProposals(0);
    List<ICompletionProposal> proposals = Arrays.<ICompletionProposal>asList(quickAssistProposals);
    Assert.assertEquals(1, proposals.size());
  }
  
  protected void assertSpellingQuickfixProposals(final List<ICompletionProposal> proposals) {
    Assert.assertEquals(4, proposals.size());
    Assert.assertEquals(1, Iterables.size(Iterables.<ICompletionProposal>filter(proposals, this.classNameEquals("AddWordProposal"))));
    Assert.assertEquals(1, Iterables.size(Iterables.<ICompletionProposal>filter(proposals, this.classNameEquals("WordCorrectionProposal"))));
    Assert.assertEquals(1, Iterables.size(Iterables.<ICompletionProposal>filter(proposals, this.classNameEquals("DisableSpellCheckingProposal"))));
    Assert.assertEquals(1, Iterables.size(Iterables.<ICompletionProposal>filter(proposals, this.classNameEquals("WordIgnoreProposal"))));
  }
  
  protected ICompletionProposal[] computeQuickAssistProposals(final int offset) {
    XtextSourceViewer sourceViewer = this.getSourceViewer();
    IReconciler _adapter = sourceViewer.<IReconciler>getAdapter(IReconciler.class);
    XtextReconciler reconciler = ((XtextReconciler) _adapter);
    IReconcilingStrategy _reconcilingStrategy = reconciler.getReconcilingStrategy("");
    IReconcilingStrategyExtension reconcilingStrategyExtension = ((IReconcilingStrategyExtension) _reconcilingStrategy);
    reconcilingStrategyExtension.initialReconcile();
    IQuickAssistAssistant _quickAssistAssistant = sourceViewer.getQuickAssistAssistant();
    QuickAssistAssistant quickAssistAssistant = ((QuickAssistAssistant) _quickAssistAssistant);
    IQuickAssistProcessor quickAssistProcessor = quickAssistAssistant.getQuickAssistProcessor();
    TextInvocationContext _textInvocationContext = new TextInvocationContext(sourceViewer, offset, (-1));
    ICompletionProposal[] quickAssistProposals = quickAssistProcessor.computeQuickAssistProposals(_textInvocationContext);
    return quickAssistProposals;
  }
  
  protected IDocument getDocument() {
    return this.getSourceViewer().getDocument();
  }
  
  protected XtextSourceViewer getSourceViewer() {
    ISourceViewer _internalSourceViewer = this.xtextEditor.getInternalSourceViewer();
    XtextSourceViewer sourceViewer = ((XtextSourceViewer) _internalSourceViewer);
    return sourceViewer;
  }
  
  public Predicate<ICompletionProposal> classNameEquals(final String simpleName) {
    final Predicate<ICompletionProposal> _function = (ICompletionProposal input) -> {
      return input.getClass().getSimpleName().equals(simpleName);
    };
    return _function;
  }
}
