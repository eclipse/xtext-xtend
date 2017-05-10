/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.ide.tests.contentassist;

import java.util.Iterator;
import java.util.List;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.xtend.ide.tests.contentassist.AbstractXtendContentAssistBugTest;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.ui.ContentAssistProcessorTestBuilder;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ReplacementTextApplier;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Holger Schill - Initial contribution and API
 */
@SuppressWarnings("all")
public class Bug427440Test extends AbstractXtendContentAssistBugTest {
  @Test
  public void testStaticMemberPriority() throws Exception {
    ContentAssistProcessorTestBuilder _newBuilder = this.newBuilder();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static def augument() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Main {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Test.a|");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final Iterator<ICompletionProposal> proposals = ((List<ICompletionProposal>)Conversions.doWrapArray(_newBuilder.append(_builder.toString()).computeCompletionProposals("|"))).iterator();
    this.assertContains(proposals.next(), "augument");
    this.assertContains(proposals.next(), "annotatedInterfaces");
    this.assertContains(proposals.next(), "annotatedSuperclass");
    this.assertContains(proposals.next(), "annotation");
    this.assertContains(proposals.next(), "annotations");
    this.assertContains(proposals.next(), "anonymousClass");
    this.assertContains(proposals.next(), "array");
    this.assertContains(proposals.next(), "asSubclass()");
  }
  
  public void assertContains(final ICompletionProposal proposal, final String text) {
    Assert.assertEquals(text, this.getProposedText(proposal));
  }
  
  public String getProposedText(final ICompletionProposal completionProposal) {
    String proposedText = completionProposal.getDisplayString();
    if ((completionProposal instanceof ConfigurableCompletionProposal)) {
      proposedText = ((ConfigurableCompletionProposal)completionProposal).getReplacementString();
      ConfigurableCompletionProposal.IReplacementTextApplier _textApplier = ((ConfigurableCompletionProposal)completionProposal).getTextApplier();
      if ((_textApplier instanceof ReplacementTextApplier)) {
        ConfigurableCompletionProposal.IReplacementTextApplier _textApplier_1 = ((ConfigurableCompletionProposal)completionProposal).getTextApplier();
        proposedText = ((ReplacementTextApplier) _textApplier_1).getActualReplacementString(((ConfigurableCompletionProposal)completionProposal));
      }
    }
    return proposedText;
  }
}
