/**
 * Copyright (c) 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.tests.quickfix;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.xtend.ide.tests.XtendIDEInjectorProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.XtextSourceViewer;
import org.eclipse.xtext.ui.testing.AbstractMultiQuickfixTest;
import org.eclipse.xtext.xbase.lib.Exceptions;
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
  private static final String EQUALS_NULL_IN_SINGLE_EXPRESSION = "if (x == 1) e1 else if (x == 2) e2 else e2";
  
  private XtextEditor xtextEditor;
  
  public XtextSourceViewer getSourceViewer() {
    ISourceViewer _internalSourceViewer = this.xtextEditor.getInternalSourceViewer();
    return ((XtextSourceViewer) _internalSourceViewer);
  }
  
  public IDocument getDocument() {
    return this.getSourceViewer().getDocument();
  }
  
  @Override
  public void setUp() {
    try {
      super.setUp();
      this.xtextEditor = this.openEditor(this.dslFile("\n"));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testEqualsWithNullQuickfixInSingleExpression() {
    this.xtextEditor.getDocument().set(EqualsWithNullMultiQuickfixTest.EQUALS_NULL_IN_SINGLE_EXPRESSION);
    final ICompletionProposal[] proposals = this.computeQuickAssistProposals();
    Assert.assertEquals(2, proposals.length);
  }
  
  public ICompletionProposal[] computeQuickAssistProposals() {
    return this.computeQuickAssistProposals(this.xtextEditor, this.getDocument().getLength());
  }
  
  public ICompletionProposal[] computeQuickAssistProposals(final int offset) {
    return this.computeQuickAssistProposals(this.xtextEditor, offset);
  }
}
