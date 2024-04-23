/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */

package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

public class DoubleLinkedListTest {

    private DoubleLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList<>();
    }

    @Nested
    @DisplayName("Constructor tests")
    class ConstructorTests {

        @Test
        @DisplayName("New list should have size 0")
        void testConstructor() {
            assertEquals(0, list.size());
        }
    }

    @Nested
    @DisplayName("Prepend tests")
    class PrependTests {

        @Test
        @DisplayName("Prepend on empty list should increase size")
        void testPrependOnEmptyList() {
            list.prepend(1);
            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("Prepend should add element to the beginning")
        void testPrepend() {
            list.append(1);
            list.append(2);
            list.prepend(3);
            assertEquals(3, list.size());
        }
    }

    @Nested
    @DisplayName("Append tests")
    class AppendTests {

        @Test
        @DisplayName("Append on empty list should increase size")
        void testAppendOnEmptyList() {
            list.append(1);
            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("Append should add element to the end")
        void testAppend() {
            list.append(1);
            list.append(2);
            assertEquals(2, list.size());
        }
    }

    @Nested
    @DisplayName("DeleteFirst tests")
    class DeleteFirstTests {

        @Test
        @DisplayName("DeleteFirst on empty list should not change size")
        void testDeleteFirstOnEmptyList() {
            list.deleteFirst();
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("DeleteFirst on single element list should reduce size to 0")
        void testDeleteFirstOnSingleElementList() {
            list.append(1);
            list.deleteFirst();
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("DeleteFirst should remove first element")
        void testDeleteFirst() {
            list.append(1);
            list.append(2);
            list.deleteFirst();
            assertEquals(1, list.size());
        }
    }

    @Nested
    @DisplayName("DeleteLast tests")
    class DeleteLastTests {

        @Test
        @DisplayName("DeleteLast on empty list should not change size")
        void testDeleteLastOnEmptyList() {
            list.deleteLast();
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("DeleteLast on single element list should reduce size to 0")
        void testDeleteLastOnSingleElementList() {
            list.append(1);
            list.deleteLast();
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("DeleteLast should remove last element")
        void testDeleteLast() {
            list.append(1);
            list.append(2);
            list.deleteLast();
            assertEquals(1, list.size());
        }
    }

    @Nested
    @DisplayName("First tests")
    class FirstTests {

        @Test
        @DisplayName("First on empty list should return null")
        void testFirstOnEmptyList() {
            assertNull(list.first());
        }

        @Test
        @DisplayName("First should return the first element")
        void testFirst() {
            list.append(1);
            list.append(2);
            assertEquals(Integer.valueOf(1), list.first());
        }
    }

    @Nested
    @DisplayName("Last tests")
    class LastTests {

        @Test
        @DisplayName("Last on empty list should return null")
        void testLastOnEmptyList() {
            assertNull(list.last());
        }

        @Test
        @DisplayName("Last should return the last element")
        void testLast() {
            list.append(1);
            list.append(2);
            assertEquals(Integer.valueOf(2), list.last());
        }
    }
    @Nested
    @DisplayName("size() method tests")
    class SizeTests {

        @Test
        @DisplayName("when list is empty")
        void sizeWhenListIsEmpty() {
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("when list has one element")
        void sizeWhenListHasOneElement() {
            list.append(1);
            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("when list has multiple elements")
        void sizeWhenListHasMultipleElements() {
            list.append(1);
            list.append(2);
            list.append(3);
            assertEquals(3, list.size());
        }
    }

    @Nested
    @DisplayName("get() method tests")
    class GetTests {

        @Test
        @DisplayName("when getting from an empty list")
        void getWhenListIsEmpty() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        }

        @Test
        @DisplayName("when getting an element that exists")
        void getExistingElement() {
            list.append(1);
            assertEquals(1, list.get(0));
        }

        @Test
        @DisplayName("when getting an element that does not exist")
        void getNonExistingElement() {
            list.append(1);
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        }

        @Test
        @DisplayName("when getting an element that does not exist")
        void getNonExistingElementNegative() {
            list.append(1);
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        }
    }

    @Nested
    @DisplayName("contains() method tests")
    class ContainsTests {

        @Test
        @DisplayName("when list is empty")
        void containsWhenListIsEmpty() {
            assertFalse(list.contains(1));
        }

        @Test
        @DisplayName("when list contains the element")
        void containsWhenElementExists() {
            list.append(1);
            assertTrue(list.contains(1));
        }

        @Test
        @DisplayName("when list does not contain the element")
        void containsWhenElementDoesNotExist() {
            list.append(1);
            assertFalse(list.contains(2));
        }
    }

    @Nested
    @DisplayName("remove() method tests")
    class RemoveTests {

        @BeforeEach
        void populateList() {
            list.append(1);
            list.append(2);
            list.append(3);
            list.append(4);
            list.append(5);
        }

        @Test
        @DisplayName("Removing existing element")
        void removeExistingElement() {
            list.remove(3);
            assertFalse(list.contains(3));
            assertEquals(4, list.size());
        }

        @Test
        @DisplayName("Removing non-existing element does nothing")
        void removeNonExistingElement() {
            list.remove(10);
            assertEquals(5, list.size());
        }

        @Test
        @DisplayName("Removing from list with one element")
        void removeElementFromSingleElementList() {
            DoubleLinkedList<Integer> singleElementList = new DoubleLinkedList<>();
            singleElementList.append(10);
            singleElementList.remove(10);
            assertEquals(0, singleElementList.size());
        }

        @Test
        @DisplayName("Removing last element")
        void removeLastElement() {
            list.remove(5);
            assertFalse(list.contains(5));
            assertEquals(4, list.size());
        }

        @Test
        @DisplayName("Removing first element")
        void removeFirstElement() {
            list.remove(1);
            assertFalse(list.contains(1));
            assertEquals(4, list.size());
        }
    }

    @Nested
    @DisplayName("sort(Comparator<? super T> comparator) method tests")
    class SortTests {

        @Test
        @DisplayName("when sorting an empty list")
        void sortWhenListIsEmpty() {
            list.sort(Comparator.naturalOrder());
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("when sorting a list with one element")
        void sortWhenListHasOneElement() {
            list.append(1);
            list.sort(Comparator.naturalOrder());
            assertEquals(1, list.get(0));
        }

        @Test
        @DisplayName("when sorting a list with multiple elements")
        void sortWhenListHasMultipleElements() {
            list.append(3);
            list.append(1);
            list.append(2);
            list.sort(Comparator.naturalOrder());
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
        }
    }
}