/*******************************************************************************
 * Copyright (c) 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.ide.tests.quickfix

import com.google.inject.Inject
import java.util.Arrays
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
class RemoveUnnecessaryModifiersMultiQuickfixTest extends AbstractMultiQuickfixTest {

	static final String MODEL_WITH_UNNECESSARY_MODIFIERS = '''
		package unnecessarymodifiers
		public class Foo {
			private final val A = 1
			def public m() {
				new Runnable {
					public override def run() {}
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
		super.dslFile(projectName, "src/unnecessarymodifiers/" + fileName, fileExtension, content);
	}

	override void setUp() throws Exception {
		super.setUp()

		projectName.createPluginProject()
		xtextEditor = openEditor(dslFile("\n"))
	}

	@Test
	def void testRemoveUnnecessaryModifiersQuickfixXtend() {

		// Use Xtend style API
		// //
		
		MODEL_WITH_UNNECESSARY_MODIFIERS.testMultiQuickfix('''
			package unnecessarymodifiers
			<0<public>0> class Foo {
				<1<private>1> <2<final>2> val <3<A>3> = 1
				def <4<public>4> m() {
					new Runnable {
						<5<public>5> override <6<def>6> run() {}
					}
				}
			}
			----------------------------------------------------
			----------------------------------------------------
			0: message=The public modifier is unnecessary on class Foo
			1: message=The private modifier is unnecessary on field A
			2: message=The final modifier is unnecessary on field A
			3: message=The value of the field Foo.A is not used
			4: message=The public modifier is unnecessary on method m
			5: message=The public modifier is unnecessary on method run
			6: message=The def modifier is unnecessary on method run
		''', '''
			package unnecessarymodifiers
			class Foo {
				val A = 1
				def m() {
					new Runnable {
						override run() {}
					}
				}
			}
			-------------------
			(no markers found)
		''')
	}

	@Test
	def void testRemoveUnnecessaryModifiersQuickfixJava() {

		// Use Java style API
		// //
		
		xtextEditor.document.set(MODEL_WITH_UNNECESSARY_MODIFIERS)
		xtextEditor.waitForReconciler()

		val offset = MODEL_WITH_UNNECESSARY_MODIFIERS.indexOf("==") + 1
		val proposals = computeQuickAssistProposals(xtextEditor, offset)

		// TODO java.lang.AssertionError: expected:<1> but was:<0>
		assertEquals(1, proposals.size())
		assertEquals(1, proposals.filter[it instanceof QuickAssistCompletionProposal].size())
		
		// TODO assert proposal text...
	}
}
