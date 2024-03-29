/*
 * GRAL: GRAphing Library for Java(R)
 *
 * (C) Copyright 2009-2013 Erich Seifert <dev[at]erichseifert.de>,
 * Michael Seifert <mseifert[at]error-reports.org>
 *
 * This file is part of GRAL.
 *
 * GRAL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GRAL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GRAL.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.erichseifert.gral.data.filters;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import de.erichseifert.gral.TestUtils;

public class KernelTest {
	private static final double DELTA = 1e-15;

	@Test
	public void testSimpleKernel() {
		Kernel k = new Kernel(1.0);

		assertEquals(0.0, k.get(-1), DELTA);
		assertEquals(1.0, k.get( 0), DELTA);
		assertEquals(0.0, k.get( 1), DELTA);
	}

	@Test
	public void testOffset() {
		Kernel k1 = new Kernel(0, new double[] {1.0, 2.0, 3.0});
		assertEquals(0.0, k1.get(-1), DELTA);
		assertEquals(1.0, k1.get( 0), DELTA);
		assertEquals(2.0, k1.get( 1), DELTA);
		assertEquals(3.0, k1.get( 2), DELTA);
		assertEquals(0.0, k1.get( 3), DELTA);

		Kernel k2 = new Kernel(1, new double[] {1.0, 2.0, 3.0});
		assertEquals(0.0, k2.get(-2), DELTA);
		assertEquals(1.0, k2.get(-1), DELTA);
		assertEquals(2.0, k2.get( 0), DELTA);
		assertEquals(3.0, k2.get( 1), DELTA);
		assertEquals(0.0, k2.get( 2), DELTA);

		Kernel k3 = new Kernel(1.0, 2.0, 3.0, 4.0);
		assertEquals(2, k3.getOffset());
	}

	@Test
	public void testIndexes() {
		Kernel k = new Kernel(1.0, 2.0, 3.0, 4.0);

		assertEquals(-2, k.getMinIndex());
		assertEquals( 1, k.getMaxIndex());
	}

	@Test
	public void testAdd() {
		Kernel k1 = new Kernel(1.0, 2.0);
		Kernel k2 = new Kernel(3.0);

		k1.add(k2);
		assertEquals(5.0, k1.get(0), DELTA);

		k1.add(1.0);
		assertEquals(6.0, k1.get(0), DELTA);
	}

	@Test
	public void testMul() {
		Kernel k1 = new Kernel(1.0, 2.0);
		Kernel k2 = new Kernel(3.0);

		k1.mul(k2);
		assertEquals(6.0, k1.get(0), DELTA);

		k1.mul(2.0);
		assertEquals(12.0, k1.get(0), DELTA);
	}

	@Test
	public void testNormalize() {
		Kernel k = new Kernel(1.0, 1.0);

		k.normalize();
		assertEquals(0.5, k.get(-1), DELTA);
		assertEquals(0.5, k.get( 0), DELTA);
	}

	@Test
	public void testNegate() {
		Kernel k = new Kernel(1.0, 1.0);

		k.negate();
		assertEquals(-1.0, k.get(-1), DELTA);
		assertEquals(-1.0, k.get( 0), DELTA);
	}

	@Test
	public void testSerialization() throws IOException, ClassNotFoundException {
		Kernel original = new Kernel(1.0, 2.0, 3.0, 4.0);
		Kernel deserialized = TestUtils.serializeAndDeserialize(original);

    	assertEquals(original.getOffset(), deserialized.getOffset());
    	assertEquals(original.size(), deserialized.size());
    	for (int i = 0; i < original.size(); i++) {
    		assertEquals(
				String.format("Wrong value at index %d.", i),
				original.get(i), deserialized.get(i), DELTA);
		}
    }
}
