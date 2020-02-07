/**
 * Copyright (c) 2013, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.validation;

import org.eclipse.xtend.core.tests.validation.AmbiguityValidationTest;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class AmbiguousOperatorsTest extends AmbiguityValidationTest {
  @Test
  public void testAmbiguousOperators_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void m(C c1, C c2) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("c1 + c2");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void operator_plus(C c, Object o) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void operator_plus(Object o, C c) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("Ambiguous binary operation.");
    _builder_1.newLine();
    _builder_1.append("The operator declarations");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("operator_plus(C, Object) in C and");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("operator_plus(Object, C) in C");
    _builder_1.newLine();
    _builder_1.append("both match.");
    this.assertAmbiguous(_builder, _builder_1.toString());
  }
  
  @Test
  public void testAmbiguousOperators_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void n(String s) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("-s");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void operator_minus(CharSequence c) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void operator_minus(java.io.Serializable s) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("Ambiguous unary operation.");
    _builder_1.newLine();
    _builder_1.append("The operator declarations");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("operator_minus(CharSequence) in C and");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("operator_minus(Serializable) in C");
    _builder_1.newLine();
    _builder_1.append("both match.");
    this.assertAmbiguous(_builder, _builder_1.toString());
  }
  
  @Test
  public void testAmbiguousOperators_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void m(C c1, C c2) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("c1 + c2");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void +(C c, Object o) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void operator_plus(Object o, C c) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("Ambiguous binary operation.");
    _builder_1.newLine();
    _builder_1.append("The operator declarations");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("operator_plus(C, Object) in C and");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("operator_plus(Object, C) in C");
    _builder_1.newLine();
    _builder_1.append("both match.");
    this.assertAmbiguous(_builder, _builder_1.toString());
  }
  
  @Test
  public void testAmbiguousOperators_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void n(String s) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("-s");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void operator_minus(CharSequence c) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void -(java.io.Serializable s) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("Ambiguous unary operation.");
    _builder_1.newLine();
    _builder_1.append("The operator declarations");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("operator_minus(CharSequence) in C and");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("operator_minus(Serializable) in C");
    _builder_1.newLine();
    _builder_1.append("both match.");
    this.assertAmbiguous(_builder, _builder_1.toString());
  }
  
  @Test
  public void testUnambiguousOperators_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void n(String s) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("s+s");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void operator_plus(String s1, String s2) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertUnambiguous(_builder);
  }
  
  @Test
  public void testUnambiguousOperators_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void n(String s) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("s+s");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void +(String s1, String s2) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertUnambiguous(_builder);
  }
  
  @Test
  public void testUnambiguousOperators_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void n(String s) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("s+s");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void operator_plus(String s1, String s2) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertUnambiguous(_builder);
  }
  
  @Test
  public void testUnambiguousOperators_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void n(String s) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("s+s");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void +(String s1, String s2) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertUnambiguous(_builder);
  }
}
