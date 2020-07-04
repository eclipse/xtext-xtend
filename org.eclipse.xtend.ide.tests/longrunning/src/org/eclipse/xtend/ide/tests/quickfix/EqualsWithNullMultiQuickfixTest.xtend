/** 
 * Copyright (c) 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.tests.quickfix

import java.util.Arrays
import java.util.List
import org.eclipse.jface.text.IDocument
import org.eclipse.jface.text.contentassist.ICompletionProposal
import org.eclipse.xtend.ide.tests.XtendIDEInjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.ui.editor.XtextEditor
import org.eclipse.xtext.ui.editor.XtextSourceViewer
import org.eclipse.xtext.ui.testing.AbstractMultiQuickfixTest
import org.junit.Test
import org.junit.runner.RunWith

/** 
 * @author Aaron R Miller - Initial contribution and API
 */
@RunWith(XtextRunner)
@InjectWith(XtendIDEInjectorProvider)
class EqualsWithNullMultiQuickfixTest extends AbstractMultiQuickfixTest {

	XtextEditor xtextEditor
	
	def XtextSourceViewer getSourceViewer() {
		return xtextEditor.getInternalSourceViewer() as XtextSourceViewer
	}
	
	def IDocument getDocument() {
		return getSourceViewer().getDocument()
	}

	override void setUp() {
		super.setUp();
		xtextEditor = openEditor(dslFile("\n"))
	}

	@Test
	def testEqualsWithNullQuickfixInMethodBody() {
		xtextEditor.document.set("")
		val quickAssistProposals = computeQuickAssistProposals(document.getLength())
		val proposals = Arrays.asList(quickAssistProposals)
		assertEqualsWithNullQuickfixProposals(proposals)
	}

	def assertEqualsWithNullQuickfixProposals(List<ICompletionProposal> proposals) {
		assertTrue(false)
//		assertEquals(4, proposals.size());
//		assertEquals(1, size(filter(proposals, classNameEquals("AddWordProposal"))));
//		assertEquals(1, size(filter(proposals, classNameEquals("WordCorrectionProposal"))));
//		assertEquals(1, size(filter(proposals, classNameEquals("DisableSpellCheckingProposal"))));
//		assertEquals(1, size(filter(proposals, classNameEquals("WordIgnoreProposal"))));
	}
	
	def ICompletionProposal[] computeQuickAssistProposals(int offset) {
		return computeQuickAssistProposals(xtextEditor, offset)
	}
}
