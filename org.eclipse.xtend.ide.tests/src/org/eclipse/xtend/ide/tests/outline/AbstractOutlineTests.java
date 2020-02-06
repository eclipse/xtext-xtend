/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.ide.tests.outline;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.xtend.core.xtend.XtendFile;
import org.eclipse.xtend.ide.outline.SwitchOutlineModeContribution;
import org.eclipse.xtend.ide.outline.XtendOutlineTreeProvider;
import org.eclipse.xtend.ide.tests.AbstractXtendUITestCase;
import org.eclipse.xtend.ide.tests.WorkbenchTestHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.actions.SortOutlineContribution;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineMode;
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreAccess;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public abstract class AbstractOutlineTests extends AbstractXtendUITestCase {
	@Inject
	private IPreferenceStoreAccess preferenceStoreAccess;

	@Inject
	private XtendOutlineTreeProvider treeProvider;

	@Inject
	private Provider<XtextDocument> documentProvider;

	@Inject
	private WorkbenchTestHelper workbenchTestHelper;
	@Inject
	private OutlineFilterAndSorter.IComparator comparator;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		getSorter().setComparator(comparator);
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		workbenchTestHelper.tearDown();

	}

	protected XtendOutlineTreeProvider getTreeProvider() {
		return treeProvider;
	}

	@Test
	public void testSimpleClass() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo {}");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(0);
	}

	@Test
	public void testPackage() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("package test class Foo {}");
		assertBuilder.numChildren(2).child(0, "test").numChildren(0);
		assertBuilder.child(1, "Foo").numChildren(0);
	}

	@Test
	public void testTypeParameter() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo <T extends Object> {}");
		assertBuilder.numChildren(1).child(0, "Foo<T extends Object>").numChildren(0);
	}

	@Test
	public void testTypeParameter1() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo <T> {}");
		assertBuilder.numChildren(1).child(0, "Foo<T>").numChildren(0);
	}

	@Test
	public void testField() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { String bar }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(1).child(0, "bar : String").numChildren(0);
	}

	@Test
	public void testConstructor() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { new(int foo) {} }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(1).child(0, "new(int)").numChildren(0);
	}

	@Test
	public void testSimpleMethod() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { def foo() {null} }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(1).child(0, "foo() : Object").numChildren(0);
	}

	@Test
	public void testMethodWithParameter() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { def foo(int bar) {null} }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(1).child(0, "foo(int) : Object").numChildren(0);
	}

	@Test
	public void testMethodWithParameters() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { def foo(int bar, java.lang.Object x) {null} }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(1).child(0, "foo(int, Object) : Object")
				.numChildren(0);
	}

	@Test
	public void testMethodWithReturnType() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { def java.lang.String foo() {null} }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(1).child(0, "foo() : String").numChildren(0);
	}

	@Test
	public void testMethodWithTypeParameter() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { def <T> foo() {null} }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(1).child(0, "foo() <T extends Object> : Object")
				.numChildren(0);
	}

	@Test
	public void testMethodWithReturnTypeParameter() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { def <T> Foo<T> foo() {null} }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(1).child(0, "foo() <T extends Object> : Foo<T>")
				.numChildren(0);
	}

	@Test
	public void testOperatorDeclarationWithSymbol() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { def java.lang.String !(Object o) {null} }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(1).child(0, "!(Object) : String (operator_not)")
				.numChildren(0);
	}

	@Test
	public void testOperatorDeclarationWithName() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { def java.lang.String operator_not(Object o) {null} }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(1).child(0, "!(Object) : String (operator_not)")
				.numChildren(0);
	}

	@Test
	public void testDispatchMethod() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { def dispatch foo(Object x) {''} def dispatch foo(String y) {''} }");
		AssertBuilder dispatcher = assertBuilder.numChildren(1).child(0, "Foo").numChildren(1)
				.child(0, "foo(Object) : String").numChildren(2);
		dispatcher.child(0, "foo(Object) : String").numChildren(0);
		dispatcher.child(1, "foo(String) : String").numChildren(0);
	}

	@Test
	public void testInterface() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("interface Foo { int bar def String foo() }");
		AssertBuilder interfaze = assertBuilder.numChildren(1).child(0, "Foo").numChildren(2);
		interfaze.child(0, "bar : int").numChildren(0);
		interfaze.child(1, "foo() : String").numChildren(0);
	}

	// enum literals are displayed without their type since that one is implicit
	@Test
	public void testEnum() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("enum Foo { BAR, BAZ }");
		AssertBuilder interfaze = assertBuilder.numChildren(1).child(0, "Foo").numChildren(2);
		interfaze.child(0, "BAR").numChildren(0);
		interfaze.child(1, "BAZ").numChildren(0);
	}

	@Test
	public void testAnnotationType() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME) annotation Foo { int bar String foo = '' }");
		AssertBuilder annotationType = assertBuilder.numChildren(1).child(0, "Foo").numChildren(2);
		annotationType.child(0, "bar : int").numChildren(0);
		annotationType.child(1, "foo : String").numChildren(0);
	}

	@Test
	public void testAnnotationTypeNoMembers() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME) annotation Foo { }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(0);
	}

	@Test
	public void testInterfaceNoMembers() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("@SuppressWarnings('foo') interface Foo { }");
		assertBuilder.numChildren(1).child(0, "Foo").numChildren(0);
	}

	@Test
	public void testCreateExtensionInfo() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { def create 'lalala' foo() {} }");
		AssertBuilder type = assertBuilder.numChildren(1).child(0, "Foo").numChildren(1);
		type.child(0, "foo() : String");
	}

	@Test
	public void testCreateExtensionInfo_dispatch() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo {  dispatch def create value : 'bar' foo(Integer it) {}  dispatch def create value : 'foo' foo(String it) {} }");
		AssertBuilder type = assertBuilder.numChildren(1).child(0, "Foo").numChildren(1);
		AssertBuilder dispatchMethod = type.child(0, "foo(Object) : String").numChildren(2);
		dispatchMethod.child(0, "foo(Integer) : String");
		dispatchMethod.child(1, "foo(String) : String");
	}

	@Test
	public void testNestedTypes() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo { int foo static class Bar { def bar() {} interface Baz {} enum FooBar{ X } } }");
		AssertBuilder foo = assertBuilder.numChildren(1).child(0, "Foo").numChildren(2);
		foo.child(0, "foo : int").numChildren(0);
		AssertBuilder bar = foo.child(1, "Bar").numChildren(3);
		bar.child(0, "bar() : Object").numChildren(0);
		bar.child(1, "Baz").numChildren(0);
		bar.child(2, "FooBar").numChildren(1);
	}

	@Test
	public void testAnonymousTypes() throws Exception {
		AssertBuilder assertBuilder = newAssertBuilder("class Foo<T extends Object> { def Foo<String> bar() { new Foo<String>() { override bar() { } } } }");
		AssertBuilder foo = assertBuilder.numChildren(1).child(0, "Foo<T extends Object>").numChildren(1);
		AssertBuilder bar = foo.child(0, "bar() : Foo<String>").numChildren(1);
		AssertBuilder barBar = bar.child(0, "new Foo<String>() {...}").numChildren(1);
		barBar.child(0, "bar() : Foo<String>").numChildren(0);
	}

	protected AssertBuilder newAssertBuilder(String model) throws Exception, CoreException {
		XtendFile file = workbenchTestHelper.xtendFile("Foo", model);
		return newAssertBuilder(file);
	}

	protected AssertBuilder newAssertBuilder(XtendFile xtendFile) throws Exception, CoreException {
		XtextDocument document = documentProvider.get();
		document.setInput((XtextResource) xtendFile.eResource());
		IOutlineNode root = treeProvider.createRoot(document);
		AssertBuilder assertBuilder = new AssertBuilder(root);
		return assertBuilder;
	}

	protected abstract OutlineFilterAndSorter getSorter();

	protected IPreferenceStoreAccess getPreferenceStoreAccess() {
		return preferenceStoreAccess;
	}

	protected WorkbenchTestHelper getWorkbenchTestHelper() {
		return workbenchTestHelper;
	}

	protected void setShowInherited(boolean isShowInherited) {
		List<OutlineMode> modes = getTreeProvider().getOutlineModes();
		getTreeProvider().setCurrentMode((isShowInherited) ? modes.get(1) : modes.get(0));
	}

	protected void setJvmMode(boolean isJvmMode) {
		getPreferenceStoreAccess().getWritablePreferenceStore().setValue(SwitchOutlineModeContribution.PREFERENCE_KEY,
				isJvmMode);
		getPreferenceStoreAccess().getWritablePreferenceStore().setValue(SortOutlineContribution.PREFERENCE_KEY, false);
	}

	public class AssertBuilder {

		private final IOutlineNode node;
		private int index = -1;

		AssertBuilder(IOutlineNode node) {
			this.node = node;
		}

		AssertBuilder(IOutlineNode node, String text) {
			this(node);
			assertEquals(text, node.getText().toString());
		}

		AssertBuilder(IOutlineNode node, StyledString styledText) {
			this(node);
			Object text = node.getText();
			assertTrue("Node text is not a StyledString: " + node, text instanceof StyledString);
			StyledString styledString = (StyledString) text;
			assertEquals("StyledString-text doesn't match", styledText.getString(), styledString.getString());
			assertArrayEquals("StyledString-StyleRanges don't match", styledText.getStyleRanges(),
					styledString.getStyleRanges());
		}

		AssertBuilder numChildren(int num) {
			IOutlineNode[] filteredAndSortedChildren = getSorter().filterAndSort(node.getChildren());
			assertEquals("Wrong number of children\n" + Joiner.on("\n").join(filteredAndSortedChildren), num,
					filteredAndSortedChildren.length);
			return this;
		}

		AssertBuilder hasTextRegion(boolean hasTextRegion) {
			if (hasTextRegion)
				assertNotNull(node.getSignificantTextRegion());
			else
				assertNull(node.getSignificantTextRegion());
			return this;
		}

		AssertBuilder child(int index, StyledString text) {
			IOutlineNode[] sortedChildren = getSorter().filterAndSort(node.getChildren());
			if (sortedChildren.length <= index)
				fail("Missing child node " + index);
			return new AssertBuilder(sortedChildren[index], text);
		}

		AssertBuilder child(int index, String text) {
			IOutlineNode[] sortedChildren = getSorter().filterAndSort(node.getChildren());
			if (sortedChildren.length <= index)
				fail("Missing child node " + index);
			this.index = index;
			return new AssertBuilder(sortedChildren[index], text);
		}

		AssertBuilder nextChild(String text) {
			this.index = this.index + 1;
			return child(index, text);
		}

		AssertBuilder leaf(int index, String text) {
			return child(index, text).numChildren(0);
		}

	}

}
