/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.richstring;

import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class RichStringCompilerTest2 extends RichStringCompilerTest {
	
	@Override
	public void assertOutput(String expectedOutput, String richString) throws Exception {
		String completeCode = "val result = new org.eclipse.xtend2.lib.StringConcatenation\n" +
			"result.append(client)\n" + 
			"return result.toString();\n" +
			"}\n" +
			"def org.eclipse.xtend2.lib.StringConcatenationClient getClient() {\n" + richString;
		super.assertOutput(expectedOutput, completeCode);
	}
	
	@Override
	public void assertOutput(String expectedOutput, String imports, String richString) throws Exception {
		String completeCode = "val result = new org.eclipse.xtend2.lib.StringConcatenation\n" +
			"result.append(client)\n" + 
			"return result.toString();\n" +
			"}\n" +
			"def org.eclipse.xtend2.lib.StringConcatenationClient getClient() {\n" + richString;
		super.assertOutput(expectedOutput, imports, completeCode);
	}
	
	@Test public void testIndentedSCClient() throws Exception {
		assertOutput("{\n\tfoo\n\tbar\n}",
				"val org.eclipse.xtend2.lib.StringConcatenationClient scc = '''\n" +
				"foo\n" +
				"bar\n" +
				"''';\n" +
				"'''{\n\t�scc�\n}'''");
	}
	
}
