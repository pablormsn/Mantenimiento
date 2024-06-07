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

    private DoubleLinkedList<Integer> list; //Creamos un objeto de tipo DoubleLinkedList

    /*
     * Método que se ejecuta antes de cada test
     * Se encarga de inicializar el objeto list
     * @BeforeEach indica que se ejecutará antes de cada test
     */
    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList<>(); //Inicializamos el objeto list
    }

    /*
     * Clase anidada que agrupa los tests de los métodos del constructor
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("Constructor tests") //Indicamos el nombre de la clase anidada
    class ConstructorTests { //Clase anidada

        /*
         * Test para comprobar que el constructor de DoubleLinkedList crea una lista vacía
         * Para ello, comprobamos que el tamaño de la lista es 0
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 0
         */
        @Test
        @DisplayName("New list should have size 0") //@DisplayName indica el nombre del test
        void testConstructor() {
            assertEquals(0, list.size()); //Comprobamos que el tamaño de la lista es 0
        }
    }

    /*
     * Clase anidada que agrupa los tests de los métodos prepend
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("Prepend tests") //Indicamos el nombre de la clase anidada
    class PrependTests { //Clase anidada

        @Test
        @DisplayName("Prepend on empty list should increase size") //@DisplayName indica el nombre del test
        /*
         * Test para comprobar que el método prepend aumenta el tamaño de la lista
         * Para ello, añadimos un elemento a la lista y comprobamos que el tamaño de la lista es 1
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 1
         */
        void testPrependOnEmptyList() {
            list.prepend(1); //Añadimos un elemento a la lista
            assertEquals(1, list.size()); //Comprobamos que el tamaño de la lista es 1
        }

        /*
         * Test para comprobar que el método prepend añade un elemento al principio de la lista
         * Para ello, añadimos tres elementos a la lista y comprobamos que el tamaño de la lista es 3
         * Después, compronamos que el primer elemento de la lista es el elemento añadido
         */
        @Test
        @DisplayName("Prepend should add element to the beginning") //@DisplayName indica el nombre del test
        void testPrepend() {
            list.append(1); //Añadimos un elemento a la lista
            list.append(2); //Añadimos un elemento a la lista
            list.prepend(3); //Añadimos un elemento al principio de la lista
            assertEquals(3, list.size()); //Comprobamos que el tamaño de la lista es 3
            assertEquals(Integer.valueOf(3), list.get(0)); //Comprobamos que el primer elemento de la lista es el elemento añadido
        }
    }

    /*
     * Clase anidada que agrupa los tests de los métodos append
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("Append tests") //Indicamos el nombre de la clase anidada
    class AppendTests { //Clase anidada

        /*
         * Test para comprobar que el método append aumenta el tamaño de la lista
         * Para ello, añadimos un elemento a la lista y comprobamos que el tamaño de la lista es 1
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 1
         */
        @Test
        @DisplayName("Append on empty list should increase size")
        void testAppendOnEmptyList() {
            list.append(1); //Añadimos un elemento a la lista
            assertEquals(1, list.size()); //Comprobamos que el tamaño de la lista es 1
        }

        /*
         * Test para comprobar que el método append añade un elemento al final de la lista
         * Para ello, añadimos dos elementos a la lista y comprobamos que el tamaño de la lista es 2
         * Después, comprobamos que el último elemento de la lista es el elemento añadido
         */
        @Test
        @DisplayName("Append should add element to the end")
        void testAppend() {
            list.append(1); //Añadimos un elemento a la lista
            list.append(2); //Añadimos un elemento a la lista
            assertEquals(2, list.size()); //Comprobamos que el tamaño de la lista es 2
            assertEquals(Integer.valueOf(2), list.get(list.size() - 1)); //Comprobamos que el último elemento de la lista es el elemento añadido
        }
    }

    /*
     * Clase anidada que agrupa los tests de los métodos deleteFirst
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("DeleteFirst tests") //Indicamos el nombre de la clase anidada
    class DeleteFirstTests { //Clase anidada

        /*
         * Test para comprobar que el método deleteFirst no cambia el tamaño de la lista si la lista está vacía
         * Para ello, eliminamos el primer elemento de la lista y comprobamos que el tamaño de la lista es 0
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 0
         */
        @Test
        @DisplayName("DeleteFirst on empty list should not change size")
        void testDeleteFirstOnEmptyList() {
            list.deleteFirst(); //Eliminamos el primer elemento de la lista
            assertEquals(0, list.size()); //Comprobamos que el tamaño de la lista es 0, porque la lista estaba vacía
        }

        /*
         * Test para comprobar que el método deleteFirst reduce el tamaño de la lista a 0 si la lista tiene un solo elemento
         * Para ello, añadimos un elemento a la lista, eliminamos el primer elemento de la lista y comprobamos que el tamaño de la lista es 0
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 0
         */
        @Test
        @DisplayName("DeleteFirst on single element list should reduce size to 0")
        void testDeleteFirstOnSingleElementList() {
            list.append(1); //Añadimos un elemento a la lista
            list.deleteFirst(); //Eliminamos el primer elemento de la lista
            assertEquals(0, list.size()); //Comprobamos que el tamaño de la lista es 0, porque la lista tenía un solo elemento
        }

        /*
         * Test para comprobar que el método deleteFirst elimina el primer elemento de la lista
         * Para ello, añadimos tres elementos a la lista, eliminamos el primer elemento de la lista y comprobamos que el tamaño de la lista es 2
         * Después, comprobamos que el primer elemento de la lista es el segundo elemento añadido
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 1
         */
        @Test
        @DisplayName("DeleteFirst should remove first element")
        void testDeleteFirst() {
            list.append(1); //Añadimos un elemento a la lista
            list.append(2); //Añadimos un elemento a la lista
            list.append(5); //Añadimos un elemento a la lista
            list.deleteFirst(); //Eliminamos el primer elemento de la lista
            assertEquals(2, list.size()); //Comprobamos que el tamaño de la lista es 2
            assertEquals(Integer.valueOf(2), list.get(0)); //Comprobamos que el primer elemento de la lista es el segundo elemento añadido
        }
    }

    /*
     * Clase anidada que agrupa los tests de los métodos deleteLast
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("DeleteLast tests") //Indicamos el nombre de la clase anidada
    class DeleteLastTests { //Clase anidada

        /*
         * Test para comprobar que el método deleteLast no cambia el tamaño de la lista si la lista está vacía
         * Para ello, eliminamos el último elemento de la lista y comprobamos que el tamaño de la lista es 0
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 0
         */
        @Test
        @DisplayName("DeleteLast on empty list should not change size")
        void testDeleteLastOnEmptyList() {
            list.deleteLast(); //Eliminamos el último elemento de la lista
            assertEquals(0, list.size()); //Comprobamos que el tamaño de la lista es 0, porque la lista estaba vacía
        }

        /*
         * Test para comprobar que el método deleteLast reduce el tamaño de la lista a 0 si la lista tiene un solo elemento
         * Para ello, añadimos un elemento a la lista, eliminamos el último elemento de la lista y comprobamos que el tamaño de la lista es 0
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 0
         */
        @Test
        @DisplayName("DeleteLast on single element list should reduce size to 0")
        void testDeleteLastOnSingleElementList() {
            list.append(1); //Añadimos un elemento a la lista
            list.deleteLast(); //Eliminamos el último elemento de la lista
            assertEquals(0, list.size()); //Comprobamos que el tamaño de la lista es 0, porque la lista tenía un solo elemento
        }

        /*
         * Test para comprobar que el método deleteLast elimina el último elemento de la lista
         * Para ello, añadimos tres elementos a la lista, eliminamos el último elemento de la lista y comprobamos que el tamaño de la lista es 2
         * Después, comprobamos que el último elemento de la lista es el segundo elemento añadido
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 1
         */
        @Test
        @DisplayName("DeleteLast should remove last element")
        void testDeleteLast() {
            list.append(1); //Añadimos un elemento a la lista
            list.append(2); //Añadimos un elemento a la lista
            list.append(5); //Añadimos un elemento a la lista
            list.deleteLast(); //Eliminamos el último elemento de la lista
            assertEquals(2, list.size()); //Comprobamos que el tamaño de la lista es 2
            assertEquals(Integer.valueOf(2), list.get(list.size() - 1)); //Comprobamos que el último elemento de la lista es el segundo elemento añadido
        }
    }

    /*
     * Clase anidada que agrupa los tests del método first
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("First tests") //Indicamos el nombre de la clase anidada
    class FirstTests { //Clase anidada

        /*
         * Test para comprobar que el método first devuelve null si la lista está vacía
         * Para ello, comprobamos que el primer elemento de la lista es null
         * Usamos el método assertNull para comprobar que el primer elemento de la lista es null
         */
        @Test
        @DisplayName("First on empty list should return null")
        void testFirstOnEmptyList() {
            assertNull(list.first()); //Comprobamos que el primer elemento de la lista es null
        }

        /*
         * Test para comprobar que el método first devuelve el primer elemento de la lista
         * Para ello, añadimos dos elementos a la lista y comprobamos que el primer elemento de la lista es el primer elemento añadido
         * Usamos el método assertEquals para comprobar que el primer elemento de la lista es el primer elemento añadido
         */
        @Test
        @DisplayName("First should return the first element")
        void testFirst() {
            list.append(1); //Añadimos un elemento a la lista
            list.append(2); //Añadimos un elemento a la lista
            assertEquals(Integer.valueOf(1), list.first()); //Comprobamos que el primer elemento de la lista es el primer elemento añadido
        }
    }

    /*
     * Clase anidada que agrupa los tests del método last
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("Last tests") //Indicamos el nombre de la clase anidada
    class LastTests { //Clase anidada

        /*
         * Test para comprobar que el método last devuelve null si la lista está vacía
         * Para ello, comprobamos que el último elemento de la lista es null
         * Usamos el método assertNull para comprobar que el último elemento de la lista es null
         */
        @Test
        @DisplayName("Last on empty list should return null")
        void testLastOnEmptyList() {
            assertNull(list.last()); //Comprobamos que el último elemento de la lista es null
        }

        /*
         * Test para comprobar que el método last devuelve el último elemento de la lista
         * Para ello, añadimos dos elementos a la lista y comprobamos que el último elemento de la lista es el último elemento añadido
         * Usamos el método assertEquals para comprobar que el último elemento de la lista es el último elemento añadido
         */
        @Test
        @DisplayName("Last should return the last element")
        void testLast() {
            list.append(1); //Añadimos un elemento a la lista
            list.append(2); //Añadimos un elemento a la lista
            assertEquals(Integer.valueOf(2), list.last()); //Comprobamos que el último elemento de la lista es el último elemento añadido
        }
    }

    /*
     * Clase anidada que agrupa los tests del método size
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("size() method tests") //Indicamos el nombre de la clase anidada
    class SizeTests { //Clase anidada

        /*
         * Test para comprobar que el método size devuelve 0 si la lista está vacía
         * Para ello, comprobamos que el tamaño de la lista es 0
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 0
         */
        @Test
        @DisplayName("when list is empty")
        void sizeWhenListIsEmpty() {
            assertEquals(0, list.size()); //Comprobamos que el tamaño de la lista es 0
        }

        /*
         * Test para comprobar que el método size devuelve 1 si la lista tiene un solo elemento
         * Para ello, añadimos un elemento a la lista y comprobamos que el tamaño de la lista es 1
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 1
         */
        @Test
        @DisplayName("when list has one element")
        void sizeWhenListHasOneElement() {
            list.append(1); //Añadimos un elemento a la lista
            assertEquals(1, list.size()); //Comprobamos que el tamaño de la lista es 1
        }

        /*
         * Test para comprobar que el método size devuelve el número de elementos si la lista tiene más de un elemento
         * Para ello, añadimos tres elementos a la lista y comprobamos que el tamaño de la lista es 3
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 3
         */
        @Test
        @DisplayName("when list has multiple elements")
        void sizeWhenListHasMultipleElements() {
            list.append(1); //Añadimos un elemento a la lista
            list.append(2); //Añadimos un elemento a la lista
            list.append(3); //Añadimos un elemento a la lista
            assertEquals(3, list.size()); //Comprobamos que el tamaño de la lista es 3
        }
    }

    /*
     * Clase anidada que agrupa los tests del método get
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("get() method tests") //Indicamos el nombre de la clase anidada
    class GetTests { //Clase anidada

        /*
         * Test para comprobar que el método get lanza una excepción si la lista está vacía
         * Para ello, intentamos obtener un elemento de la lista y comprobamos que se lanza una excepción
         * Usamos el método assertThrows para comprobar que se lanza una excepción
         */
        @Test
        @DisplayName("when getting from an empty list")
        void getWhenListIsEmpty() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(0)); //Comprobamos que se lanza una excepción al intentar obtener un elemento de la lista vacía
        }

        /*
         * Test para comprobar que el método get devuelve el elemento de la lista si el índice es 0
         * Para ello, añadimos un elemento a la lista y comprobamos que el elemento de la lista es el elemento añadido
         * Usamos el método assertEquals para comprobar que el elemento de la lista es el elemento añadido
         */
        @Test
        @DisplayName("when getting an element that exists")
        void getExistingElement() {
            list.append(1); //Añadimos un elemento a la lista
            assertEquals(1, list.get(0)); //Comprobamos que el elemento de la lista es el elemento añadido
        }

        /*
         * Test para comprobar que el método get lanza una excepción si el índice es mayor que el tamaño de la lista, es decir, si no existe el elemento
         * Para ello, añadimos un elemento a la lista y comprobamos que se lanza una excepción al intentar obtener un elemento de la lista
         * Usamos el método assertThrows para comprobar que se lanza una excepción
         */
        @Test
        @DisplayName("when getting an element that does not exist")
        void getNonExistingElement() {
            list.append(1); //Añadimos un elemento a la lista
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(1)); //Comprobamos que se lanza una excepción al intentar obtener un elemento que no existe
        }

        /*
         * Test para comprobar que el método get lanza una excepción si el índice es negativo
         * Para ello, añadimos un elemento a la lista y comprobamos que se lanza una excepción al intentar obtener un elemento de la lista
         * Usamos el método assertThrows para comprobar que se lanza una excepción
         */
        @Test
        @DisplayName("when getting an element that does not exist")
        void getNonExistingElementNegative() {
            list.append(1); //Añadimos un elemento a la lista
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1)); //Comprobamos que se lanza una excepción al intentar obtener un elemento con un índice negativo
        }
    }

    /*
     * Clase anidada que agrupa los tests del método contains
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("contains() method tests") //Indicamos el nombre de la clase anidada
    class ContainsTests { //Clase anidada

        /*
         * Test para comprobar que el método contains devuelve false si la lista está vacía
         * Para ello, comprobamos que la lista no contiene ningún elemento
         * Usamos el método assertFalse para comprobar que la lista no contiene un elemento
         */
        @Test
        @DisplayName("when list is empty")
        void containsWhenListIsEmpty() {
            assertFalse(list.contains(1)); //Comprobamos que la lista no contiene un elemento
        }

        /*
         * Test para comprobar que el método contains devuelve true si la lista contiene el elemento
         * Para ello, añadimos un elemento a la lista y comprobamos que la lista contiene un elemento
         * Usamos el método assertTrue para comprobar que la lista contiene un elemento
         */
        @Test
        @DisplayName("when list contains the element")
        void containsWhenElementExists() {
            list.append(7); //Añadimos un elemento a la lista
            assertTrue(list.contains(7)); //Comprobamos que la lista contiene un elemento
        }

        /*
         * Test para comprobar que el método contains devuelve false si la lista no contiene el elemento
         * Para ello, añadimos un elemento a la lista y comprobamos que la lista no contiene el elemento
         * Usamos el método assertFalse para comprobar que la lista no contiene un elemento
         */
        @Test
        @DisplayName("when list does not contain the element")
        void containsWhenElementDoesNotExist() {
            list.append(1); //Añadimos un elemento a la lista
            assertFalse(list.contains(2)); //Comprobamos que la lista no contiene ese elemento
        }
    }

    /*
     * Clase anidada que agrupa los tests del método remove
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("remove() method tests") //Indicamos el nombre de la clase anidada
    class RemoveTests { //Clase anidada

        /*
         * Método que se ejecuta antes de cada test
         * Se encarga de añadir cinco elementos a la lista
         * @BeforeEach indica que se ejecutará antes de cada test
         */
        @BeforeEach
        void populateList() { //Añade cinco elementos a la lista. Esto lo hacemos para no tener que añadir los elementos en cada test
            list.append(1); //Añadimos un elemento a la lista
            list.append(2); //Añadimos un elemento a la lista
            list.append(3); //Añadimos un elemento a la lista
            list.append(4); //Añadimos un elemento a la lista
            list.append(5); //Añadimos un elemento a la lista
        }

        /*
         * Test para comprobar que el método remove reduce el tamaño de la lista si el elemento existe
         * Para ello, eliminamos un elemento de la lista y comprobamos que el tamaño de la lista es 4
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 4
         */
        @Test
        @DisplayName("Removing existing element")
        void removeExistingElement() {
            list.remove(3); //Eliminamos un elemento de la lista
            assertFalse(list.contains(3)); //Comprobamos que la lista no contiene ese elemento
            assertEquals(4, list.size()); //Comprobamos que el tamaño de la lista es 4
        }

        /*
         * Test para comprobar que el método remove no cambia el tamaño de la lista si el elemento no existe
         * Para ello, eliminamos un elemento que no existe en la lista y comprobamos que el tamaño de la lista es 5
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 5
         */
        @Test
        @DisplayName("Removing non-existing element does nothing")
        void removeNonExistingElement() {
            list.remove(10); //Eliminamos un elemento que no existe en la lista
            assertEquals(5, list.size()); //Comprobamos que el tamaño de la lista es 5
        }

        /*
         * Test para comprobar que el método remove reduce el tamaño de la lista si el elemento existe y es el único elemento de la lista
         * Para ello, eliminamos el único elemento de la lista y comprobamos que el tamaño de la lista es 0
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 0
         */
        @Test
        @DisplayName("Removing from list with one element")
        void removeElementFromSingleElementList() {
            DoubleLinkedList<Integer> singleElementList = new DoubleLinkedList<>(); //Creamos una lista con un solo elemento
            singleElementList.append(10); //Añadimos un elemento a la lista
            singleElementList.remove(10); //Eliminamos el único elemento de la lista
            assertEquals(0, singleElementList.size()); //Comprobamos que el tamaño de la lista es 0
        }

        /*
         * Test para comprobar que el método remove reduce el tamaño de la lista si el elemento existe y es el primer elemento de la lista
         * Para ello, eliminamos el primer elemento de la lista y comprobamos que el tamaño de la lista es 4
         * Además, comprobamos que el último elemento de la lista es el 4 ahora
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 4
         */
        @Test
        @DisplayName("Removing last element")
        void removeLastElement() {
            list.remove(5); //Eliminamos el último elemento de la lista
            assertFalse(list.contains(5)); //Comprobamos que la lista no contiene ese elemento
            assertEquals(4, list.get(list.size()-1)); //Comprobamos que ahora el último elemento de la lista es el 4
            assertEquals(4, list.size()); //Comprobamos que el tamaño de la lista es 4
        }

        /*
         * Test para comprobar que el método remove reduce el tamaño de la lista si el elemento existe y es el último elemento de la lista
         * Para ello, eliminamos el último elemento de la lista y comprobamos que el tamaño de la lista es 4
         * Además, comprobamos que el primer elemento de la lista es el 2 ahora
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 4
         */
        @Test
        @DisplayName("Removing first element")
        void removeFirstElement() {
            list.remove(1); //Eliminamos el primer elemento de la lista
            assertFalse(list.contains(1)); //Comprobamos que la lista no contiene ese elemento
            assertEquals(2, list.get(0)); //Comprobamos que ahora el primer elemento de la lista es el 2
            assertEquals(4, list.size()); //Comprobamos que el tamaño de la lista es 4
        }
    }

    /*
     * Clase anidada que agrupa los tests del método sort
     */
    @Nested //Agrupamos los tests en una clase anidada
    @DisplayName("sort(Comparator<? super T> comparator) method tests") //Indicamos el nombre de la clase anidada
    class SortTests { //Clase anidada

        /*
         * Test para comprobar que el método sort no cambia el tamaño de la lista si la lista está vacía
         * Para ello, ordenamos la lista y comprobamos que el tamaño de la lista es 0
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 0
         */
        @Test
        @DisplayName("when sorting an empty list")
        void sortWhenListIsEmpty() {
            list.sort(Comparator.naturalOrder()); //Ordenamos la lista
            assertEquals(0, list.size()); //Comprobamos que el tamaño de la lista es 0
        }

        /*
         * Test para comprobar que el método sort no cambia el tamaño de la lista si la lista tiene un solo elemento
         * Para ello, añadimos un elemento a la lista, ordenamos la lista y comprobamos que el tamaño de la lista es 1
         * Usamos el método assertEquals para comprobar que el tamaño de la lista es 1
         */
        @Test
        @DisplayName("when sorting a list with one element")
        void sortWhenListHasOneElement() {
            list.append(1); //Añadimos un elemento a la lista
            list.sort(Comparator.naturalOrder()); //Ordenamos la lista
            assertEquals(1, list.get(0)); //Comprobamos que el primer elemento de la lista es el elemento añadido
        }

        /*
         * Test para comprobar que el método sort ordena la lista si la lista tiene más de un elemento
         * Para ello, añadimos tres elementos a la lista, ordenamos la lista y comprobamos que la lista está ordenada
         * Usamos el método assertEquals para comprobar que la lista está ordenada
         */
        @Test
        @DisplayName("when sorting a list with multiple elements")
        void sortWhenListHasMultipleElements() {
            list.append(3); //Añadimos un elemento a la lista
            list.append(1); //Añadimos un elemento a la lista
            list.append(2); //Añadimos un elemento a la lista
            list.sort(Comparator.naturalOrder()); //Ordenamos la lista
            assertEquals(1, list.get(0)); //Comprobamos que el primer elemento de la lista es el 1
            assertEquals(2, list.get(1)); //Comprobamos que el segundo elemento de la lista es el 2
            assertEquals(3, list.get(2)); //Comprobamos que el tercer elemento de la lista es el 3
        }
    }
}