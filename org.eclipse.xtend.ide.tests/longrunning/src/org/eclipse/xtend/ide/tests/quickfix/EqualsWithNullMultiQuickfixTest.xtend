/** 
 * Copyright (c) 2012, 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.tests.quickfix

import com.google.inject.Inject
import org.eclipse.jface.text.contentassist.ICompletionProposal
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
import org.eclipse.xtext.ui.refactoring.ui.SyncUtil
import org.eclipse.xtext.ui.testing.AbstractMultiQuickfixTest
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.eclipse.xtend.ide.tests.WorkbenchTestHelper.createPluginProject

/** 
 * @author Aaron R Miller - Initial contribution and API
 */
@RunWith(XtextRunner)
@InjectWith(XtendIDEInjectorProvider)
class EqualsWithNullMultiQuickfixTest extends AbstractMultiQuickfixTest {
	
	@Inject extension SyncUtil

	static final String SINGLE_EQUALS_NULL_IN_EXPRESSION = '''
		package foo {
			class Foo {
				def foo(Object x) {
					return if(x == null) 0 else 1
				}
			}
		}
	'''

	XtextEditor xtextEditor

	override void setUp() throws Exception {
		super.setUp()
		xtextEditor = openEditor(dslFile("\n"))
	}
	
	override dslFile(String projectName, String fileName, String fileExtension, CharSequence content) {
		projectName.createPluginProject
		super.dslFile(projectName, "src/foo/" + fileName, fileExtension, content)
	}

	@Test
	def void testSingleEqualsNullQuickfixInExpression() throws Exception {
		xtextEditor.document.set(SINGLE_EQUALS_NULL_IN_EXPRESSION)
		xtextEditor.waitForReconciler
		
		val offset = SINGLE_EQUALS_NULL_IN_EXPRESSION.indexOf("==") + 1
		val proposals = computeQuickAssistProposals(offset)
		
		// also tried these...
		// val proposals = computeQuickAssistProposals(0)
		// val proposals = computeQuickAssistProposals(xtextEditor, offset)
		// val proposals = computeQuickAssistProposals(xtextEditor, 0)
		
		// TODO expected:<1> but was:<0>
		assertEquals(1, proposals.size())
	}

	/**
	 * TODO This is duplicated in SpellingQuickfixTest and could possibly be refactored
	 *      into AbstractMultiQuickfixTest
	 */
	def protected ICompletionProposal[] computeQuickAssistProposals(int offset) {
		val reconciler = sourceViewer.getAdapter(IReconciler) as XtextReconciler
		val reconcilingStrategy = reconciler.getReconcilingStrategy("") as IReconcilingStrategyExtension
		reconcilingStrategy.initialReconcile()
		
		val quickAssistAssistant = sourceViewer.getQuickAssistAssistant() as QuickAssistAssistant
		val quickAssistProcessor = quickAssistAssistant.getQuickAssistProcessor()
		return quickAssistProcessor
				.computeQuickAssistProposals(new TextInvocationContext(sourceViewer, offset, -1))
	}

	def protected XtextSourceViewer getSourceViewer() {
		return xtextEditor.getInternalSourceViewer() as XtextSourceViewer
	}
}
