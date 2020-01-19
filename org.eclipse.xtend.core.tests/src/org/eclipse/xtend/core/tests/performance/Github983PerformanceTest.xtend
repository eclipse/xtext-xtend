/*******************************************************************************
 * Copyright (c) 2020 Sebastian Zarnekow and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.performance

import java.util.List
import java.util.Map
import org.eclipse.xtend.core.tests.AbstractXtendTestCase
import org.junit.Test

import static org.eclipse.xtext.util.internal.Stopwatches.*

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
class Github983PerformanceTest extends AbstractXtendTestCase {
	
	def void doCompile(String file) {
		val map = getDependencies();
		val List<String> files = newArrayList() 
		files.addAll(map.values.map[toString])
		files.add(file)
		val task = forTask("Github983PerformanceTest.doCompile")
		task.start
		files(true, files as String[])
		task.stop
	}
	
	@Test def void compileTest1c() {
		doCompile('''
			package gh983;
			
			import static gh983.Assert1.*
			
			import java.util.Calendar
			
			class Test1 {
			
				def test() {
					var actual = DFH.fDRUTSOU(5 * DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.D)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.MINUTE) +
													13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.D)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
												L.D)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.HOUR_OF_DAY) +
													42 * DH.gM(
														Calendar.SECOND
													) + 321, 10000, Calendar.MILLISECOND, L.D)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.D)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.D)
					assertEquals("foo",
						actual, "foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							(2000 * DH.gM(Calendar.YEAR)), 1, Calendar.MILLISECOND,
							L.D), "foo")
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.YEAR) +
													2 * DH.gM(Calendar.DAY_OF_YEAR) +
													3 * DH.gM(Calendar.HOUR_OF_DAY) +
													4 * DH.gM(Calendar.MINUTE) +
													5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
												L.D)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(5 *
												DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.F)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.MINUTE) +
													13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.F)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
												L.F)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.HOUR_OF_DAY) +
													42 * DH.gM(
														Calendar.SECOND
													) + 321, 10000, Calendar.MILLISECOND, L.F)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.F)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.F)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(2000 *
												DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.F)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.YEAR) +
													2 * DH.gM(Calendar.DAY_OF_YEAR) +
													3 * DH.gM(Calendar.HOUR_OF_DAY) +
													4 * DH.gM(Calendar.MINUTE) +
													5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
												L.F)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(5 *
												DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.I)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.MINUTE) +
													13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.I)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
												L.I)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.HOUR_OF_DAY) +
													42 * DH.gM(
														Calendar.SECOND
													) + 321, 10000, Calendar.MILLISECOND, L.I)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.I)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.I)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(2000 *
												DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.I)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.YEAR) +
													2 * DH.gM(Calendar.DAY_OF_YEAR) +
													3 * DH.gM(Calendar.HOUR_OF_DAY) +
													4 * DH.gM(Calendar.MINUTE) +
													5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
												L.I)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(5 *
												DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.E)
					assertEquals("foo",
						actual,
						"5 foo")
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.MINUTE) +
													13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.E)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
												L.E)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.HOUR_OF_DAY) +
													42 * DH.gM(
														Calendar.SECOND
													) + 321, 10000, Calendar.MILLISECOND, L.E)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.E)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.E)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(2000 *
												DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.E)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.YEAR) +
													2 * DH.gM(Calendar.DAY_OF_YEAR) +
													3 * DH.gM(Calendar.HOUR_OF_DAY) +
													4 * DH.gM(Calendar.MINUTE) +
													5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
												L.E)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(1, 0, Calendar.SECOND, L.D)
					assertEquals("foo",
						actual, "")
					actual = DFH.fDRUTSOU(1, -10, Calendar.SECOND, L.D)
					assertEquals("foo",
						actual, "")
					actual = DFH.fDRUTSOU(1, 10, 0, L.D)
					assertEquals("foo", actual, "")
					actual = DFH.fDRUTSOU(1, 10, Calendar.SECOND, null)
					assertEquals("foo", actual, "")
					actual = DFH.fDRUTSOU(-524944, 10, Calendar.SECOND, L.D)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(5 *
												DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.D)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.MINUTE) +
													13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.D)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
												L.D)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.HOUR_OF_DAY) +
													42 * DH.gM(
														Calendar.SECOND
													) + 321, 10000, Calendar.MILLISECOND, L.D)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.D)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.D)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(
												(2000 * DH.gM(Calendar.YEAR)), 1, Calendar.MILLISECOND,
												L.D)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.YEAR) +
													2 * DH.gM(Calendar.DAY_OF_YEAR) +
													3 * DH.gM(Calendar.HOUR_OF_DAY) +
													4 * DH.gM(Calendar.MINUTE) +
													5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
												L.D)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(5 *
												DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.F)
					assertEquals("foo",
						actual,
						"5 foo")
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.MINUTE) +
													13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.F)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
												L.F)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.HOUR_OF_DAY) +
													42 * DH.gM(
														Calendar.SECOND
													) + 321, 10000, Calendar.MILLISECOND, L.F)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.F)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.F)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(2000 *
												DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.F)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.YEAR) +
													2 * DH.gM(Calendar.DAY_OF_YEAR) +
													3 * DH.gM(Calendar.HOUR_OF_DAY) +
													4 * DH.gM(Calendar.MINUTE) +
													5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
												L.F)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(5 *
												DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.I)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.MINUTE) +
													13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.I)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
												L.I)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.HOUR_OF_DAY) +
													42 * DH.gM(
														Calendar.SECOND
													) + 321, 10000, Calendar.MILLISECOND, L.I)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.I)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.I)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(2000 *
												DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.I)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.YEAR) +
													2 * DH.gM(Calendar.DAY_OF_YEAR) +
													3 * DH.gM(Calendar.HOUR_OF_DAY) +
													4 * DH.gM(Calendar.MINUTE) +
													5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
												L.I)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(5 *
												DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.E)
					assertEquals("foo",
						actual,
						"5 foo")
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.MINUTE) +
													13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.E)
					assertEquals("foo",
						actual,
						"foo")
					actual = DFH.fDRUTSOU(
												6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
												L.E)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.HOUR_OF_DAY) +
													42 * DH.gM(
														Calendar.SECOND
													) + 321, 10000, Calendar.MILLISECOND, L.E)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.E)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.E)
					assertEquals("foo",
						actual, "foo")
					actual = DFH.fDRUTSOU(2000 *
												DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.E)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(
												1 * DH.gM(Calendar.YEAR) +
													2 * DH.gM(Calendar.DAY_OF_YEAR) +
													3 * DH.gM(Calendar.HOUR_OF_DAY) +
													4 * DH.gM(Calendar.MINUTE) +
													5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
												L.E)
					assertEquals(
						"foo",
						actual,
						"foo"
					)
					actual = DFH.fDRUTSOU(1, 0, Calendar.SECOND, L.D)
					assertEquals("foo",
						actual, "")
					actual = DFH.fDRUTSOU(1, -10, Calendar.SECOND, L.D)
					assertEquals("foo",
						actual, "")
					actual = DFH.fDRUTSOU(1, 10, 0, L.D)
					assertEquals("foo", actual, "")
					actual = DFH.fDRUTSOU(1, 10, Calendar.SECOND, null)
					assertEquals("foo", actual, "")
					actual = DFH.fDRUTSOU(-524944, 10, Calendar.SECOND, L.D)
					assertEquals("foo",
						actual,
						"foo")
				}
			
			}
			
		''')
	}
	
	@Test def void compileTest1b() {
		doCompile('''
			package gh983;
			
			import static gh983.Assert1.*
			
			import java.util.Calendar
			
			class Test1 {
			
				def test() {
					assertEquals("foo",
						DFH.fDRUTSOU(1, 1, Calendar.MINUTE, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10, Calendar.SECOND, L.D),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 2, Calendar.HOUR_OF_DAY,
							L.D),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 10000, Calendar.MILLISECOND, L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.D), "foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 1, Calendar.MILLISECOND,
							L.D), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 1, Calendar.MILLISECOND,
							L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 1, Calendar.MINUTE, L.F),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10, Calendar.SECOND, L.F),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 2, Calendar.HOUR_OF_DAY,
							L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10000, Calendar.MILLISECOND, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 1, Calendar.MILLISECOND, L.F),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 1, Calendar.MILLISECOND,
							L.F),
						"foo"
					)
							assertEquals("foo",
						DFH.fDRUTSOU(1, 1, Calendar.MINUTE, L.I),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10, Calendar.SECOND, L.I),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 2, Calendar.HOUR_OF_DAY,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10000, Calendar.MILLISECOND, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 1, Calendar.MILLISECOND, L.I),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 1, Calendar.MILLISECOND,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 1, Calendar.MINUTE, L.E),
						"5 foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10, Calendar.SECOND, L.E),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 2, Calendar.HOUR_OF_DAY,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10000, Calendar.MILLISECOND, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.E),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 1, Calendar.MILLISECOND,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 0, Calendar.SECOND, L.D), "")
					assertEquals("foo",
						DFH.fDRUTSOU(1, -10, Calendar.SECOND, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, 0, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, Calendar.SECOND, null), "")
					assertEquals("foo",
						DFH.fDRUTSOU(-524944, 10, Calendar.SECOND, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10, Calendar.SECOND, L.D),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 2, Calendar.HOUR_OF_DAY,
							L.D),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 10000, Calendar.MILLISECOND, L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.D), "foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 1, Calendar.MILLISECOND,
							L.D), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 1, Calendar.MILLISECOND,
							L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 1, Calendar.MINUTE, L.F),
						"5 foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10, Calendar.SECOND, L.F),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 2, Calendar.HOUR_OF_DAY,
							L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10000, Calendar.MILLISECOND, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.F),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 1, Calendar.MILLISECOND,
							L.F),
						"foo"
					)
							assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.I),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10, Calendar.SECOND, L.I),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 2, Calendar.HOUR_OF_DAY,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10000, Calendar.MILLISECOND, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 1, Calendar.MILLISECOND, L.I),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 1, Calendar.MILLISECOND,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 1, Calendar.MINUTE, L.E),
						"5 foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10, Calendar.SECOND, L.E),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 2, Calendar.HOUR_OF_DAY,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1, 10000, Calendar.MILLISECOND, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 1, Calendar.MILLISECOND, L.E),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1, 1, Calendar.MILLISECOND,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 0, Calendar.SECOND, L.D), "")
					assertEquals("foo",
						DFH.fDRUTSOU(1, -10, Calendar.SECOND, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, 0, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, Calendar.SECOND, null), "")
					assertEquals("foo",
						DFH.fDRUTSOU(-524944, 10, Calendar.SECOND, L.D),
						"foo")
				}
			
			}
			
		''')
	}
	
	@Test def void compileTest1a() {
		doCompile('''
			package gh983;
			
			import static gh983.Assert1.*
			
			import java.util.Calendar
			
			class Test1 {
			
				def test() {
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.D),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.D),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.D), "foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							(2000 * DH.gM(Calendar.YEAR)), 1, Calendar.MILLISECOND,
							L.D), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.F),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.F),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.F),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.F),
						"foo"
					)
							assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.I),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.I),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.I),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.E),
						"5 foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.E),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.E),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 0, Calendar.SECOND, L.D), "")
					assertEquals("foo",
						DFH.fDRUTSOU(1, -10, Calendar.SECOND, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, 0, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, Calendar.SECOND, null), "")
					assertEquals("foo",
						DFH.fDRUTSOU(-524944, 10, Calendar.SECOND, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.D),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.D),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.D), "foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							(2000 * DH.gM(Calendar.YEAR)), 1, Calendar.MILLISECOND,
							L.D), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.F),
						"5 foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.F),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.F),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.F),
						"foo"
					)
							assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.I),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.I),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.I),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.E),
						"5 foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.E),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.E),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 0, Calendar.SECOND, L.D), "")
					assertEquals("foo",
						DFH.fDRUTSOU(1, -10, Calendar.SECOND, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, 0, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, Calendar.SECOND, null), "")
					assertEquals("foo",
						DFH.fDRUTSOU(-524944, 10, Calendar.SECOND, L.D),
						"foo")
				}
			
			}
			
		''')
	}
	
	@Test def void compileTest2() {
		doCompile('''
			package gh983;
			
			import static gh983.Assert2.*
			
			import java.util.Calendar
			
			class Test2 {
			
				def test() {
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.D),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.D),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.D), "foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							(2000 * DH.gM(Calendar.YEAR)), 1, Calendar.MILLISECOND,
							L.D), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.F),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.F),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.F),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.F),
						"foo"
					)
							assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.I),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.I),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.I),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.E),
						"5 foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.E),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.E),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 0, Calendar.SECOND, L.D), "")
					assertEquals("foo",
						DFH.fDRUTSOU(1, -10, Calendar.SECOND, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, 0, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, Calendar.SECOND, null), "")
					assertEquals("foo",
						DFH.fDRUTSOU(-524944, 10, Calendar.SECOND, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.D),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.D),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.D),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.D), "foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							(2000 * DH.gM(Calendar.YEAR)), 1, Calendar.MILLISECOND,
							L.D), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.D),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.F),
						"5 foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.F),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.F),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.F), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.F),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.F),
						"foo"
					)
							assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.I),
						"foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.I),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.I), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.I),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.I),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(5 *
							DH.gM(Calendar.MINUTE), 1, Calendar.MINUTE, L.E),
						"5 foo")
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.MINUTE) +
								13 * DH.gM(Calendar.SECOND), 10, Calendar.SECOND, L.E),
						"foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							6 * DH.gM(Calendar.HOUR_OF_DAY) + 1, 2, Calendar.HOUR_OF_DAY,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.HOUR_OF_DAY) +
								42 * DH.gM(
									Calendar.SECOND
								) + 321, 10000, Calendar.MILLISECOND, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(1, 7, Calendar.DAY_OF_YEAR, L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(0, 1, Calendar.YEAR, L.E), "foo")
					assertEquals(
						"foo",
						DFH.fDRUTSOU(2000 *
							DH.gM(Calendar.YEAR), 1, Calendar.MILLISECOND, L.E),
						"foo"
					)
					assertEquals(
						"foo",
						DFH.fDRUTSOU(
							1 * DH.gM(Calendar.YEAR) +
								2 * DH.gM(Calendar.DAY_OF_YEAR) +
								3 * DH.gM(Calendar.HOUR_OF_DAY) +
								4 * DH.gM(Calendar.MINUTE) +
								5 * DH.gM(Calendar.SECOND) + 6, 1, Calendar.MILLISECOND,
							L.E),
						"foo"
					)
					assertEquals("foo",
						DFH.fDRUTSOU(1, 0, Calendar.SECOND, L.D), "")
					assertEquals("foo",
						DFH.fDRUTSOU(1, -10, Calendar.SECOND, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, 0, L.D), "")
					assertEquals("foo", DFH.fDRUTSOU(1, 10, Calendar.SECOND, null), "")
					assertEquals("foo",
						DFH.fDRUTSOU(-524944, 10, Calendar.SECOND, L.D),
						"foo")
				}
			
			}

		''')
	}
	
	def Map<String,? extends CharSequence> getDependencies(){
		newHashMap(assert1, assert2, dfh, dh, l)
	}
	
	def assert1() {
		"gh983/Assert1.xtend"->'''
			package gh983;
			class Assert1 {
				protected new() {
				}
				def static void assertEquals(Object expected, Object actual) {
				}
				def static void assertEquals(String message, double expected, double actual, double delta) {
				}
				def static void assertEquals(String message, float expected, float actual, float delta) {
				}
				def static void assertEquals(long expected, long actual) {
					assertEquals(null, expected, actual)
				}
				def static void assertEquals(String message, long expected, long actual) {
				}
				def static void assertEquals(double expected, double actual) {
				}
				def static void assertEquals(String message, double expected, double actual) {
				}
				def static void assertEquals(double expected, double actual, double delta) {
					assertEquals(null, expected, actual, delta)
				}
				def static void assertEquals(float expected, float actual, float delta) {
					assertEquals(null, expected, actual, delta)
				}
				def static void assertEquals(String message, Object[] expecteds, Object[] actuals) {
				}
				def static void assertEquals(String message, Object expected, Object actual) {
				}
				def static void assertEquals(Object[] expecteds, Object[] actuals) {
				}
			}
		'''
	}
	
	def assert2() {
		"gh983/Assert2.xtend"->'''
			package gh983;
			class Assert2 {
				def static void assertEquals(String message, Object expected, Object actual) {
				}
			}
		'''
	}
	
	def dfh() {
		"gh983/DFH.xtend"->'''
			package gh983;
			class DFH {
			    def static String fDRUTSOU(long a, long b, int c, L d) {
			        return null;
			    }
			    def static long roundtimeToSizeOfUnit(long time, long size, int unit) {
			        return 0l;
			    }
			}
		'''
	}
	
	def dh() {
		"gh983/DH.xtend"->'''
			package gh983;
			class DH {
			    def static long gM(int unit) { return 0l; }
			}
		'''
	}
	
	def l() {
		"gh983/L.xtend"->'''
			package gh983;
			enum L {
			    D, F, I, E
			}
		'''
	}
}