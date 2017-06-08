/**
 * CellProfiler is distributed under the GNU General Public License.
 * See the accompanying file LICENSE for details.
 *
 * Copyright (c) 2003-2009 Massachusetts Institute of Technology
 * Copyright (c) 2009-2015 Broad Institute
 * All rights reserved.
 * 
 * Please see the AUTHORS file for credits.
 * 
 * Website: http://www.cellprofiler.org
 */
package org.cellprofiler.imageset;

import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Lee Kamentsky
 *
 */
public class TestRegexpMetadataExtractor {
	private void testSomething(String pattern, String input, String [][] expected) {
		RegexpMetadataExtractor x = new RegexpMetadataExtractor(pattern);
		Map<String, String> map = x.extract(input);
		Set<String> expectedKeys = new HashSet<String>();
		for (String [] kv:expected) {
			assertTrue(map.containsKey(kv[0]));
			assertEquals(kv[1], map.get(kv[0]));
			expectedKeys.add(kv[0]);
		}
		for (String key:map.keySet()) {
			assertTrue(expectedKeys.contains(key));
		}
	}
	
	@Test
	public void testNoKV() {
		testSomething("foo", "foobar", new String [0][]);
	}
	@Test
	public void testNoMatch() {
		testSomething("foo", "bar", new String[0][]);
	}
	@Test
	public void testOneKV() {
		testSomething("_(?P<WellName>[A-Z][0-9]{2})_", "Plate_A01_.png", 
				new String[][] { { "WellName", "A01"}});
		testSomething("_(?P<WellName>[A-Z][0-9]{2})_", "Plate_AXY_.png", new String[0][]);
	}
	@Test
	public void testParseBackslash() {
		testSomething("_\\((?P<WellName>[A-Z][0-9]{2})\\)_", "Plate_(A01)_.png",
				new String[][] { { "WellName", "A01"}});
		testSomething("Plate\\\\(?P<WellName>[A-Z][0-9]{2})", "Plate\\A01.png",
				new String[][] {{ "WellName", "A01"}});
	}
	@Test
	public void testTwoKV() {
		testSomething("_(?P<WellRow>[A-Z])(?P<WellColumn>[0-9]{2})_", "Plate_A01_.png",
				new String[][] {{"WellRow", "A"}, {"WellColumn", "01"}});
	}
	@Test
	public void testIAmAskingForTrouble() {
		testSomething("_(?P<WellName>[A-Z][0-9]{2})|(?P<FamousCowgirl>Annie +Oakley)_",
				"Plate_A01.jpg", new String [][] { {"WellName", "A01"}});
		testSomething("_(?P<WellName>[A-Z][0-9]{2})|(?P<FamousCowgirl>Annie +Oakley)_",
				"_Annie Oakley_.jpg", new String [][] { {"FamousCowgirl", "Annie Oakley"}});
	}
	@Test
	public void testNonKeywordMatch() {
		testSomething(
				"(Plate|Plato)_(?P<Well>[A-Z][0-9]{2})",
				"Plate_A01.png",
				new String[][] {{ "Well", "A01"}});
		testSomething(
				"_((?P<Well>[A-Z][0-9]{2}))",
				"Plate_A01.png",
				new String[][] {{ "Well", "A01"}});
	}
	@Test
	public void testNonCapturing() {
		// Regression test of issue 1843
		testSomething(
				"^(?P<Experiment>[0-9]+).+?(?=_p[0-9])_p(?P<Position>[0-9]+)t(?P<Time>[0-9]+)c(?P<ChannelNumber>[0-9]+)",
				"121201_RCM_HBC_cell density_complete_p01t00001c01.tif",
				new String [][] {
						{ "Experiment", "121201" },
						{ "Position", "01" },
						{ "Time", "00001" },
						{ "ChannelNumber", "01" }
				});
	}
}
