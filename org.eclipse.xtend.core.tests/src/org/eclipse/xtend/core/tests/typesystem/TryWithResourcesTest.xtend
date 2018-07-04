/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.typesystem

import org.eclipse.xtend.core.tests.AbstractXtendTestCase
import org.eclipse.xtext.xbase.XBlockExpression
import org.eclipse.xtext.xbase.XTryCatchFinallyExpression
import org.junit.Test

/**
 * @author epoell - Initial contribution and API
 */
class TryWithResourcesTest extends AbstractXtendTestCase {
	
	val input = '''
				def normalTry(String path) {
					val br = new BufferedReader(new FileReader(path));
					
					//TryCatchFinallyExpression without Resources
					try
						return br.readLine()
					finally
						if(br !== null)
							br.close()
				}
				'''	
	
	val input2 = '''
				def foo(String path) {
					try (val fr = new FileReader(path); val buffy = new BufferedReader(fr)) 
						buffy.readLine()
					finally
						fr.close()
				}
				'''
	val block = input.function as XBlockExpression
	val expressions = block.expressions
	
	
	@Test
	def void internalLinking_Types(){
		
		for (exp : expressions) {
			if (exp instanceof XTryCatchFinallyExpression && (exp as XTryCatchFinallyExpression).resources.nullOrEmpty) {
				// TODO Eva Check resources
			}
		}
		
		val typeFr = null
		val typeBuffy = null
		
		
	}
	
	/**
	 * try (val fr = new FileReader(path); val buffy = new BufferedReader(fr)) 
			buffy.readLine()
	 */
}