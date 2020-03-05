package edu.smith.cs.csc212.adtr;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.smith.cs.csc212.adtr.real.JavaMap;
import edu.smith.cs.csc212.adtr.real.JavaSet;

public class JavaMapTest {
	
	// You might want this; if you're using Map<String, Integer> anywhere...
	// JUnit has an assertEquals(Object, Object) and an assertEquals(int, int).
	// When you give it assertEquals(Integer, int) it doesn't know which to use (but both would be OK!)
	// This method gets around that by forcing the (int, int) version.
	void assertIntEq(int x, int y) {
		assertEquals(x, y);
	}
	
	// =========
	// Size -- how many key-value mappings 
	// are in this data structure? 
	
	// If make empty Map, size should be 0
	@Test
	public void testEmpty() {
		MapADT<String, String> empty = new JavaMap<>();
		assertEquals(empty.size(), 0);
	}
	
	/* If put ("x", "1") and ("y", "2") in Map,
	 * it should have a size of 2
	 */
	@Test
	public void testSize() {
		MapADT<String, String> size = new JavaMap<>();
		size.put("x", "1");
		size.put("y", "2");
		assertEquals(size.size(), 2);
	}
		
	// =========
	// Put -- put a new entry in table
	
	// If add ("A", "1"), map should be size of 1
	@Test
	public void testPutOnce() {
		MapADT<String, String> once = new JavaMap<>();
		once.put("A", "1");
		assertEquals(once.size(), 1);
	}
	
	/* If put ("a", "1") and ("b", "2") in map, 
	 * map size should be 2
	 */
	@Test
	public void testPut2() {
		MapADT<String, String> ab = new JavaMap<>();
		ab.put("a", "1");
		ab.put("b", "2");
		assertEquals(ab.size(), 2);
	}
		
	/* If put ("A", "1"); ("B", "2"); ("B", "3"), map size should be 2 and get
	 * should give us "1" for "A" and "3" for "B"
	 */
	@Test
	public void testMultiplePut() {
		MapADT<String, String> three = new JavaMap<>();
		three.put("A", "1");
		three.put("B", "2");
		three.put("B", "3");
		assertEquals(three.size(), 2);
		assertEquals(three.get("A"), "1");
		assertEquals(three.get("B"), "3");
	}
	
	// =========
	// Get -- get value for key k or return null
	
	/* If put ("a", "1"); ("b", "2");
	 * ("c", "3") in map, key "c" should get "3"
	 * and "d" should get null
	 */
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
	
	/* If put ("a", "1"); ("b","2"); ("c", "3"); 
	 * ("d", "4"); in map, size should be 4 
	 * 
	 * If remove ("d", "4"), size should be 3
	 */
	@Test
	public void testRemove() {
		MapADT<String, String> abcd = new JavaMap<>();
		abcd.put("a", "1");
		abcd.put("b", "2");
		abcd.put("c", "3");
		abcd.put("d", "4");
		assertEquals(abcd.size(), 4);
		abcd.remove("d");
		assertEquals(abcd.size(), 3);
	}
}