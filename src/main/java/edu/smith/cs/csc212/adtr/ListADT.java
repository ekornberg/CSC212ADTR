package edu.smith.cs.csc212.adtr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import edu.smith.cs.csc212.adtr.errors.BadIndexError;
import edu.smith.cs.csc212.adtr.errors.EmptyListError;

/**
 * This is an abstract view of a List data structure.
 * 
 * @author jfoley
 *
 * @param <ItemType> - the type of the item stored in this list.
 */
public abstract class ListADT<ItemType> implements Iterable<ItemType> {

	/**
	 * Is this list of size zero? Might be easier than counting all the elements.
	 * @return true if this list is empty.
	 */
	public abstract boolean isEmpty();

	/**
	 * The size of this list.
	 * 
	 * @return the size of the list or number of items.
	 */
	public abstract int size();

	/**
	 * Set the item stored at the given index to value.
	 * 
	 * @param index - the index; from 0 to size() inclusive.
	 * @param value - the value to put in the list.
	 */
	public abstract void setIndex(int index, ItemType value);

	/**
	 * Get the value at the given index.
	 * 
	 * @param index - the item index to retrieve.
	 * @return the value stored at that position.
	 */
	public abstract ItemType getIndex(int index);

	/**
	 * Get the first item in the list.
	 * 
	 * @return this.getIndex(0);
	 */
	public abstract ItemType getFront();

	/**
	 * Get the last item in the list.
	 * 
	 * @return this.getIndex(this.size()-1);
	 */
	public abstract ItemType getBack();

	/**
	 * Add an item with value before the item at the given index.
	 * 
	 * @param index - a number from 0 to size (inclusive).
	 * @param value - the value to insert into the list.
	 */
	public abstract void addIndex(int index, ItemType value);

	/**
	 * Add value to the front of the list.
	 * 
	 * @param value - the item to add.
	 */
	public abstract void addFront(ItemType value);

	/**
	 * Add value to the back of the list.
	 * 
	 * @param value - the item to add.
	 */
	public abstract void addBack(ItemType value);

	/**
	 * Remove the item from the list at the given index.
	 * 
	 * @param index - a number from 0 to size (exclusive).
	 * @return the value removed.
	 */
	public abstract ItemType removeIndex(int index);

	/**
	 * Remove the item from the back of the list.
	 * 
	 * @return the value removed.
	 */
	public abstract ItemType removeBack();

	/**
	 * Remove the item from the front of the list.
	 * 
	 * @return the value removed.
	 */
	public abstract ItemType removeFront();

	
	/**
	 * If this list is empty, throw an error; useful for implementing classes.
	 */
	protected void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}
	
	/**
	 * Check the index for a get/set/remove method.
	 * Not for LinkedList classes!
	 */
	protected void checkExclusiveIndex(int index) {
		if (index < 0) {
			throw new BadIndexError(index);
		}
		if (index >= size()) {
			throw new BadIndexError(index);
		}
	}
	
	/**
	 * Check the index for an add method.
	 * Not for LinkedList classes!
	 */
	protected void checkInclusiveIndex(int index) {
		if (index < 0) {
			throw new BadIndexError(index);
		}
		if (index > size()) {
			throw new BadIndexError(index);
		}
	}
	
	/**
	 * Java requires this method for it's "for (ItemType x : list) { }" loop.
	 */
	public Iterator<ItemType> iterator() {
		return new ListADTIterator<>(this);
	}
	
	/**
	 * Convert this to a Java data structure; probably useful for unit-test errors.
	 * @return - a Java List object.
	 */
	public List<ItemType> toJava() {
		List<ItemType> output = new ArrayList<>();
		for (ItemType x : this) {
			output.add(x);
		}
		return output;
	}
	
	
	/**
	 * Teach java how to compare two ListADT objects.
	 * @return true if they have the same contents.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof ListADT) {
			// We don't really know what kind of list is passed in, so treat it as a list of anything:
			@SuppressWarnings("unchecked")
			ListADT<Object> rhs = (ListADT<Object>) other;
			
			// if they're not the same size, they're different!
			if (rhs.size() != this.size()) {
				return false;
			}
			
			// if they're not the same elements, they're different!
			for (int i=0; i<this.size(); i++) {
				if (!Objects.equals(this.getIndex(i), rhs.getIndex(i))) {
					return false;
				}
			}
			// if we got here, they're the same lists!
			return true;
		}
		
		return false;
	}
	
	/**
	 * Teach java how to print this kind of object.
	 * @return a string representation of the data.
	 */
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("ListADT [");
		for (int i=0; i<this.size(); i++) {
			if (i > 0) {
				output.append(", ");
			}
			output.append(this.getIndex(i));
		}
		return output.append("]").toString();
	}
	
	/**
	 * Don't let people mis-use this class.
	 */
	@Override
	public int hashCode() {
		throw new IllegalArgumentException("Don't use a ListADT as a key in a hashmap!");
	}

}
