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
import org.eclipse.xtend.ide.tests.XtendIDEInjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.ui.editor.XtextEditor
import org.eclipse.xtext.ui.editor.quickfix.QuickAssistCompletionProposal
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
			def m(Object a, Object b) {
				if(a == null || b === null) 0 else 1
			}
		}
	'''

	static final String MULTI_EQUALS_NULL_IN_EXPRESSION = '''
		package foo
		class Foo {
			def m(Object a, Object b) {
				if(a == null || b == null) 0 else 1
			}
		}
	'''

	static final String MULTI_EQUALS_NULL_IN_SWITCH = '''
		package foo
		class Foo {
			def m(Object a, Object b) {
				switch true {
					case a == null: 0
					case b == null: 0
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

	override dslFile(CharSequence content) {
		super.dslFile(projectName, "src/foo/" + fileName, fileExtension, content);
	}

	override void setUp() throws Exception {
		super.setUp()

		projectName.createPluginProject()
		xtextEditor = openEditor(dslFile(SINGLE_EQUALS_NULL_IN_EXPRESSION))
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

		// TODO Assert that proposals contain 1 marker
	}

	@Test
	def void testMultiEqualsNullQuickfixInExpression() {
		xtextEditor.document.set(MULTI_EQUALS_NULL_IN_EXPRESSION)
		xtextEditor.waitForReconciler()

		val offset = MULTI_EQUALS_NULL_IN_EXPRESSION.indexOf("==") + 1
		val proposals = Arrays.asList(computeQuickAssistProposals(offset))

		assertEquals(1, proposals.size())
		assertEquals(1, proposals.filter[it instanceof QuickAssistCompletionProposal].size())

		// TODO Assert that proposals contain 2 markers
	}

	@Test
	def void testMultiEqualsNullQuickfixInSwitch() {
		xtextEditor.document.set(MULTI_EQUALS_NULL_IN_SWITCH)
		xtextEditor.waitForReconciler()

		val offset = MULTI_EQUALS_NULL_IN_SWITCH.indexOf("==") + 1
		val proposals = Arrays.asList(computeQuickAssistProposals(offset))

		assertEquals(1, proposals.size())
		assertEquals(1, proposals.filter[it instanceof QuickAssistCompletionProposal].size())

		// TODO Assert that proposals contain 2 markers
	}

	/**
	 * TODO Refactor into AbstractMultiQuickfixTest
	 */
	def protected ICompletionProposal[] computeQuickAssistProposals(int offset) {
		return computeQuickAssistProposals(xtextEditor, offset)
	}
}
