package edu.smith.cs.csc212.adtr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.smith.cs.csc212.adtr.real.JavaSet;

public class JavaSetTest {
	// =========	
	// Size -- how many items in Set?
	// if create new set of string and put nothing in it,
	// it should be empty	
	@Test
	public void testEmpty() {
		SetADT<String> empty = new JavaSet<>();
		assertEquals(empty.size(), 0);
	}
	
	// =========
	// Insert -- add an element to this set
	// If add "A", set should be size of 1
	@Test
	public void testInsertOnce() {
		// turns out, it's impossible 
		// to test size fully without
		// calling insert...
		SetADT<String> one = new JavaSet<>();
		one.insert("A");
		assertEquals(one.size(), 1);
	}
	
	// If add "A" once and "B" multiple times,
	// Set size should be 2
	// and should contain "A" and "B"
	@Test
	public void testRepeatedInsert() {
		SetADT<String> ab = new JavaSet<>();
		ab.insert("A");
		ab.insert("B");
		ab.insert("B");
		ab.insert("B");
		assertEquals(ab.size(), 2);
		assertTrue(ab.contains("A"));
		assertTrue(ab.contains("B"));
	}
	
	// If add "A" once and "B" many times,
	// Set size should be 2
	@Test
	public void testRepeatedInsert2() {
		SetADT<String> abb = new JavaSet<>();
		abb.insert("A");
		for(int i=0; i<1000; i++) {
			abb.insert("B");
		}
		assertEquals(abb.size(), 2);
	}
	
	// =========
	// Contains -- has a certain value been seen in this Set?
	// If add "A" once, "B" twice, and "C" many times,
	// Set should contain "A", "B", and "C"
	// and its size should be 3
	@Test
	public void testContains() {
		SetADT<String> abc = new JavaSet<>();
		abc.insert("A");
		abc.insert("B");
		abc.insert("B");
		for(int i=0; i<25; i++) {
			abc.insert("C");
		} 
		assertTrue(abc.contains("A"));
		assertTrue(abc.contains("B"));
		assertTrue(abc.contains("C"));
		assertEquals(abc.size(), 3);
	}
	
	// =========
	// Remove -- remove a value from this Set
	// If add "A" and "C" once and "B" twice,
	// then remove "A" and "D,"
	// Set size should be 2	
	@Test
	public void testRemove() {
		SetADT<String> abbc = new JavaSet<>();
		abbc.insert("A");
		abbc.insert("B");
		abbc.insert("B");
		abbc.insert("C");
		abbc.remove("A");
		abbc.remove("D");
		assertEquals(abbc.size(), 2);		
	}	
}
