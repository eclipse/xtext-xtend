/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.lib

import org.eclipse.xtend.lib.macro.file.Path
import org.junit.Test

import static org.junit.Assert.*

/**
 * @author Sven Efftinge - Initial contribution and API
 */
class PathTest {
	
	@Test def void testPath() {
		val p = new Path("/foo/bar/baz.txt")
		assertTrue(p.absolute)
		assertEquals("baz.txt", p.lastSegment)
		assertEquals("txt", p.fileExtension)
		assertEquals('foo', p.segments.head)
		assertEquals('bar', p.segments.get(1))
		assertEquals(3, p.segments.size)
		assertEquals("/foo/bar/baz.txt", p.toString)
	}
	
	@Test def void testPathConstructor() {
		assertEquals("/foo/bar/baz.txt", new Path("/foo/bar/baz.txt").toString())
		assertEquals("/foo/bar/baz.txt", new Path("/foo////bar/   baz.txt").toString())
		assertEquals("foo/bar/baz.txt", new Path("foo/bar/baz.txt").toString())
		assertEquals("foo/bar/baz.txt", new Path("    foo   /bar/baz.txt").toString())
		assertEquals("/bar/baz.txt", new Path("/foo/../bar/baz.txt").toString())
		assertEquals("bar/baz.txt", new Path("foo/../bar/baz.txt").toString())
		assertEquals("../bar/baz.txt", new Path("../bar/baz.txt").toString())
		assertEquals("../..", new Path("../..").toString())
		assertEquals("../..", new Path("../foo/.././..").toString())
	}
	
	@Test def void testPathStartsWith() {
		assertTrue(new Path("/foo/bar/baz.txt").startsWith(new Path("/")))
		assertTrue(new Path("/foo/bar/baz.txt").startsWith(new Path("/foo")))
		assertTrue(new Path("/foo/bar/baz.txt").startsWith(new Path("/foo/bar")))
		assertTrue(new Path("/foo/bar/baz.txt").startsWith(new Path("/foo/bar/baz.txt")))
		assertFalse(new Path("/foo/bar/baz.txt").startsWith(new Path("foo")))
		assertFalse(new Path("/foo/bar/baz.txt").startsWith(new Path("foo/bar/baz.txt")))
		assertFalse(new Path("/foo/bar/baz.txt").startsWith(new Path("/foo/bar/baz")))
	}
	
	@Test def void testPathGetAbsolutePath() {
		assertEquals(new Path("/foo"), new Path("/foo/bar/baz.txt").getAbsolutePath("../.."))
	}
	
	@Test def void testAppend() {
		assertEquals(new Path("/foo/bar"), new Path("/foo").append("bar"))
		assertEquals(new Path("/foo/bar"), new Path("/foo").append("/bar"))
		assertEquals(new Path("foo/bar"), new Path("foo").append("/bar"))
		assertEquals(new Path("foo"), new Path("foo/bar").append(".."))
		assertEquals(new Path("foo/file.txt"), new Path("foo/bar").append("../xfoo/../file.txt"))
	}
	
	@Test def void testRelativize() {
		assertEquals(new Path("foo"), new Path("/hubba/bubba/foo").relativize("/hubba/bubba"))
		assertEquals(new Path("foo"), new Path("hubba/bubba/foo").relativize("hubba/bubba"))
		try {
			new Path("hubba/bubba/foo").relativize("/hubba/bubba")
		} catch (IllegalArgumentException e) {
			// expected
		}
		try {
			new Path("/hubba/bubba/foo").relativize("hubba/bubba")
		} catch (IllegalArgumentException e) {
			// expected
		}
		assertNull(new Path("/foo").relativize("/bar"))
	}
	
	@Test def void testRelativizeBothDirections() {
		val base = new Path("/hubba/bubba")
		val child = new Path("/hubba/bubba/bar")
		assertEquals(new Path("bar"), base.relativize(child))
		assertEquals(new Path("bar"), child.relativize(base))
	}
}