/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */

package com;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class NodeLinkedTest {
    private LinkedNode<Integer> node;

    @Test
    public void testNodeCreationWithNonNullItem() {
        node = new LinkedNode<>(42, null, null);
        assertNotNull(node);
        assertEquals(42, (int) node.getItem());
    }
    
    @Test
    public void testNodeCreationWithNullItem() {
        LinkedNode<Integer> node = new LinkedNode<>(null, null, null);
        assertNotNull(node);
        assertNull(node.getItem());
    }

    @Test
    public void testSetAndGetItem() {
        LinkedNode<Integer> node = new LinkedNode<>(10, null, null);
        assertEquals(10, (int) node.getItem());
        node.setItem(20);
        assertEquals(20, (int) node.getItem());
    }

    @Test
    public void testSetAndGetPreviousNode() {
        LinkedNode<Integer> node1 = new LinkedNode<>(1, null, null);
        LinkedNode<Integer> node2 = new LinkedNode<>(2, null, null);
        
        node1.setNext(node2);
        assertEquals(node2, node1.getNext());
    }

    @Test
    public void testSetAndgetNextNode() {
        LinkedNode<Integer> node1 = new LinkedNode<>(1, null, null);
        LinkedNode<Integer> node2 = new LinkedNode<>(2, null, null);
        
        node2.setPrevious(node1);
        assertEquals(node1, node2.getPrevious());
    }

    @Test
    public void testIsFirstNode() {
        LinkedNode<Integer> node1 = new LinkedNode<>(1, null, null);
        assertTrue(node1.isFirstNode());

        LinkedNode<Integer> node2 = new LinkedNode<>(2, node1, null);
        node2.setPrevious(node1);
        assertFalse(node2.isFirstNode());
    }

    @Test
    public void testIsLastNode() {
        LinkedNode<Integer> node1 = new LinkedNode<>(1, null, null);
        assertTrue(node1.isLastNode());

        LinkedNode<Integer> node2 = new LinkedNode<>(2, null, null);
        node2.setNext(node1);
        assertFalse(node2.isLastNode());
    }

    @Test
    public void testIsNotATerminalNode() {
        LinkedNode<Integer> node1 = new LinkedNode<>(1, null, null);
        assertFalse(node1.isNotATerminalNode());

        LinkedNode<Integer> node3 = new LinkedNode<>(3, node1, null);
        assertFalse(node3.isNotATerminalNode());
        
        LinkedNode<Integer> node4 = new LinkedNode<>(3, null, node1);
        assertFalse(node4.isNotATerminalNode());
        

        LinkedNode<Integer> node2 = new LinkedNode<>(2, node1, node3);
        assertTrue(node2.isNotATerminalNode());
    }
}
