#!/usr/bin/env python3
# -*- coding: utf-8 -*-
# Copyright (c) 2018 Universite Bourgogne Franche-Comté (http://www.ubfc.fr)
#                    Universite de Technologie de Belfort-Montbeliard (http://www.utbm.fr), and others.
# All rights reserved. This program and the accompanying
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html

import re
import os
import shutil
import subprocess
from pprint import pprint

'''
Generate tests for systematic testing of the inheritance feature.

:author: Stephane Galland - Initial contribution and API.
'''

class UnitTestGenerator(object):
	'''
	Generate tests for systematic testing of the inheritance feature.
	'''

	def __init__(self):
		self.ignorableCodes = list()

	def addIgnorableCode(self, code : str):
		'''
		Add an issue code that must be ignored.
		:param code: The issue code.
		:type name: str
		'''
		self.ignorableCodes.append(code)

	def _dslfctproto(self, name : str, rettype : str, *args : str) -> str:
		'''
		Replies a functions' prototype.
		:param name: The name of the function.
		:type name: str
		:param rettype: The type of the returned value.
		:type rettype: str
		:param args: The description of the parameters, an array of pairs (name, type).
		:type args: str
		:rtype: str
		'''
		p = "def " + rettype + " " + name + "("
		i = 0
		length = len(args)
		while i < length:
			if i > 0:
				p = p + ", "
			ptype = args[i]
			pname = args[i + 1]
			i = i + 2
			p = p + ptype + " " + pname
		p = p + ")"
		return p

	def _createInterfaces(self, t : dict, name : str, *supers : str):
		'''
		Create the interface definitions.
		:param t: The description of the interface type. Keys are 'java' for the Java definition, 'dsl' for the DSL definition.
		:type t: dict
		:param name: The name of the interface type.
		:type name: str
		:param supers: The list of the super types.
		:type supers: str
		'''
		ss = list(supers) + [""]
		for s in ss:
			extends = ""
			if s:
				extends = " extends " + s
			if "java" not in t:
				t["java"] = dict()
			if name not in t["java"]:
				t["java"][name] = list()
			proto = "\tpublic interface " + name + extends + " {\n"
			t["java"][name].append(
				proto
				+ "\t\tvoid method(int arg);\n"
				+ "\t}")
			t["java"][name].append(
				proto
				+ "\t\tdefault void method(int arg) {}\n"
				+ "\t}")
			t["java"][name].append(
				proto
				+ "\t}")

			if "dsl" not in t:
				t["dsl"] = dict()
			if name not in t["dsl"]:
				t["dsl"][name] = list()
			proto = "public interface " + name + extends + " {\n"
			t["dsl"][name].append(
				proto
				+ "\t" + self._dslfctproto("method", "void", "int", "arg") + "\n"
				+ "}")
			t["dsl"][name].append(
				proto
				+ "\t" + self._dslfctproto("method", "void", "int", "arg") + " {}\n"
				+ "}")
			t["dsl"][name].append(
				proto
				+ "}")

	def _createClasses(self, t : dict, abstract : bool, rel : str, name : str, supercls : str, *interfaces : str):
		'''
		Create the class definitions.
		:param t: The description of the class type. Keys are 'java' for the Java definition, 'dsl' for the DSL definition.
		:type t: dict
		:param abstract: The flag that indicates if the class is abstract or not.
		:type absname: bool
		:param rel: The name of the relation among the super types. May be 'and' or other.
		:type rel: str
		:param name: The name of the class type.
		:type name: str
		:param supercls: The name of the super type.
		:type supercls: str
		:param interfaces: The list of implemented interfaces.
		:type interfaces: str
		'''
		ss = list()
		if rel == "and":
			ss = [ str.join(', ', list(interfaces)),  interfaces[0] ]
		else:
			ss = list(interfaces) + [""]
		extends = ''
		if supercls:
			extends = " extends " + supercls
		if abstract:
			abstract = " abstract"
		else:
			abstract = ''
		for sup in ss:
			impls = ''
			if sup:
				impls = " implements " + sup
			
			if "java" not in t:
				t["java"] = dict()
			if name not in t["java"]:
				t["java"][name] = list()
			proto = "\tpublic" + abstract + " static class " + name + extends + impls + " {\n"
			t["java"][name].append(
				proto
				+ "\t\tpublic void method(int arg) {}\n"
				+ "\t}")
			t["java"][name].append(
				proto
				+ "\t\tpublic abstract void method(int arg);\n"
				+ "\t}")
			t["java"][name].append(
				proto
				+ "\t}")

			if "dsl" not in t:
				t["dsl"] = dict()
			if name not in t["dsl"]:
				t["dsl"][name] = list()
			proto = "public" + abstract + " class " + name + extends + impls + " {\n"
			t["dsl"][name].append(
				proto
				+ "\tpublic " + self._dslfctproto("method", "void", "int", "arg") + " {}\n"
				+ "}")
			t["dsl"][name].append(
				proto
				+ "\tpublic abstract " + self._dslfctproto("method", "void", "int", "arg") + "\n"
				+ "}")
			t["dsl"][name].append(
				proto
				+ "}")

	def _generateCases(self, t : dict) -> list:
		'''
		Generate all the cases of inheritance definitions.
		:param t: The description of the class type. Keys are 'java' for the Java definition, 'dsl' for the DSL definition.
		:type t: dict
		:rtype: list
		'''
		cases = list()
		for k in sorted(t.keys()):
			if len(cases) > 0:
				ncases = list()
				for prefix in cases:
					for code in t[k]:
						ncases.append( prefix + [code] )
				cases = ncases
			else:
				for code in t[k]:
					cases.append( [code] )
		return cases

	def _generateJavaCode(self, elements : list) -> str:
		'''
		Generate the Java declaration for the unit test.
		:param elements: The Java definitions of the unit tests.
		:type elements: list
		:rtype: str
		'''
		java = "package mypack;\n@SuppressWarnings(\"all\")\npublic class SystematicInheritanceTest {\n\n"

		for elt in elements:
			java = java + elt + "\n"

		java = java + "}"
		return java

	def _generateDSLCode(self, ok : bool, index : int, elements : list) -> str:
		'''
		Generate the DSL declaration for the unit test.
		:param ok: Indicates if the unit test should be a failure or a sucess.
		:type ok: bool
		:param index: The index of the unit test.
		:type index: int
		:param elements: The DSL definitions of the unit tests.
		:type elements: list
		:rtype: str
		'''
		dsl = ''
		dsl = dsl + "public class SystematicInheritanceTest" + str(index) + " extends AbstractXtendTestCase {\n"
		dsl = dsl + "\n"
		dsl = dsl + "\t@Inject private ValidationTestHelper helper;\n"
		dsl = dsl + "\n"
		dsl = dsl + "\t@Test\n"
		dsl = dsl + "\tpublic void "
		if ok:
			dsl = dsl + "validInheritanceCase"
		else:
			dsl = dsl + "invalidInheritanceCase"
		dsl = dsl + "() throws Exception {\n"
		dsl = dsl + "\t\tEObject resource = file("
		first = True
		for elt in elements:
			formatted = elt.strip()
			for line in formatted.splitlines():
				dsl = dsl + "\n\t\t\t"
				if first:
					first = False
				else:
					dsl = dsl + "+ "
				dsl = dsl + "\"" + line + "\\n\""
		dsl = dsl + ", false, true);\n"
		dsl = dsl + "\t\tList<Issue> issues = this.helper.validate(resource);\n"
		dsl = dsl + "\t\tIterable<Issue> errors = filter(issues, new Predicate<Issue>() {\n"
		dsl = dsl + "\t\t\tpublic boolean apply(Issue input) {\n"
		dsl = dsl + "\t\t\t\treturn Severity.ERROR == input.getSeverity() && !isIgnorable(input.getCode());\n"
		dsl = dsl + "\t\t\t}\n"
		dsl = dsl + "\t\t});\n"
		dsl = dsl + "\t\tif ("
		if ok:
			dsl = dsl + "!"
		dsl = dsl + "errors.iterator().hasNext()) {\n"
		dsl = dsl + "\t\t\tfail("
		if ok:
			dsl = dsl + "\"Expecting no errors but got \" + getIssuesAsString(errors)"
		else:
			dsl = dsl + "\"Expecting error\""
		dsl = dsl + ");\n"
		dsl = dsl + "\t\t}\n"
		dsl = dsl + "\t}\n"
		dsl = dsl + "\n"
		dsl = dsl + "\tprotected static String getIssuesAsString(Iterable<Issue> errors) {\n"
		dsl = dsl + "\t\tStringBuilder m = new StringBuilder();\n"
		dsl = dsl + "\t\tfor (final Issue error : errors) {\n"
		dsl = dsl + "\t\t\tm.append(error.getSeverity());\n"
		dsl = dsl + "\t\t\tm.append(\" (\");\n"
		dsl = dsl + "\t\t\tm.append(error.getCode());\n"
		dsl = dsl + "\t\t\tm.append(\") '\");\n"
		dsl = dsl + "\t\t\tm.append(error.getMessage());\n"
		dsl = dsl + "\t\t\tm.append(\"', offset \");\n"
		dsl = dsl + "\t\t\tm.append(error.getOffset());\n"
		dsl = dsl + "\t\t\tm.append(\", length \");\n"
		dsl = dsl + "\t\t\tm.append(error.getLength());\n"
		dsl = dsl + "\t\t\tm.append(\"\\n\");\n"
		dsl = dsl + "\t\t}\n"
		dsl = dsl + "\t\treturn m.toString();\n"
		dsl = dsl + "\t}\n"
		dsl = dsl + "\n"
		dsl = dsl + "\tprotected static boolean isIgnorable(String code) {\n"
		for icode in self.ignorableCodes:
			dsl = dsl + "\t\tif (\"" + icode + "\".equals(code)) {\n"
			dsl = dsl + "\t\t\treturn true;\n"
			dsl = dsl + "\t\t}\n"
		dsl = dsl + "\t\treturn false;\n"
		dsl = dsl + "\t}\n"
		dsl = dsl + "}\n"
		return dsl

	def _toJavaDoc(self, text : str, indent : str = "") -> str:
		'''
		Convert the given text to a valid JavaDoc text (with * prefix).
		:param text: The text to transform.
		:type text: str
		:param indent: The indentation text.
		:type indent: str
		:rtype: str
		'''
		result = ""
		for line in text.splitlines():
			if line:
				result = result + indent + " * " + line + "\n"
			else:
				result = result + indent + " *\n"
		if not result:
			return indent + " *\n"
		return result

	def generate(self, maxNumberOfGenerations : int = 0):
		'''
		Generate the unit tests.
		:param maxNumberOfGenerations: The maximum number of files that will be generated. If negative or nul, all the files are generated.
		:type maxNumberOfGenerations: int
		'''
		types = dict()
		self._createInterfaces(types, 'I1', 'I2')
		self._createInterfaces(types, 'I2')
		self._createInterfaces(types, 'I3', 'I1', 'I2')

		self._createClasses(types, True, 'or', 'IC', '', 'I1', 'I2')
		self._createClasses(types, False, 'and', 'C', 'IC', 'I1', 'I3')

		javaCases = self._generateCases(types["java"])

		dslCases = self._generateCases(types["dsl"])

		shutil.rmtree("./systematicInheritanceTests", ignore_errors = True)
		os.makedirs("./systematicInheritanceTests", exist_ok = True)

		max1 = len(dslCases)
		max2 = len(javaCases)

		if maxNumberOfGenerations > 0:
			max1 = min(max1, maxNumberOfGenerations)
			max2 = min(max2, maxNumberOfGenerations)

		nbSuccess = 0
		nbFailures = 0

		i = 0
		while i < max1 and i < max2:
			print((i+1), "/", max1)
			jcase = javaCases[i]
			dcase = dslCases[i]
			java = self._generateJavaCode(jcase)
			shutil.rmtree("./java", ignore_errors = True)
			os.makedirs("./java/mypack", exist_ok = True)
			with open("./java/mypack/SystematicInheritanceTest.java", "wt") as fileid:
				fileid.write(java)
			os.chdir("./java")
			result = subprocess.run(['/usr/bin/javac', '-nowarn', 'mypack/SystematicInheritanceTest.java'], stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
			err = result.stdout.decode('utf-8')
			ok = (result.returncode == 0)

			if ok:
				nbSuccess = nbSuccess + 1
			else:
				nbFailures = nbFailures + 1

			os.chdir("..")

			shutil.rmtree("./java", ignore_errors = True)

			out = "./systematicInheritanceTests/SystematicInheritanceTest" + str(i) + ".java"

			with open(out, "wt") as fileid:
				fileid.write(
					"/**\n"
					+ " * Copyright (c) 2018 Universite Bourgogne Franche-Comté (http://www.ubfc.fr)\n"
					+ " *                    Universite de Technologie de Belfort-Montbeliard (http://www.utbm.fr), and others.\n"
					+ " * All rights reserved. This program and the accompanying materials\n"
					+ " * are made available under the terms of the Eclipse Public License v1.0\n"
					+ " * which accompanies this distribution, and is available at\n"
					+ " * http://www.eclipse.org/legal/epl-v10.html\n"
					+ " */\n"
					+ "\n"
					+ "// CAUTION: THIS FILE IS AUTOMATICALLY GENERATED BY THE SCRIPT '../generateSystematicInheritanceTests.py'.\n"
					+ "// DO NOT CHANGE MANUALLY.\n"
					+ "\n"
					+ "package org.eclipse.xtext.xbase.tests.typesystem.inheritance.systematicInheritanceTests;\n"
					+ "\n"
					+ "import java.util.List;\n"
					+ "import com.google.common.base.Predicate;\n"
					+ "import com.google.inject.Inject;\n"
					+ "import org.eclipse.emf.ecore.EObject;\n"
					+ "import org.eclipse.xtend.core.tests.AbstractXtendTestCase;\n"
					+ "import org.eclipse.xtend.core.tests.java8.Java8RuntimeInjectorProvider;\n"
					+ "import org.eclipse.xtext.diagnostics.Severity;\n"
					+ "import org.eclipse.xtext.testing.InjectWith;\n"
					+ "import org.eclipse.xtext.testing.validation.ValidationTestHelper;\n"
					+ "import org.eclipse.xtext.validation.Issue;\n"
					+ "import org.junit.Test;\n"
					+ "import static com.google.common.collect.Iterables.*;\n"
					+ "\n"
					+ "/** Java code:\n"
					+ " * <pre><code>\n"
					+ self._toJavaDoc(java)
					+ " * </code></pre>\n"
					+ " *\n"
					+ " * <p>javac output:\n"
					+ " * <pre><code>\n")
				if ok:
					fileid.write(" * 0 error\n")
				else:
					fileid.write(self._toJavaDoc(err))
				fileid.write(
					" * </code></pre>\n"
					+ " *\n"
					+ " * @author Stephane Galland - Initial contribution and API\n"
					+ " */\n"
					+ "@InjectWith(Java8RuntimeInjectorProvider.class) \n"
					+ "@SuppressWarnings(\"all\")\n")
				dsl = self._generateDSLCode(ok, i, dcase)
				fileid.write(dsl)
			i = i + 1

		return {'total': min(max1, max2), 'success': nbSuccess, 'failure': nbFailures}



def main():
	generator = UnitTestGenerator()
	generator.addIgnorableCode("")
	stats = generator.generate()
	print("Valid code: " + str(stats['success']) + " (" + str(stats['success'] * 100.0 / stats['total']) + "%)")
	print("Invalid code: " + str(stats['failure']) + " (" + str(stats['failure'] * 100.0 / stats['total']) + "%)")

if __name__ == "__main__":
	main()

