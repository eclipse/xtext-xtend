/** 
 * Copyright (c) 2012, 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.tests.quickfix

import com.google.inject.Inject
import java.util.Arrays
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
import org.eclipse.xtext.ui.editor.quickfix.QuickAssistCompletionProposal
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

	static final String VALID_EQUALS_NULL_IN_EXPRESSION = '''
		package foo
		class Foo {
			def foo(Object x) {
				return if(x === null) 0 else 1
			}
		}
	'''

	static final String SINGLE_EQUALS_NULL_IN_EXPRESSION = '''
		package foo
		class Foo {
			def foo(Object x) {
				return if(x == null) 0 else 1
			}
		}
	'''

	static final String MULTI_EQUALS_NULL_IN_EXPRESSION = '''
		package foo
		class Foo {
			def foo(Object x, Object y) {
				return if(x == null || y == null) 0 else 1
			}
		}
	'''

	static final String MULTI_EQUALS_NULL_IN_SWITCH = '''
		package foo
		class Foo {
			def foo(Object x, Object y) {
				switch true {
					case x == null: 0
					case y == null: 0
					default: 1
				}
			}
		}
	'''

	@Inject extension SyncUtil

	XtextEditor xtextEditor

	override String getFileName() {
		return "Foo"
	}

	override void setUp() throws Exception {
		super.setUp()

		projectName.createPluginProject()
		xtextEditor = openEditor(dslFile(SINGLE_EQUALS_NULL_IN_EXPRESSION))
	}

	override dslFile(CharSequence content) {
		super.dslFile(projectName, "src/foo/" + fileName, fileExtension, content);
	}

	@Test
	def void testValidEqualsNullQuickfixInExpression() {
		xtextEditor.document.set(VALID_EQUALS_NULL_IN_EXPRESSION)
		xtextEditor.waitForReconciler()

		val offset = VALID_EQUALS_NULL_IN_EXPRESSION.indexOf("===") + 1
		val proposals = Arrays.asList(computeQuickAssistProposals(offset))
		
		assertEquals(0, proposals.size())
	}

	@Test
	def void testSingleEqualsNullQuickfixInExpression() {
		xtextEditor.document.set(SINGLE_EQUALS_NULL_IN_EXPRESSION)
		xtextEditor.waitForReconciler()

		val offset = SINGLE_EQUALS_NULL_IN_EXPRESSION.indexOf("==") + 1
		val proposals = Arrays.asList(computeQuickAssistProposals(offset))

		assertEquals(1, proposals.size())
		assertEquals(1, proposals.filter[it instanceof QuickAssistCompletionProposal].size())
	}

	@Test
	def void testMultiEqualsNullQuickfixInExpression() {
		xtextEditor.document.set(MULTI_EQUALS_NULL_IN_EXPRESSION)
		xtextEditor.waitForReconciler()

		val offset = MULTI_EQUALS_NULL_IN_EXPRESSION.indexOf("==") + 1
		val proposals = Arrays.asList(computeQuickAssistProposals(offset))
		
		assertEquals(1, proposals.size())
		assertEquals(1, proposals.filter[it instanceof QuickAssistCompletionProposal].size())
	}

	@Test
	def void testMultiEqualsNullQuickfixInSwitch() {
		xtextEditor.document.set(MULTI_EQUALS_NULL_IN_SWITCH)
		xtextEditor.waitForReconciler()

		val offset = MULTI_EQUALS_NULL_IN_SWITCH.indexOf("==") + 1
		val proposals = Arrays.asList(computeQuickAssistProposals(offset))

		assertEquals(1, proposals.size())
		assertEquals(1, proposals.filter[it instanceof QuickAssistCompletionProposal].size())
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
		
		return quickAssistProcessor.computeQuickAssistProposals(new TextInvocationContext(sourceViewer, offset, -1))
	}

	def protected XtextSourceViewer getSourceViewer() {
		return xtextEditor.getInternalSourceViewer() as XtextSourceViewer
	}
}
