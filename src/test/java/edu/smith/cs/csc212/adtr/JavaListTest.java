package edu.smith.cs.csc212.adtr;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import edu.smith.cs.csc212.adtr.errors.BadIndexError;
import edu.smith.cs.csc212.adtr.errors.EmptyListError;
import edu.smith.cs.csc212.adtr.real.JavaList;


public class JavaListTest {
	
	/**
	 * Make a new empty list.
	 * @return an empty list to be tested.
	 */
	private <T> ListADT<T> makeEmptyList() {
		return new JavaList<>();
	}
	
	/**
	 * Helper method to make a full list.
	 * @return [a, b, c, d] - a small, predictable list for many tests.
	 */
	private ListADT<String> makeFullList() {
		ListADT<String> data = makeEmptyList();
		data.addBack("a");
		data.addBack("b");
		data.addBack("c");
		data.addBack("d");
		return data;
	}
		
	@Test
	public void testEmpty() {
		ListADT<String> data = makeEmptyList();
		Assert.assertEquals(0, data.size());
		Assert.assertEquals(true, data.isEmpty());
	}
	
	@Test(expected=EmptyListError.class)
	public void testRemoveFrontCrash() {
		ListADT<String> data = makeEmptyList();
		data.removeFront(); 
		/* Can't do anything here b/c can't 
		 * remove from empty list.
		 * Java tries to delete null 
		 * but we want it to crash b/c 
		 * this would be programmer's error.
		 * --> check size before removing things
		 */
	}
	
	@Test(expected=EmptyListError.class)
	public void testRemoveBackCrash() {
		ListADT<String> data = makeEmptyList();
		data.removeBack();
	}
	
	@Test(expected=EmptyListError.class)
	public void testRemoveIndexCrash() {
		ListADT<String> data = makeEmptyList();
		data.removeIndex(3);
	}

