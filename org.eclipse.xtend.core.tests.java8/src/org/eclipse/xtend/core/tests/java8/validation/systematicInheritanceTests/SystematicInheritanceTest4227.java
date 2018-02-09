/**
 * Copyright (c) 2018 Universite Bourgogne Franche-Comté (http://www.ubfc.fr)
 *                    Universite de Technologie de Belfort-Montbeliard (http://www.utbm.fr), and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

// CAUTION: THIS FILE IS AUTOMATICALLY GENERATED BY THE SCRIPT '../generateSystematicInheritanceTests.py'.
// DO NOT CHANGE MANUALLY.

package org.eclipse.xtext.xbase.tests.typesystem.inheritance.systematicInheritanceTests;

import java.util.List;
import com.google.common.base.Predicate;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.core.tests.AbstractXtendTestCase;
import org.eclipse.xtend.core.tests.java8.Java8RuntimeInjectorProvider;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.junit.Test;
import static com.google.common.collect.Iterables.*;

/** Java code:
 * <pre><code>
 * package mypack;
 * @SuppressWarnings("all")
 * public class SystematicInheritanceTest {
 *
 * 	public static class C extends IC implements I1, I3 {
 * 	}
 * 	public interface I1 {
 * 	}
 * 	public interface I2 {
 * 		default void method(int arg) {}
 * 	}
 * 	public interface I3 extends I1 {
 * 		default void method(int arg) {}
 * 	}
 * 	public abstract static class IC {
 * 		public void method(int arg) {}
 * 	}
 * }
 * </code></pre>
 *
 * <p>javac output:
 * <pre><code>
 * 0 error
 * </code></pre>
 *
 * @author Stephane Galland - Initial contribution and API
 */
@InjectWith(Java8RuntimeInjectorProvider.class) 
@SuppressWarnings("all")
public class SystematicInheritanceTest4227 extends AbstractXtendTestCase {

	@Inject private ValidationTestHelper helper;

	@Test
	public void validInheritanceCase() throws Exception {
		EObject resource = file(
			"public class C extends IC implements I1, I3 {\n"
			+ "}\n"
			+ "public interface I1 {\n"
			+ "}\n"
			+ "public interface I2 {\n"
			+ "	def void method(int arg) {}\n"
			+ "}\n"
			+ "public interface I3 extends I1 {\n"
			+ "	def void method(int arg) {}\n"
			+ "}\n"
			+ "public abstract class IC {\n"
			+ "	public def void method(int arg) {}\n"
			+ "}\n", false, true);
		List<Issue> issues = this.helper.validate(resource);
		Iterable<Issue> errors = filter(issues, new Predicate<Issue>() {
			public boolean apply(Issue input) {
				return Severity.ERROR == input.getSeverity() && !isIgnorable(input.getCode());
			}
		});
		if (!errors.iterator().hasNext()) {
			fail("Expecting no errors but got " + getIssuesAsString(errors));
		}
	}

	protected static String getIssuesAsString(Iterable<Issue> errors) {
		StringBuilder m = new StringBuilder();
		for (final Issue error : errors) {
			m.append(error.getSeverity());
			m.append(" (");
			m.append(error.getCode());
			m.append(") '");
			m.append(error.getMessage());
			m.append("', offset ");
			m.append(error.getOffset());
			m.append(", length ");
			m.append(error.getLength());
			m.append("\n");
		}
		return m.toString();
	}

	protected static boolean isIgnorable(String code) {
		if ("".equals(code)) {
			return true;
		}
		return false;
	}
}
