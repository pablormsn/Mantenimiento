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

public class DoubleLinkedListTest {

    @Test
    public void testConstructor() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        assertEquals(0, list.size());
    }

    @Test
    public void testPrependOnEmptyList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.prepend(1);
        assertEquals(1, list.size());
    }

    @Test
    public void testPrepend() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.append(1);
        list.append(2);
        list.prepend(3);
        assertEquals(3, list.size());
    }

    @Test
    public void testAppendOnEmptyList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.append(1);
        assertEquals(1, list.size());
    }

    @Test
    public void testAppend() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.append(1);
        list.append(2);
        assertEquals(2, list.size());
    }

    @Test
    public void testDeleteFirstOnEmptyList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.deleteFirst();
        assertEquals(0, list.size());
    }

    @Test
    public void testDeleteFirstOnSingleElementList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.append(1);
        list.deleteFirst();
        assertEquals(0, list.size());
    }

    @Test
    public void testDeleteFirst() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.append(1);
        list.append(2);
        list.deleteFirst();
        assertEquals(1, list.size());
    }

    @Test
    public void testDeleteLastOnEmptyList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.deleteLast();
        assertEquals(0, list.size());
    }

    @Test
    public void testDeleteLastOnSingleElementList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.append(1);
        list.deleteLast();
        assertEquals(0, list.size());
    }

    @Test
    public void testDeleteLast() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.append(1);
        list.append(2);
        list.deleteLast();
        assertEquals(1, list.size());
    }

    @Test
    public void testFirstOnEmptyList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        assertNull(list.first());
    }

    @Test
    public void testFirst() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.append(1);
        list.append(2);
        assertEquals(Integer.valueOf(1), list.first());
    }

    @Test
    public void testLastOnEmptyList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        assertNull(list.last());
    }

    @Test
    public void testLast() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.append(1);
        list.append(2);
        assertEquals(Integer.valueOf(2), list.last());
    }
}