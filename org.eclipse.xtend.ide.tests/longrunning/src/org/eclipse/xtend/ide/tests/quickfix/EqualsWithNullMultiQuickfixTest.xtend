/** 
 * Copyright (c) 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.tests.quickfix

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

	static final String EQUALS_NULL_IN_SINGLE_EXPRESSION = "if (x == 1) e1 else if (x == 2) e2 else e2"

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
	def testEqualsWithNullQuickfixInSingleExpression() {
		xtextEditor.document.set(EQUALS_NULL_IN_SINGLE_EXPRESSION)
		val proposals = computeQuickAssistProposals()
		assertEquals(2, proposals.length)
	}

	def ICompletionProposal[] computeQuickAssistProposals() {
		return computeQuickAssistProposals(xtextEditor, document.getLength())
	}

	def ICompletionProposal[] computeQuickAssistProposals(int offset) {
		return computeQuickAssistProposals(xtextEditor, offset)
	}
}
