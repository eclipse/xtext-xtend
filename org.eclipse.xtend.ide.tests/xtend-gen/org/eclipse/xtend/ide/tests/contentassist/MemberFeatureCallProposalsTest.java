/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.ide.tests.contentassist;

import org.eclipse.xtend.ide.tests.contentassist.AbstractXtendContentAssistBugTest;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.Flaky;
import org.eclipse.xtext.ui.testing.ContentAssistProcessorTestBuilder;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class MemberFeatureCallProposalsTest extends AbstractXtendContentAssistBugTest {
  @Test
  public void test_01() throws Exception {
    ContentAssistProcessorTestBuilder _newBuilder = this.newBuilder();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("def static void main() {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("val list = #[]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("list.em");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _newBuilder.append(_builder.toString()).assertTextAtCursorPosition("em", 2, "empty");
  }
  
  @Flaky
  @Test
  public void test_02() throws Exception {
    ContentAssistProcessorTestBuilder _newBuilder = this.newBuilder();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("def static void main() {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("val it = #[]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("em");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _newBuilder.append(_builder.toString()).assertTextAtCursorPosition("em", 2, "empty", "emptyList", "emptyMap", "emptySet");
  }
  
  @Flaky
  @Test
  public void test_03() throws Exception {
    ContentAssistProcessorTestBuilder _newBuilder = this.newBuilder();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("def static void main() {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("val it = #[]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("println(em)");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _newBuilder.append(_builder.toString()).assertTextAtCursorPosition("em", 2, "empty", "emptyList", "emptyMap", "emptySet");
  }
  
  @Flaky
  @Test
  public void test_04() throws Exception {
    ContentAssistProcessorTestBuilder _newBuilder = this.newBuilder();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("def static void main() {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("val it = #[]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("it.addAll(em)");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _newBuilder.append(_builder.toString()).assertTextAtCursorPosition("em", 2, "empty", "emptyList", "emptyMap", "emptySet");
  }
}
