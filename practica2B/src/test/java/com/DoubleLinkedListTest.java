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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

public class DoubleLinkedListTest {

    private DoubleLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList<>();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, list.size());
    }

    @Test
    public void testPrependOnEmptyList() {
        list.prepend(1);
        assertEquals(1, list.size());
    }

    @Test
    public void testPrepend() {
        list.append(1);
        list.append(2);
        list.prepend(3);
        assertEquals(3, list.size());
    }

    @Test
    public void testAppendOnEmptyList() {
        list.append(1);
        assertEquals(1, list.size());
    }

    @Test
    public void testAppend() {
        list.append(1);
        list.append(2);
        assertEquals(2, list.size());
    }

    @Test
    public void testDeleteFirstOnEmptyList() {
        list.deleteFirst();
        assertEquals(0, list.size());
    }

    @Test
    public void testDeleteFirstOnSingleElementList() {
        list.append(1);
        list.deleteFirst();
        assertEquals(0, list.size());
    }

    @Test
    public void testDeleteFirst() {
        list.append(1);
        list.append(2);
        list.deleteFirst();
        assertEquals(1, list.size());
    }

    @Test
    public void testDeleteLastOnEmptyList() {
        list.deleteLast();
        assertEquals(0, list.size());
    }

    @Test
    public void testDeleteLastOnSingleElementList() {
        list.append(1);
        list.deleteLast();
        assertEquals(0, list.size());
    }

    @Test
    public void testDeleteLast() {
        list.append(1);
        list.append(2);
        list.deleteLast();
        assertEquals(1, list.size());
    }

    @Test
    public void testFirstOnEmptyList() {
        assertNull(list.first());
    }

    @Test
    public void testFirst() {
        list.append(1);
        list.append(2);
        assertEquals(Integer.valueOf(1), list.first());
    }

    @Test
    public void testLastOnEmptyList() {
        assertNull(list.last());
    }

    @Test
    public void testLast() {
        list.append(1);
        list.append(2);
        assertEquals(Integer.valueOf(2), list.last());
    }

    @Nested
    @DisplayName("size() method tests")
    class SizeTests {

        @Test
        @DisplayName("when list is empty")
        void sizeWhenListIsEmpty() {
            assertEquals(0, list.size(), "Size should be 0 when list is empty");
        }

        @Test
        @DisplayName("when list has one element")
        void sizeWhenListHasOneElement() {
            list.append(1);
            assertEquals(1, list.size(), "Size should be 1 when list has one element");
        }

        @Test
        @DisplayName("when list has multiple elements")
        void sizeWhenListHasMultipleElements() {
            list.append(1);
            list.append(2);
            list.append(3);
            assertEquals(3, list.size(), "Size should be 3 when list has three elements");
        }
    }

    @Nested
    @DisplayName("get() method tests")
    class GetTests {

        @Test
        @DisplayName("when getting from an empty list")
        void getWhenListIsEmpty() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(0), "Get should throw IndexOutOfBoundsException when list is empty");
        }

        @Test
        @DisplayName("when getting an element that exists")
        void getExistingElement() {
            list.append(1);
            assertEquals(1, list.get(0), "Get should return the correct element");
        }

        @Test
        @DisplayName("when getting an element that does not exist")
        void getNonExistingElement() {
            list.append(1);
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(1), "Get should throw IndexOutOfBoundsException when element does not exist");
        }
    }

    @Nested
    @DisplayName("contains() method tests")
    class ContainsTests {

        @Test
        @DisplayName("when list is empty")
        void containsWhenListIsEmpty() {
            assertFalse(list.contains(1), "Contains should return false when list is empty");
        }

        @Test
        @DisplayName("when list contains the element")
        void containsWhenElementExists() {
            list.append(1);
            assertTrue(list.contains(1), "Contains should return true when element exists in the list");
        }

        @Test
        @DisplayName("when list does not contain the element")
        void containsWhenElementDoesNotExist() {
            list.append(1);
            assertFalse(list.contains(2), "Contains should return false when element does not exist in the list");
        }
    }

    @Nested
    @DisplayName("remove() method tests")
    class RemoveTests {

        @Test
        @DisplayName("when removing an element that exists")
        void removeExistingElement() {
            list.append(1);
            assertTrue(list.contains(1), "List contains the element");
            list.remove(1);
            assertFalse(list.contains(1), "List should not contain the removed element");
        }

        @Test
        @DisplayName("when removing an element that does not exist")
        void removeNonExistingElement() {
            list.append(1);
            list.remove(2);
            assertTrue(list.contains(1), "List should still contain the non-removed element");
        }
    }
}