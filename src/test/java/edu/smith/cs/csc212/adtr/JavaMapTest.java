package edu.smith.cs.csc212.adtr;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.smith.cs.csc212.adtr.real.JavaMap;

public class JavaMapTest {
	
	// You might want this; if you're using Map<String, Integer> anywhere...
	// JUnit has an assertEquals(Object, Object) and an assertEquals(int, int).
	// When you give it assertEquals(Integer, int) it doesn't know which to use (but both would be OK!)
	// This method gets around that by forcing the (int, int) version.
	void assertIntEq(int x, int y) {
		assertEquals(x, y);
	}
	
	// =========
	// Size -- how many key-value mappings are in this data structure?
	// If make empty Set, size should be 0
	@Test
	public void testEmpty() {
		MapADT<String, String> empty = new JavaMap<>();
		assertEquals(empty.size(), 0);
		//Assert.assert
	}
	
	// =========
	// Put -- put a new entry in table
	// If put (a, 1) and (b, 2) in map,
	// map size should be 2
	@Test
	public void testPut() {
		MapADT<String, String> ab = new JavaMap<>();
		ab.put("a", "1");
		ab.put("b", "2");
		assertEquals(ab.size(), 2);
	}
	
	// =========
	// Get -- get value for key k or return null
	// If put (a, 1), (b, 2), and (c, 3) in map,
	// key "c" should get "3" and "d" should get null
	@Test
	public void testGet() {
		MapADT<String, String> abc = new JavaMap<>();
		abc.put("a", "1");
		abc.put("b", "2");
		abc.put("c", "3");
		assertEquals(abc.get("c"), "3");
		assertEquals(abc.get("d"), null);
	}
	
	// =========
	// Remove -- remove mapping from table based on key k
	@Test
	public void testRemove() {
		MapADT<String, String> abcd = new JavaMap<>();
		abcd.put("a", "1");
		abcd.put("b", "2");
		abcd.put("c", "3");
		abcd.put("d", "4");
		//assertEquals(abcd.size(), 4);
		abcd.remove("d");
		assertEquals(abcd.size(), 3);

	}
}