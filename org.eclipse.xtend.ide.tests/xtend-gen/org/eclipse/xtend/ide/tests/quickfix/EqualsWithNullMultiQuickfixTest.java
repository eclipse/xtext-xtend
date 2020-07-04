/**
 * Copyright (c) 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.tests.quickfix;

import java.util.Arrays;
import java.util.List;
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
  public void testEqualsWithNullQuickfixInMethodBody() {
    this.xtextEditor.getDocument().set("");
    final ICompletionProposal[] quickAssistProposals = this.computeQuickAssistProposals(this.getDocument().getLength());
    final List<ICompletionProposal> proposals = Arrays.<ICompletionProposal>asList(quickAssistProposals);
    this.assertEqualsWithNullQuickfixProposals(proposals);
  }
  
  public void assertEqualsWithNullQuickfixProposals(final List<ICompletionProposal> proposals) {
    Assert.assertTrue(false);
  }
  
  public ICompletionProposal[] computeQuickAssistProposals(final int offset) {
    return this.computeQuickAssistProposals(this.xtextEditor, offset);
  }
}
