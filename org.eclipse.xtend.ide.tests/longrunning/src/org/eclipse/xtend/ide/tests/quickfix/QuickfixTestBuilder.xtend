/*******************************************************************************
 * Copyright (c) 2013, 2018 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.ide.tests.quickfix

import com.google.inject.Inject
import java.util.List
import java.util.Set
import java.util.regex.Pattern
import org.eclipse.core.resources.IFile
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.jface.preference.IPersistentPreferenceStore
import org.eclipse.xtend.ide.tests.WorkbenchTestHelper
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.ui.editor.XtextEditor
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreAccess
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider
import org.eclipse.xtext.ui.refactoring.ui.SyncUtil
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.util.JavaVersion
import org.eclipse.xtext.validation.CheckMode
import org.eclipse.xtext.validation.IResourceValidator
import org.eclipse.xtext.validation.Issue
import org.eclipse.xtext.xbase.annotations.validation.UnresolvedFeatureCallTypeAwareMessageProvider
import org.eclipse.xtext.xbase.ui.builder.XbaseBuilderPreferenceAccess

import static org.junit.Assert.*

class QuickfixTestBuilder {
	
	@Inject extension WorkbenchTestHelper
	
	@Inject extension IssueResolutionProvider

	@Inject extension IResourceValidator
	
	@Inject extension SyncUtil
	
	@Inject IPreferenceStoreAccess preferenceStoreAccess;
	
	@Inject XbaseBuilderPreferenceAccess xbaseBuilderPreferenceAccess;
	
	int caretOffset
	
	XtextEditor editor
	
	List<Issue> issues
	
	Set<String> modifiedIssueCodes
	
	val primaryPositionMarker = "<|>"
	val secondaryPositionMarker = "|"
	
	private def getPreferenceStore() {
		preferenceStoreAccess.getWritablePreferenceStore(project) as IPersistentPreferenceStore;
	}
	
	def setSeverity(String issueCode, String severity) {
		if (modifiedIssueCodes === null)
			modifiedIssueCodes = newHashSet
		modifiedIssueCodes.add(issueCode)
		preferenceStore.setValue(issueCode, "error")
	}
	
	def setJavaVersion(JavaVersion javaVersion) {
		xbaseBuilderPreferenceAccess.setJavaVersion(project, javaVersion);
	}
	
	def create(String fileName, String model) {
		val positionMarker = model.positionMarker
		val file = createFile(fileName, model.replace(positionMarker, ""))
		editor = openEditorSafely(file)
		val document = editor.document
		assertNotNull("Error getting document from editor", document) 
		document.readOnly [
			issues = validate(CheckMode::NORMAL_AND_FAST, CancelIndicator::NullImpl)
		]
		caretOffset = model.indexOf(positionMarker)
		this
	}
	
	def caretOffset(int offset) {
		caretOffset = offset
		this
	}

	def assertIssueCodes(String... issueCodes) {
		assertEqual(issueCodes, issuesAtCaret.map[code])
		this
	}
	
	def assertFeatureCallLinkingIssue() {
		assertTrue(issuesAtCaret.exists [
			code == Diagnostic.LINKING_DIAGNOSTIC &&
				data.contains(UnresolvedFeatureCallTypeAwareMessageProvider.FEATURE_CALL)
		])
		this
	}
	
	def assertTypeLiteralLinkingIssue() {
		assertTrue(issuesAtCaret.exists [
			code == Diagnostic.LINKING_DIAGNOSTIC &&
				data.contains(UnresolvedFeatureCallTypeAwareMessageProvider.TYPE_LITERAL)
		])
		this
	}
	
	def assertResolutionLabels(String... resolutionLabels) {
		val resolutions = issuesAtCaret.map[resolutions].flatten
		assertEqual(resolutionLabels, resolutions.map[label])
		this
	}

	def assertResolutionLabelsSubset(String... expectedLabels) {
		val actualLabels = issuesAtCaret.map[resolutions].flatten.map[label].toSet
		expectedLabels.forEach[
			assertTrue('Label \'' + it + '\' missing. Got ' + actualLabels.join(', '), actualLabels.contains(it))
		]
		this
	}

	def assertNoResolutionLabels(String... unExpectedLabels) {
		val actualLabels = issuesAtCaret.map[resolutions].flatten.map[label].toSet
		unExpectedLabels.forEach[
			assertFalse('Label \'' + it + '\' should not appear. Got ' + actualLabels.join(', '), actualLabels.contains(it))
		]
		this
	}

	def assertModelAfterQuickfix(CharSequence expectedModel) {
		val resolutions = issuesAtCaret.map[resolutions].flatten.toList
		val originalModel = editor.document.get
		val resolution = resolutions.head
		assertNotNull(resolution)
		resolution.apply()
		assertEquals(expectedModel.toString, editor.document.get())
		editor.document.set(originalModel)
		waitForReconciler(editor)
		this
	}
	
	def assertModelAfterQuickfix(String label, CharSequence expectedModel) {
		val resolutions = issuesAtCaret.map[resolutions].flatten.toList
		val originalModel = editor.document.get
		val matchingResolution = resolutions.findFirst[it.label==label]
		assertNotNull(error(newArrayList(label), resolutions.map[label]), matchingResolution)
		matchingResolution.apply()
		assertEquals(expectedModel.toString, editor.document.get())
		editor.document.set(originalModel)
		waitForReconciler(editor)
		this
	}
	
	def protected getEditor() {
		return editor
	}
	
	def protected getIssuesAtCaret() {
		issues.filter[offset <= caretOffset && offset+length >= caretOffset]
	}
	
	def protected assertEqual(List<String> expected, Iterable<String> actual) {
		assertEquals(expected.sort.join('\n'), actual.sort.join('\n'))
	}
	
	def protected error(Iterable<String> expected, Iterable<String> actual) {
		'''
			Expected 
				'«expected.join(', ')»'  
			but got 
				'«actual.join(', ')»'
		'''.toString
	}
	
	def protected openEditorSafely(IFile file) throws Exception {
		val monitor = new NullProgressMonitor
		waitForBuild(monitor)
		yieldToQueuedDisplayJobs(monitor)
		var editor = openEditor(file)
		waitForReconciler(editor)
		yieldToQueuedDisplayJobs(monitor)
		editor
	}
	
	def protected getPositionMarker(String model) {
		if(model.count(primaryPositionMarker) == 1) {
			return primaryPositionMarker
		} else if(model.count(secondaryPositionMarker) == 1) {
			return secondaryPositionMarker
		} else {
			fail('''«primaryPositionMarker» may be used to disambiguate ordinary «secondaryPositionMarker» from a position marker!''')
		}
	}
	
	def protected count(String model, String positionMarker) {
		val regex = Pattern.quote(positionMarker)
		model.split(regex).length - 1
	}
	
	def tearDown() {
		editor = null
		closeAllEditors(false)
		files.forEach[ delete(true, new NullProgressMonitor) ]
		files.clear
		if (modifiedIssueCodes !== null) {
			preferenceStore => [
				modifiedIssueCodes.forEach [ code |
					setToDefault(code)
				]
				setToDefault(XbaseBuilderPreferenceAccess.PREF_USE_COMPILER_SOURCE)
				setToDefault(XbaseBuilderPreferenceAccess.PREF_JAVA_VERSION)
			]
			modifiedIssueCodes = null;
		}		
		yieldToQueuedDisplayJobs(new NullProgressMonitor)
	}
	
	def removeFile(String fileName) {
		getFile(fileName).delete(true, new NullProgressMonitor)
		this
	}
	
}