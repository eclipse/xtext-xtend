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

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.eclipse.xtext.xbase.lib.InputOutput;

/**
 * You are given the following information, but you may prefer to do some research for yourself.
 * 
 *  1 Jan 1900 was a Monday.
 *  Thirty days has September,
 *  April, June and November.
 *  All the rest have thirty-one,
 *  Saving February alone,
 *  Which has twenty-eight, rain or shine.
 *  And on leap years, twenty-nine.
 *  A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 * 
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 * 
 * @see http://projecteuler.net/problem=19
 */
@SuppressWarnings("all")
public class Solution_019 {
  public static void main(final String[] args) {
    final Calendar calendar = GregorianCalendar.getInstance();
    calendar.set(1901, 1, 1);
    int result = 0;
    do {
      {
        int _get = calendar.get(Calendar.DAY_OF_WEEK);
        boolean _equals = (_get == Calendar.SUNDAY);
        if (_equals) {
          result = (result + 1);
        }
        calendar.add(Calendar.MONTH, 1);
      }
    } while((calendar.get(Calendar.YEAR) <= 2000));
    InputOutput.<Integer>println(Integer.valueOf(result));
  }
}