	@Test
	public void testAddToFront() {
		ListADT<String> data = makeEmptyList();
		Assert.assertEquals(true, data.isEmpty());
		data.addFront("1");
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("1", data.getIndex(0));
		Assert.assertEquals(false, data.isEmpty());
		data.addFront("0");
		Assert.assertEquals(2, data.size());
		Assert.assertEquals("0", data.getIndex(0));
		Assert.assertEquals("1", data.getIndex(1));
		Assert.assertEquals(false, data.isEmpty());
		data.addFront("-1");
		Assert.assertEquals(3, data.size());
		Assert.assertEquals("-1", data.getIndex(0));
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(2));
		Assert.assertEquals(false, data.isEmpty());
		data.addFront("-2");
		Assert.assertEquals("-1", data.getIndex(1));
		Assert.assertEquals("-2", data.getIndex(0));
		Assert.assertEquals("0", data.getIndex(2));
		Assert.assertEquals("1", data.getIndex(3));
		Assert.assertEquals(false, data.isEmpty());
	}
	
	// ===========
	// addBack -- add value to back of the list

	/* If add value "e" to back of full list,
	 * data.getIndex(4) yields "e" 
	 * and size of data is 5
	 */
	@Test
	public void testaddBack() {
		ListADT<String> data = makeFullList();
		data.addBack("e");
		assertEquals(data.getIndex(4), "e");
		assertEquals(data.size(), 5);
	}
	
	/* If add values "e" and "f" to back of full list,
	 * data.getIndex(4) yields "e" and
	 * data.getIndex(5) yields "f" and 
	 * size of data is 6
	 */
	@Test
	public void testaddBack2() {
		ListADT<String> data = makeFullList();
		data.addBack("e");
		data.addBack("f");
		assertEquals(data.getIndex(4), "e");
		assertEquals(data.getIndex(5), "f");
		assertEquals(data.size(), 6);
	}
	
	// Can add value to back of empty list
	@Test
	public void testaddBackErrorIndex() {
		ListADT<String> data = makeEmptyList();
		data.addBack("empty");
		assertEquals(data.getBack(), "empty");
	}
	
		
	// ===========
	// removeBack -- remove item from back of list
	
	/* If remove last item from full list, 
	 * full list size should be 3 and last item
	 * should be "c"
	 * 
	 * Check to see if list size > 0 before 
	 * removing back
	 */
	@Test
	public void testremoveBack() {
		ListADT<String> data = makeFullList();
		if(data.size() > 0) {
			data.removeBack();
		}
		assertEquals(data.size(), 3);
		assertEquals(data.getBack(), "c");
	}	
	
	/* data is an empty list and you cannot
	 * remove an item from the back of the list
	 * if there is nothing in the list
	 * 
	 * Trying to get last item of list from
	 * an empty list would not work
	 */
	@Test(expected=EmptyListError.class)
	public void testremoveBackError() {
		ListADT<String> data = makeEmptyList();
		data.removeBack();
		assertEquals(data.getBack(), null);
	}	
		
	// ============
	// removeFront -- remove item from front of list
	
	// If remove item from front of full list,
	// data size should be 3
	// (list size should be greater than 0)
	@Test
	public void testremoveFront() {
		ListADT<String> data = makeFullList();
		if (data.size() > 0) {
			data.removeFront();
		} 
		assertEquals(data.size(), 3);
	}	
	
	// Cannot remove item from front of empty list
	// because there is nothing to remove
	@Test(expected=EmptyListError.class)
	public void testremoveFrontErrorEmpty() {
		ListADT<String> data = makeEmptyList();
		data.removeFront();
	}	
		
	// ============
	// removeIndex -- Remove item from 
	// list at given index
	
	// If remove item at index 2 from full list,
	// items shift and data.getIndex(2) yields "d"
	@Test
	public void testremoveIndex2() {
		ListADT<String> data = makeFullList();
		if (data.size() > 0) {
			data.removeIndex(2);
		}
		assertEquals(data.getIndex(2), "d");
	}
	
	// If remove item at index 1 from full list,
	// items shift and data.getIndex(1) yields "c"
	public void testremoveIndex1() {
		ListADT<String> data = makeFullList();
		if (data.size() > 0) {
			data.removeIndex(1);
		}
		assertEquals(data.getIndex(1), "c");
	}
	
	// No item has an index of 5 in full list,
	// So test should expect BadIndexError
	@Test(expected=BadIndexError.class)
	public void testRemoveIndexErrorIndex() {
		ListADT<String> data = makeFullList();
		if (data.size() > 0) {
			data.removeIndex(5);
		}
	}
	
	// data is an empty list and you cannot 
	// remove an item from empty list
	// no matter the index, so it would 
	// yield an EmptyListError
	@Test(expected=EmptyListError.class)
	public void testRemoveIndexErrorEmpty() {
		ListADT<String> data = makeEmptyList();
		data.removeIndex(1);
	}
	
	// =============
	// addIndex -- Add item with value before 
	// item at given index
	
	/* If add item with value "second" at index 2
	 * and item with value "fourth" at index 4
	 * to list "data", values at indexes 2 and 4 
	 * should be "second" and "fourth", respectively
	 */
	@Test
	public void testaddIndex() {
		ListADT<String> data = makeFullList();
		data.addIndex(2, "second");
		data.addIndex(4, "fourth");
		assertEquals(data.getIndex(2), "second");
		assertEquals(data.getIndex(4), "fourth");
	}
	
	/* No item exists at index 6,
	 * so when you try to add item with value
	 * before item at this index, yields a 
	 * BadIndexError
	 */
	@Test(expected=BadIndexError.class)
	public void testaddIndexErrorIndex() {
		ListADT<String> data = makeFullList();
		data.addIndex(6, "error");
		assertEquals(data.getIndex(6), "error");
	}
	
	/* data is an empty list so when you try 
	 * to add item with value before item at
	 * index 1, yields BadIndexError because
	 * list doesn't have an item at this index
	 */
	@Test(expected=BadIndexError.class)
	public void testaddIndexErrorEmpty() {
		ListADT<String> data = makeEmptyList();
		data.addIndex(1, "error");
		assertEquals(data.getIndex(1), "error");
	}
	
	@Test
	public void testGetFront() {
		ListADT<String> data = makeFullList();
		assertEquals("a", data.getFront());
	}
	
	@Test
	public void testGetBack() {
		ListADT<String> data = makeFullList();
		assertEquals("d", data.getBack());
	}
	
	@Test(expected=EmptyListError.class)
	public void testGetFrontCrash() {
		ListADT<String> data = makeEmptyList();
		data.getFront();
	}
	
	@Test(expected=EmptyListError.class)
	public void testGetBackCrash() {
		ListADT<String> data = makeEmptyList();
		data.getBack();
	}
	
	@Test(expected=BadIndexError.class)
	public void testGetIndexLow() {
		ListADT<String> data = makeFullList();
		data.getIndex(-2);
	}
	
	@Test(expected=BadIndexError.class)
	public void testGetIndexHigh() {
		ListADT<String> data = makeFullList();
		data.getIndex(data.size());
	}
	
	@Test(expected=BadIndexError.class)
	public void testGetIndexHighEasy() {
		ListADT<String> data = makeFullList();
		data.getIndex(data.size()*2);
	}
	
	@Test(expected=BadIndexError.class)
	public void testAddIndexHighEasy() {
		ListADT<String> data = makeFullList();
		data.addIndex(data.size()*2, "the");
	}
	
	@Test(expected=BadIndexError.class)
	public void testAddIndexHigh() {
		ListADT<String> data = makeFullList();
		data.addIndex(data.size()+1, "the");
	}
	
	@Test(expected=BadIndexError.class)
	public void testAddIndexLow() {
		ListADT<String> data = makeFullList();
		data.addIndex(-1, "the");
	}
	
	// ===========
	// setIndex -- set the item stored at the  
	// given index to value
	
	// If set index at 0 to "hello" in full list,
	// first item in list should be "hello"
	@Test
	public void testSetIndexFront() {
		ListADT<String> data = makeFullList();
		data.setIndex(0, "hello");
		assertEquals(data.getFront(), "hello");
	}	
	
	// If set index at 2 to "middle" in full list,
	// item in list at index 2 should be "middle"
	@Test
	public void testSetIndex() {
		ListADT<String> data = makeFullList();
		data.setIndex(2, "middle");
		assertEquals(data.getIndex(2), "middle");
	}
	
	// If set index at back/3 to "bye" in full list,
	// last item in list should be "bye"
	@Test
	public void testSetIndexBack() {
		ListADT<String> data = makeFullList();
		data.setIndex(3, "bye");
		assertEquals(data.getBack(), "bye");
	}
	
	/* There is no item at index 5 in full list,
	 * so setting the item stored at the
	 * given index to value wouldn't work, 
	 * and would thus yield a BadIndexError
	 */
	@Test(expected=BadIndexError.class)
	public void testSetIndexErrorIndex() {
		ListADT<String> data = makeFullList();
		data.setIndex(5, "error");
		assertEquals(data.getIndex(5), "error");
	}
	
	/* data is an empty list, so when you try
	 * to setIndex to something that isn't
	 * there, it yields an empty list error
	 */	
	@Test(expected=EmptyListError.class)
	public void testSetIndexErrorEmpty() {
		ListADT<String> data = makeEmptyList();
		data.setIndex(1, "error");
		assertEquals(data.getIndex(1), "error");
	}
		
	@Test
	public void testToJava() {
		assertEquals(makeFullList().toJava(), Arrays.asList("a", "b", "c", "d"));
	}
	
	@Test
	public void testEquals() {
		assertEquals(makeFullList(), new JavaList<>(Arrays.asList("a", "b", "c", "d")));
	}
	
	@Test
	public void testEquals2() {
		assertEquals(makeFullList(), makeFullList());
	}
}
