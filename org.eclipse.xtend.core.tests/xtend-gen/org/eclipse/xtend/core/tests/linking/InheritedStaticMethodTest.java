/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.linking;

import org.eclipse.xtend.core.tests.linking.AbstractOverloadedStaticMethodTest;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * Use a copy of org.junit.Assert as test data
 * 
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class InheritedStaticMethodTest extends AbstractOverloadedStaticMethodTest {
  @Override
  protected String inMethodBody(final String invocation) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import overloading.CopiedAssert");
    _builder.newLine();
    _builder.append("class C extends CopiedAssert {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(invocation, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
