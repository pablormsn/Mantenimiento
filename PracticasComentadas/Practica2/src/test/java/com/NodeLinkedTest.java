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
     private LinkedNode<Integer> node; //Creamos un objeto de tipo LinkedNode
 
     /*
      * Test para comprobar que se crea un nodo con un item no nulo
      * Para ello, usamos el método assertNotNull para comprobar que el nodo no es nulo
      */
     @Test
     public void testNodeCreationWithNonNullItem() {
         node = new LinkedNode<>(42, null, null); //Creamos un nodo con un item de valor 42
         assertNotNull(node); //Comprobamos que el nodo no es nulo
         assertEquals(42, (int) node.getItem()); //Comprobamos que el item del nodo es 42
     }
     
     /*
      * Test para comprobar que se crea un nodo con un item nulo
      * Para ello, usamos el método assertNotNull para comprobar que el nodo no es nulo
      */
     @Test
     public void testNodeCreationWithNullItem() {
         LinkedNode<Integer> node = new LinkedNode<>(null, null, null); //Creamos un nodo con un item nulo
         assertNotNull(node); //Comprobamos que el nodo no es nulo
         assertNull(node.getItem()); //Comprobamos que el item del nodo es nulo
     }
 
     /*
      * Test para comprobar los métodos set y get del item de un nodo
      * Para ello, creamos un nodo con un item de valor 10 y comprobamos que el item del nodo es 10
      * Después, cambiamos el item del nodo a 20 y comprobamos que el item del nodo es 20
      * Todo esto con los métodos set y get del item
      */
     @Test
     public void testSetAndGetItem() {
         LinkedNode<Integer> node = new LinkedNode<>(10, null, null); //Creamos un nodo con un item de valor 10
         assertEquals(10, (int) node.getItem()); //Comprobamos que el item del nodo es 10
         node.setItem(20); //Cambiamos el item del nodo a 20
         assertEquals(20, (int) node.getItem()); //Comprobamos que el item del nodo es 20
     }
 
     /*
      * Test para comprobar los métodos set y get del nodo siguiente
      * Para ello, creamos dos nodos, node1 y node2
      * Asignamos node2 como nodo siguiente de node1 y comprobamos que el nodo siguiente de node1 es node2
      */
     @Test
     public void testSetAndGetPreviousNode() {
         LinkedNode<Integer> node1 = new LinkedNode<>(1, null, null); //Creamos un nodo con un item de valor 1
         LinkedNode<Integer> node2 = new LinkedNode<>(2, null, null); //Creamos un nodo con un item de valor 2
         
         node1.setNext(node2); //Asignamos node2 como nodo siguiente de node1
         assertEquals(node2, node1.getNext()); //Comprobamos que el nodo siguiente de node1 es node2
     }
 
     /*
      * Test para comprobar los métodos set y get del nodo anterior
      * Para ello, creamos dos nodos, node1 y node2
      * Asignamos node1 como nodo anterior de node2 y comprobamos que el nodo anterior de node2 es node1
      */
     @Test
     public void testSetAndgetNextNode() {
         LinkedNode<Integer> node1 = new LinkedNode<>(1, null, null); //Creamos un nodo con un item de valor 1
         LinkedNode<Integer> node2 = new LinkedNode<>(2, null, null); //Creamos un nodo con un item de valor 2
         
         node2.setPrevious(node1); //Asignamos node1 como nodo anterior de node2
         assertEquals(node1, node2.getPrevious()); //Comprobamos que el nodo anterior de node2 es node1
     }
 
     /*
      * Test para comprobar si un nodo es el primer nodo de la lista
      * Para ello, creamos un nodo y comprobamos que es el primer nodo de la lista
      * Después, creamos otro nodo y lo asignamos como siguiente del primer nodo
      * Comprobamos que el primer nodo ya no es el primer nodo de la lista
      */
     @Test
     public void testIsFirstNode() {
         LinkedNode<Integer> node1 = new LinkedNode<>(1, null, null); //Creamos un nodo con un item de valor 1
         assertTrue(node1.isFirstNode()); //Comprobamos que el nodo es el primer nodo de la lista
 
         LinkedNode<Integer> node2 = new LinkedNode<>(2, node1, null); //Creamos un nodo con un item de valor 2
         node2.setPrevious(node1); //Asignamos node1 como nodo anterior de node2
         assertFalse(node2.isFirstNode()); //Comprobamos que el nodo ya no es el primer nodo de la lista
     }
 
     /*
      * Test para comprobar si un nodo es el último nodo de la lista
      * Para ello, creamos un nodo y comprobamos que es el último nodo de la lista
      * Después, creamos otro nodo y lo asignamos como anterior del primer nodo
      * Comprobamos que el primer nodo ya no es el último nodo de la lista
      */
     @Test
     public void testIsLastNode() {
         LinkedNode<Integer> node1 = new LinkedNode<>(1, null, null); //Creamos un nodo con un item de valor 1
         assertTrue(node1.isLastNode()); //Comprobamos que el nodo es el último nodo de la lista
 
         LinkedNode<Integer> node2 = new LinkedNode<>(2, null, null); //Creamos un nodo con un item de valor 2
         node2.setNext(node1); //Asignamos node1 como nodo siguiente de node2
         assertFalse(node2.isLastNode()); //Comprobamos que el nodo ya no es el último nodo de la lista
     }
 
     /*
      * Test para comprobar si un nodo no es ni el primer ni el último nodo de la lista
      * Para ello, creamos un nodo y comprobamos que no es ni el primer ni el último nodo de la lista
      * Después, creamos otro nodo y lo asignamos como siguiente del primer nodo
      * Comprobamos que el primer nodo ya no es ni el primer ni el último nodo de la lista
      */
     @Test
     public void testIsNotATerminalNode() {
         LinkedNode<Integer> node1 = new LinkedNode<>(1, null, null); //Creamos un nodo con un item de valor 1
         assertFalse(node1.isNotATerminalNode()); //Comprobamos que el nodo no es ni el primer ni el último nodo de la lista
 
         LinkedNode<Integer> node3 = new LinkedNode<>(3, node1, null); //Creamos un nodo con un item de valor 3
         assertFalse(node3.isNotATerminalNode()); //Comprobamos que el nodo no es ni el primer ni el último nodo de la lista
         
         LinkedNode<Integer> node4 = new LinkedNode<>(3, null, node1); //Creamos un nodo con un item de valor 3, asignamos node1 como nodo anterior de node4
         assertFalse(node4.isNotATerminalNode());
         
         LinkedNode<Integer> node2 = new LinkedNode<>(2, node1, node3); //Creamos un nodo con un item de valor 2, asignamos node1 como nodo anterior y node3 como nodo siguiente de node2
         assertTrue(node2.isNotATerminalNode()); //Comprobamos que el nodo ya no es ni el primer ni el último nodo de la lista
     }
 }
 