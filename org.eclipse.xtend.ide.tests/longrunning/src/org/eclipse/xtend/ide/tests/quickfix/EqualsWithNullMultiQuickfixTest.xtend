/** 
 * Copyright (c) 2012, 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.tests.quickfix

import com.google.common.base.Predicate
import java.util.Arrays
import java.util.List
import org.eclipse.core.resources.IFile
import org.eclipse.jface.text.IDocument
import org.eclipse.jface.text.contentassist.ICompletionProposal
import org.eclipse.jface.text.quickassist.IQuickAssistProcessor
import org.eclipse.jface.text.quickassist.QuickAssistAssistant
import org.eclipse.jface.text.reconciler.IReconciler
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension
import org.eclipse.jface.text.source.TextInvocationContext
import org.eclipse.xtend.ide.tests.XtendIDEInjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.ui.editor.XtextEditor
import org.eclipse.xtext.ui.editor.XtextSourceViewer
import org.eclipse.xtext.ui.editor.reconciler.XtextReconciler
import org.eclipse.xtext.ui.testing.AbstractMultiQuickfixTest
import org.junit.Test
import org.junit.runner.RunWith

import static com.google.common.collect.Iterables.*

/** 
 * @author Aaron R Miller - Initial contribution and API
 */
@RunWith(XtextRunner)
@InjectWith(XtendIDEInjectorProvider)
class EqualsWithNullMultiQuickfixTest extends AbstractMultiQuickfixTest {

	static final String MODEL_WITH_SPELLING_QUICKFIX_IN_STRINGS = "'String Speling Error' Foo {  } \n"

	static final String SINGLE_EQUALS_NULL_IN_EXPRESSION = '''
		class Foo {
			def foo(Object x) {
				return if(x == null) 0 else 1
			}
		}
	'''

	XtextEditor xtextEditor

	override void setUp() throws Exception {
		super.setUp()
		var IFile dslFile = dslFile("\n")
		xtextEditor = openEditor(dslFile)
	}

	@Test
	def void testSpellingQuickfixInString() throws Exception {
		xtextEditor.getDocument().set(MODEL_WITH_SPELLING_QUICKFIX_IN_STRINGS)
		var ICompletionProposal[] quickAssistProposals = computeQuickAssistProposals(0)
		var List<ICompletionProposal> proposals = Arrays.asList(quickAssistProposals)
		assertSpellingQuickfixProposals(proposals)
	}

	@Test
	def void testSingleEqualsNullQuickfixInExpression() throws Exception {
		xtextEditor.getDocument().set(SINGLE_EQUALS_NULL_IN_EXPRESSION)
		var ICompletionProposal[] quickAssistProposals = computeQuickAssistProposals(0)
		var List<ICompletionProposal> proposals = Arrays.asList(quickAssistProposals)
		assertEquals(1, proposals.size())
	}

	def protected void assertSpellingQuickfixProposals(List<ICompletionProposal> proposals) {
		assertEquals(4, proposals.size())
		assertEquals(1, size(filter(proposals, classNameEquals("AddWordProposal"))))
		assertEquals(1, size(filter(proposals, classNameEquals("WordCorrectionProposal"))))
		assertEquals(1, size(filter(proposals, classNameEquals("DisableSpellCheckingProposal"))))
		assertEquals(1, size(filter(proposals, classNameEquals("WordIgnoreProposal"))))
	}

	def protected ICompletionProposal[] computeQuickAssistProposals(int offset) {
		var XtextSourceViewer sourceViewer = getSourceViewer()
		var XtextReconciler reconciler = (sourceViewer.getAdapter(IReconciler) as XtextReconciler)
		var IReconcilingStrategyExtension reconcilingStrategyExtension = (reconciler.
			getReconcilingStrategy("") as IReconcilingStrategyExtension)
		reconcilingStrategyExtension.initialReconcile()
		var QuickAssistAssistant quickAssistAssistant = (sourceViewer.getQuickAssistAssistant() as QuickAssistAssistant)
		var IQuickAssistProcessor quickAssistProcessor = quickAssistAssistant.getQuickAssistProcessor()
		var ICompletionProposal[] quickAssistProposals = quickAssistProcessor.computeQuickAssistProposals(
			new TextInvocationContext(sourceViewer, offset, -1))
		return quickAssistProposals
	}

	def protected IDocument getDocument() {
		return getSourceViewer().getDocument()
	}

	def protected XtextSourceViewer getSourceViewer() {
		var XtextSourceViewer sourceViewer = (xtextEditor.getInternalSourceViewer() as XtextSourceViewer)
		return sourceViewer
	}

	def Predicate<ICompletionProposal> classNameEquals(String simpleName) {
		return [ICompletionProposal input|return input.getClass().getSimpleName().equals(simpleName)]
	}
}
