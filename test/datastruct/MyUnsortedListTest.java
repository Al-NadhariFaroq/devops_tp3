package datastruct;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MyUnsortedListTest {

	@Test
	public void createList() {
		var l = MyUnsortedList.of(0, 1, 2);
		assertNotNull("Created list should be not null.", l);
	}
	
	@Test
	public void createIterableList() {
		List<Integer> in = new ArrayList<Integer>(2);
		in.add(0);
		in.add(1);
		var l = MyUnsortedList.of(in);
		assertNotNull("Created list should be not null.", l);
	}
	
	@Test
	public void isEmpty() {
		var l = MyUnsortedList.of();
		assertTrue("Empty created list should be empty.", l.isEmpty());
	}

	@Test
	public void isNotEmpty() {
		var l = MyUnsortedList.of(0, 1, 2, 3);
		assertFalse("Not empty created list should not be empty.", l.isEmpty());
	}

	@Test
	public void isNoMoreEmpty() {
		var l = MyUnsortedList.of();
		assertTrue("Empty created list should be empty.", l.isEmpty());
		l.append(1);
		assertFalse("List should be not empty after appending.", l.isEmpty());
		int pop = (Integer) l.pop();
		assertTrue("List should be empty after poping.", l.isEmpty());
		assertEquals("Poped item should be the one inserted.", 1, pop);
	}
	
	@Test
	public void emptyListSize() {
		var l = MyUnsortedList.of();
		assertEquals("Size of empty list should be 0.", 0, l.size());
	}

	@Test
	public void notEmptyListSize() {
		var l = MyUnsortedList.of(0, 1, 2, 3, 4);
		assertEquals("Size of not empty list should be the number of elements in it.", 5, l.size());
	}
	
	@Test
	public void prepend() {
		var l = MyUnsortedList.of();
		l.prepend(0);
		l.prepend(1);
		l.prepend(2);
		assertEquals("List size should increase after prepending.", 3, l.size());
		assertEquals("List should contain the prepended elements in order.", MyUnsortedList.of(2, 1, 0), l);
	}

	@Test
	public void append() {
		var l = MyUnsortedList.of();
		l.append(0);
		l.append(1);
		l.append(2);
		assertEquals("List size should increase after appending.", 3, l.size());
		assertEquals("List should contain the appended elements in order.", MyUnsortedList.of(0, 1, 2), l);
	}
	
	@Test
	public void insertInEmptyList() {
		var l = MyUnsortedList.of();
		l.insert(0, 0);
		assertEquals("List should contain the inserted element.", MyUnsortedList.of(0), l);
	}

	@Test
	public void insertInMiddleList() {
		var l = MyUnsortedList.of(0, 2);
		l.insert(1, 1);
		assertEquals("List should contain the inserted element in right index.", MyUnsortedList.of(0, 1, 2), l);
	}

	@Test
	public void insertInStartList() {
		var l = MyUnsortedList.of(1, 2);
		l.insert(0, 0);
		assertEquals("List should contain the inserted element in right index.", MyUnsortedList.of(0, 1, 2), l);
	}

	@Test
	public void insertInEndList() {
		var l = MyUnsortedList.of(0, 1);
		l.insert(2, l.size());
		assertEquals("List should contain the inserted element in right index.", MyUnsortedList.of(0, 1, 2), l);
	}

	@Test
	public void insertIncrease() {
		var l = MyUnsortedList.of();
		int size;
		for (size = 0; size < 10; size++) {
			assertEquals("List size should increase after insert.", size, l.size());
			l.insert(0, size);
		}
		assertEquals("List size should increase after insert.", size, l.size());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void insertOutList() throws IndexOutOfBoundsException {
		var l = MyUnsortedList.of(0, 2);
		l.insert(1, 3);
		assertEquals("List size should not increase after inserting out of bound.", 2, l.size());
	}
	
	@Test
	public void pop() {
		var l = MyUnsortedList.of(0, 1, 2);
		int pop = l.pop();
		assertEquals("List size should decrease after poping.", 2, l.size());
		assertEquals("Poped element should be the first in the list.", 0, pop);
		assertEquals("List after poping should not contain the poped element.", MyUnsortedList.of(1, 2), l);
	}

	@Test(expected=EmptyListException.class)
	public void popEmptyList() throws EmptyListException {
		var l = MyUnsortedList.of();
		l.pop();
		assertEquals("List size should always be 0.", 0, l.size());
	}

	@Test
	public void multiplePop() {
		var l = MyUnsortedList.of(0, 1, 2, 3, 4);
		int size = l.size();
		while (!l.isEmpty()) {
			int pop = l.pop();
			assertEquals("Poped element should be the first in the list.", 5 - size, pop);
			size--;
			assertEquals("List size should decrease after poping.", size, l.size());
		}
	}

	@Test
	public void popLast() {
		var l = MyUnsortedList.of(0, 1, 2);
		int pop = l.popLast();
		assertEquals("List size should decrease after poping last.", 2, l.size());
		assertEquals("Poped element should be the last in the list.", 2, pop);
		assertEquals("List after poping should not contain the poped element.", MyUnsortedList.of(0, 1), l);
	}

	@Test(expected=EmptyListException.class)
	public void popLastEmptyList() throws EmptyListException {
		var l = MyUnsortedList.of();
		l.popLast();
		assertEquals("List size should always be 0.", 0, l.size());
	}

	@Test
	public void multiplePopLast() {
		var l = MyUnsortedList.of(0, 1, 2, 3, 4);
		int size = l.size();
		while (!l.isEmpty()) {
			int pop = l.popLast();
			size--;
			assertEquals("Poped element should be the last in the list.", size, pop);
			assertEquals("List size should decrease after poping last.", size, l.size());
		}
	}
	
	@Test
	public void remove() {
		var l = MyUnsortedList.of(0, 1, 2);
		int rm = l.remove(1);
		assertEquals("List size should decrease after removing.", 2, l.size());
		assertEquals("Removed element should be the one at the index.", 1, rm);
		assertEquals("List after removing should not contain the removed element.", MyUnsortedList.of(0, 2), l);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void removeEmptyList() throws IndexOutOfBoundsException {
		var l = MyUnsortedList.of();
		l.remove(5);
		assertEquals("List size should always be 0.", 0, l.size());
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void removeListNegativeIndex() throws IndexOutOfBoundsException {
		var l = MyUnsortedList.of(0, 1, 2);
		l.remove(-5);
		assertEquals("List size should not change.", 3, l.size());
	}

	@Test
	public void multipleRemove() {
		var l = MyUnsortedList.of(0, 1, 2, 3, 4);
		int size = l.size();
		while (!l.isEmpty()) {
			int rm = l.remove(size - 1);
			size--;
			assertEquals("Removed element should be the first in the list.", size, rm);
			assertEquals("List size should decrease after removing.", size, l.size());
		}
	}
	
	@Test
	public void equalsTrue() {
		var l1 = MyUnsortedList.of(0, 1, 2, 3, 4);
		var l2 = MyUnsortedList.of(0, 1, 2, 3, 4);
		assertTrue("Two lists with same elements in the same order should be equals.", l1.equals(l2));
	}

	@Test
	public void equalsFalseSize() {
		var l1 = MyUnsortedList.of(0, 1, 2, 3, 4);
		var l2 = MyUnsortedList.of(0, 1, 2, 3, 4, 1);
		assertFalse("Two lists with different elements should not be equals.", l1.equals(l2));
	}

	@Test
	public void equalsFalseWithoutNull() {
		var l1 = MyUnsortedList.of(0, 1, 2, 3, 4);
		var l2 = MyUnsortedList.of(0, 5, 2, 3, 4);
		assertFalse("Two lists with different elements should not be equals.", l1.equals(l2));
	}

	@Test
	public void equalsFalseWithNull() {
		var l1 = MyUnsortedList.of(0, 1, 2, 3, 4);
		var l2 = MyUnsortedList.of(0, null, 2, 3, 4);
		assertFalse("Two lists with different elements should not be equals.", l1.equals(l2));
	}

	@Test
	public void equalsOrder() {
		var l1 = MyUnsortedList.of(0, 1, 2, 3, 4);
		var l2 = MyUnsortedList.of(0, 1, 3, 2, 4);
		assertFalse("Two lists with same elements but not in the same order should not be equals.", l1.equals(l2));
	}

	@Test
	public void equalsEmpty() {
		var l1 = MyUnsortedList.of();
		var l2 = MyUnsortedList.of();
		assertTrue("Two empty lists should be equals.", l1.equals(l2));
	}
	
	@Test
	public void equalsSameObject() {
		var l1 = MyUnsortedList.of(0, 1, 2, 3, 4);
		assertTrue("Two lists with same reference should be equals.", l1.equals(l1));
	}

	@Test
	public void equalsNotSameObject() {
		var l1 = MyUnsortedList.of(0, 1, 2, 3, 4);
		List<Integer> l2 = Arrays.asList(0, 1, 2, 3, 4);
		assertFalse("Two lists with same elements but not the same class should not be equals.", l1.equals(l2));
	}

	@Test
	public void equalsNull() {
		var l1 = MyUnsortedList.of(0, 1, 2, 3, 4);
		MyUnsortedList<Integer> l2 = null;
		assertFalse("Two lists with one null should not be equals.", l1.equals(l2));
	}
	
	@Test
	public void string() {
		var l = MyUnsortedList.of(0, 1, 2);
		String res = "MyUnsortedList { size = 3, [0, 1, 2] }";
		assertEquals("List string should correspond to this elements.", res, l.toString());
	}
}
