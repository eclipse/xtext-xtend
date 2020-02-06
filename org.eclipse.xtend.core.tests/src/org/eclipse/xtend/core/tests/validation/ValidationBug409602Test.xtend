/*******************************************************************************
 * Copyright (c) 2013, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.validation

import com.google.inject.Inject
import org.eclipse.xtend.core.tests.AbstractXtendTestCase
import org.eclipse.xtend.core.xtend.XtendFile
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.eclipse.xtext.xbase.XbasePackage
import org.eclipse.xtext.xbase.validation.IssueCodes
import org.junit.Test

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
class ValidationBug409602Test extends AbstractXtendTestCase {
	
	@Inject ValidationTestHelper helper
	@Inject ParseHelper<XtendFile> parser
	
	@Test def void test_01() {
		val file = parser.parse('''
			class C {
				def m() {
					if ("" == null) {
						return
					}
					1
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XNUMBER_LITERAL, IssueCodes.INVALID_INNER_EXPRESSION)
	}
	
	@Test def void test_02() {
		val file = parser.parse('''
			class C {
				def int m() {
					if ("" == null) {
						return
					}
					1
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN)
	}
	
	@Test def void test_03() {
		val file = parser.parse('''
			class C {
				def void m() {
					if ("" == null) {
						return
					}
					1
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XNUMBER_LITERAL, IssueCodes.INVALID_INNER_EXPRESSION)
	}
	
	@Test def void test_04() {
		val file = parser.parse('''
			class C {
				def m() {
					[|
						if ("" == null) {
							return
						}
						1
					]
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XNUMBER_LITERAL, IssueCodes.INVALID_INNER_EXPRESSION)
	}
	
	@Test def void test_05() {
		val file = parser.parse('''
			class C {
				def m() {
					val ()=>int res = [|
						if ("" == null) {
							return
						}
						1
					]
					return res
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN)
	}
	
	@Test def void test_06() {
		val file = parser.parse('''
			class C {
				def m() {
					val ()=>void res = [|
						if ("" == null) {
							return
						}
						1
					]
					return res
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XNUMBER_LITERAL, IssueCodes.INVALID_INNER_EXPRESSION)
	}
	
	@Test def void test_07() {
		val file = parser.parse('''
			class C {
				def int m() {
					if ("" == null) {
						return
					} else {
						return 1
					}
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN)
	}
	
	@Test def void test_08() {
		val file = parser.parse('''
			class C {
				def m() {
					if ("" == null) {
						return
					} else {
						return 1
					}
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN, "Void functions cannot return a value.")
	}
	
	@Test def void test_09() {
		val file = parser.parse('''
			class C {
				def void m() {
					if ("" == null) {
						return
					} else {
						return 1
					}
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN, "Void functions cannot return a value.")
	}
	
	@Test def void test_10() {
		val file = parser.parse('''
			class C {
				def int m() {
					if ("" == null) {
						return 1
					} else {
						return
					}
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN)
	}
	
	@Test def void test_11() {
		val file = parser.parse('''
			class C {
				def m() {
					if ("" == null) {
						return 1
					} else {
						return
					}
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN, "Void functions cannot return a value.")
	}
	
	@Test def void test_12() {
		val file = parser.parse('''
			class C {
				def void m() {
					if ("" == null) {
						return 1
					} else {
						return
					}
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN, "Void functions cannot return a value.")
	}
	
	@Test def void test_13() {
		val file = parser.parse('''
			class C {
				def m() {
					val ()=>int res = [|
						if ("" == null) {
							return
						} else {
							return 1
						}
					]
					res
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN)
	}
	
	@Test def void test_14() {
		val file = parser.parse('''
			class C {
				def m() {
					[|
						if ("" == null) {
							return
						} else {
							return 1
						}
					]
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN, "Void functions cannot return a value.")
	}
	
	@Test def void test_15() {
		val file = parser.parse('''
			class C {
				def void m() {
					val ()=>void res = [|
						if ("" == null) {
							return
						} else {
							return 1
						}
					]
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN, "Void functions cannot return a value.")
	}
	
	@Test def void test_16() {
		val file = parser.parse('''
			class C {
				def m() {
					val ()=>int res = [|
						if ("" == null) {
							return 1
						} else {
							return
						}
					]
					res
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN)
	}
	
	@Test def void test_17() {
		val file = parser.parse('''
			class C {
				def m() {
					[|
						if ("" == null) {
							return 1
						} else {
							return
						}
					]
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN, "Void functions cannot return a value.")
	}
	
	@Test def void test_18() {
		val file = parser.parse('''
			class C {
				def m() {
					val ()=>void res = [|
						if ("" == null) {
							return 1
						} else {
							return
						}
					]
					res
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN, "Void functions cannot return a value.")
	}
	
	@Test def void test_19() {
		val file = parser.parse('''
			class C {
				def void m() {
					while(true)
						return ''
				}
			}
		''')
		helper.assertError(file, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN, "Void functions cannot return a value.")
	}
	
	@Test def void test_20() {
		val file = parser.parse('''
			class C {
				def m() {
					while(true)
						return ''
				}
			}
		''')
		helper.assertNoErrors(file)
	}
	
	@Test def void test_21() {
		val file = parser.parse('''
			class C {
				def m(boolean b) {
					while(b)
						return ''
					return ''
				}
			}
		''')
		helper.assertNoErrors(file)
	}
	
	@Test def void test_22() {
		val file = parser.parse('''
			class C {
				def String m() {
					while(true)
						return ''
				}
			}
		''')
		helper.assertNoErrors(file)
	}
	
	@Test def void test_23() {
		val file = parser.parse('''
			class C {
				def String m(boolean b) {
					while(b)
						return ''
					return ''
				}
			}
		''')
		helper.assertNoErrors(file)
	}
}