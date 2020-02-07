/**
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Author - Sebastian Zarnekow
 * See https://github.com/szarnekow/xtend-euler
 */
package euler;

import euler.SolutionTriangle;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.InputOutput;

/**
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
 * 
 * 3
 * 7 4
 * 2 4 6
 * 8 5 9 3
 * 
 * That is, 3 + 7 + 4 + 9 = 23.
 * 
 * Find the maximum total from top to bottom of the triangle below:
 * 
 * 75
 * 95 64
 * 17 47 82
 * 18 35 87 10
 * 20 04 82 47 65
 * 19 01 23 75 03 34
 * 88 02 77 73 07 63 67
 * 99 65 04 28 06 16 70 92
 * 41 41 26 56 83 40 80 70 33
 * 41 48 72 33 47 32 37 16 94 29
 * 53 71 44 65 25 43 91 52 97 51 14
 * 70 11 33 28 77 73 17 78 39 68 17 57
 * 91 71 52 38 17 14 91 43 58 50 27 29 48
 * 63 66 04 68 89 53 67 30 73 16 69 87 40 31
 * 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
 * 
 * NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route. However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot be solved by brute force, and requires a clever method! ;o)
 * 
 * @see http://projecteuler.net/problem=18
 */
@SuppressWarnings("all")
public class Solution_018 {
  public static void main(final String[] args) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("75");
    _builder.newLine();
    _builder.append("95 64");
    _builder.newLine();
    _builder.append("17 47 82");
    _builder.newLine();
    _builder.append("18 35 87 10");
    _builder.newLine();
    _builder.append("20 04 82 47 65");
    _builder.newLine();
    _builder.append("19 01 23 75 03 34");
    _builder.newLine();
    _builder.append("88 02 77 73 07 63 67");
    _builder.newLine();
    _builder.append("99 65 04 28 06 16 70 92");
    _builder.newLine();
    _builder.append("41 41 26 56 83 40 80 70 33");
    _builder.newLine();
    _builder.append("41 48 72 33 47 32 37 16 94 29");
    _builder.newLine();
    _builder.append("53 71 44 65 25 43 91 52 97 51 14");
    _builder.newLine();
    _builder.append("70 11 33 28 77 73 17 78 39 68 17 57");
    _builder.newLine();
    _builder.append("91 71 52 38 17 14 91 43 58 50 27 29 48");
    _builder.newLine();
    _builder.append("63 66 04 68 89 53 67 30 73 16 69 87 40 31");
    _builder.newLine();
    _builder.append("04 62 98 27 23 09 70 98 73 93 38 53 60 04 23");
    _builder.newLine();
    String _string = _builder.toString();
    SolutionTriangle _solutionTriangle = new SolutionTriangle(_string);
    InputOutput.<SolutionTriangle>println(_solutionTriangle);
  }
}
