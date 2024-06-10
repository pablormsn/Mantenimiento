/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */

package org.mps.boundedqueue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class BoundedQueueTest {

    /*
    * Tests del constructor de ArrayBoundedQueue
    * Anidamos los tests en una clase interna
    * @Nested permite agrupar tests en una clase interna
    * @DisplayName permite dar un nombre a la clase interna
    */
    @Nested
    @DisplayName("Tests del constructor")
    class testConstructor{

        /*
         * Test para comprobar que se lanza una excepción si se intenta crear una cola con capacidad negativa
         * Se espera que se lance una excepción de tipo IllegalArgumentException
         * Para ello, se utiliza el método assertThatThrownBy de AssertJ, que permite comprobar que se lanza una excepción
         */
        @Test
        public void testNegativeCapacity() {
            assertThatThrownBy(() -> new ArrayBoundedQueue<>(-1)) //Debería lanzar una excepción si intentamos crear una cola con capacidad negativa
                    .isInstanceOf(IllegalArgumentException.class); //.instanceOf() comprueba que la excepción lanzada es de tipo IllegalArgumentException
        }
    
        /*
         * Test para comprobar que se lanza una excepción si se intenta añadir a la cola un elemento nulo
         * Se espera que se lance una excepción de tipo IllegalArgumentException
         * Para ello, se utiliza el método assertThatThrownBy de AssertJ, que permite comprobar que se lanza una excepción
         */
        @Test
        public void testNullElement() {
            assertThatThrownBy(() -> new ArrayBoundedQueue<>(0)) //Debería lanzar una excepción si intentamos crear una cola con capacidad negativa
                    .isInstanceOf(IllegalArgumentException.class); //.instanceOf() comprueba que la excepción lanzada es de tipo IllegalArgumentException
        }
    }

    /*
     * Tests del método put de ArrayBoundedQueue
     * Anidamos los tests en una clase interna
     * @Nested permite agrupar tests en una clase interna
     * @DisplayName permite dar un nombre a la clase interna
     */
    @Nested
    @DisplayName("Tests del método put")
    class TestPut{

        /*
         * Test para comprobar que se lanza una excepción si se intenta añadir un elemento a una cola llena
         * Se espera que se lance una excepción de tipo FullBoundedQueueException
         * Para ello, se utiliza el método assertThatThrownBy de AssertJ, junto con .isInstanceOf() para comprobar que se lanza una excepción de tipo FullBoundedQueueException
         */
        @Test
        public void testPutToFullQueueException() {
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(2); //Creamos una cola con capacidad 2
    
            queue.put(1); //Insertamos un elemento
            queue.put(2); //Insertamos otro elemento
    
            assertThatThrownBy(() -> queue.put(3)) //Debería lanzar una excepción si intentamos insertar un elemento en una cola llena
                    .isInstanceOf(FullBoundedQueueException.class); //.instanceOf() comprueba que la excepción lanzada es de tipo FullBoundedQueueException
        }
    
        /*
        *Este test debería funcionar, porque es una cola circular, por lo que si la cola está llena, 
        *el elemento que se inserta debería sustituir al primer elemento insertado, 
        *por lo que debería devolver el elemento en la primera posición, que sería el último elemento insertado.
        *Sin embargo, tal y como está implementado el código, este test no funciona correctamente, porque cuando
        *la cola está llena e intentas insertar un elemento, el método put() lanza una excepción, 
        *por lo que no se llega a ejecutar la línea de código
        */
        
        // @Test
        // public void testPutToFullQueue() throws FullBoundedQueueException{
        //     BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5);
    
        //     for (int i = 0; i < 5; i++) {
        //         queue.put(i);
        //     }
    
        //     assertThat(queue.size()).isEqualTo(5);
    
        //     //Si intentamos insertar un elemento en una cola llena, debería devolver el primer elemento insertado, sin embargo tal y como está implementado el código, este no funciona correctamente
        //     //Debido a que el método put() lanza una excepción si la cola está llena, no se llega a ejecutar la línea de código que devuelve el primer elemento insertado
        //     //Sin embargo como no lo ponía en el enunciado, no lo hemos arreglado
        //     try{
        //         queue.put(5);
        //     }catch (FullBoundedQueueException e){
        //         assertThat(queue.get()).isEqualTo(5);
        //         assertThat(queue.size()).isEqualTo(5);
        //     }
        // }

        /*
         * Test para comprobar que se lanza una excepción si se intenta añadir un elemento nulo a la cola
         * Se espera que se lance una excepción de tipo IllegalArgumentException
         * Para ello, se utiliza el método assertThatThrownBy de AssertJ, junto con .isInstanceOf() para comprobar que se lanza una excepción de tipo IllegalArgumentException
         */
        @Test
        public void testPutNullValue(){
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(2); //Creamos una cola con capacidad 2
            assertThatThrownBy(() -> queue.put(null)) //Debería lanzar una excepción si intentamos insertar un elemento nulo
                .isInstanceOf(IllegalArgumentException.class); //.instanceOf() comprueba que la excepción lanzada es de tipo IllegalArgumentException
        }

        /*
         * Test para comprobar que el método put inserta el elemento en la cola
         * Se espera que todos los elementos se inserten en orden
         * Para ello, se utiliza el método assertThat de AssertJ, con .isEqualTo() para comprobar que el valor devuelto por el método es el esperado
         */
        @Test
        public void testPut() {

            //Arrange
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3); //Creamos una cola de capacidad 3
            queue.put(1); //Insertamos un elemento
            queue.put(2); //Insertamos otro elemento

            //Act
            Integer value = queue.getLast(); //Recuperamos el último elemento insertado

            //Assert
            assertThat(value).isEqualTo(2); //El valor recuperado debería ser el ultimo elemento insertado
            assertThat(queue.size()).isEqualTo(2); //La cola debería tener tamaño 2 después de insertar 2 elementos
        }
    }


    @Nested
    @DisplayName ("Tests de los métodos que tienen que ver con el tamaño y capacidad de la cola")
    class TestCapacity{

        /*
         * Test para comprobar que el método isFull() devuelve true si la cola está llena
         * Se espera que el método devuelva true
         * Para ello, se utiliza el método assertThat de AssertJ, con .isTrue() para comprobar que el método devuelve true, o .isFalse() para comprobar que devuelve false
         * assertThat() es una aserción que permite comprobar que el valor devuelto por un método es el esperado
         */
        @Test
        public void testIsFull() {
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(2); //Creamos una cola de capacidad 2
            BoundedQueue<Integer> queue2 = new ArrayBoundedQueue<>(3); //Creamos una cola de capacidad 3

            queue.put(1); //Insertamos un elemento
            queue.put(2); //Insertamos otro elemento

            queue2.put(1); //Insertamos un elemento
            queue2.put(2); //Insertamos otro elemento

            assertThat(queue.isFull()).isTrue(); //La cola debería estar llena después de insertar 2 elementos en una cola de capacidad 2
            assertThat(queue2.isFull()).isFalse(); //La cola no debería estar llena después de insertar 2 elementos en una cola de capacidad 3
        }

        /*
         * Test para comprobar que el método isEmpty() devuelve true si la cola está vacía
         * Se espera que el método devuelva true
         * Para ello, se utiliza el método assertThat de AssertJ, con .isTrue() para comprobar que el método devuelve true, o .isFalse() para comprobar que devuelve false
         */
        @Test
        public void testIsEmpty() {
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3); //Creamos una cola de capacidad 3

            assertThat(queue.isEmpty()).isTrue(); //La cola debería estar vacía inicialmente

            queue.put(1); //Insertamos un elemento

            assertThat(queue.isEmpty()).isFalse(); //La cola no debería estar vacía después de insertar un elemento
        }

        /*
         * Test para comprobar que el método size() devuelve el número de elementos de la cola
         * Se espera que el método devuelva el número de elementos de la cola
         * Para ello, se utiliza el método assertThat de AssertJ, con .isEqualTo() para comprobar que el método devuelve el valor esperado
         */
        @Test
        public void testSize() {
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

            queue.put(1);
            queue.put(2);

            assertThat(queue.size()).isEqualTo(2); //La cola debería tener tamaño 2 después de insertar 2 elementos
        }

    }
    
    /*
     * Tests de los métodos get, getFirst y getLast de ArrayBoundedQueue
     * Anidamos los tests en una clase interna
     * @Nested permite agrupar tests en una clase interna
     * @DisplayName permite dar un nombre a la clase interna
     */
    @Nested
    @DisplayName("Tests de los métodos get, getFirst y getLast")
    class TestGet{

        /*
         * Test para comprobar que se recupera el primer elemento de la cola correctamente y que se elimina de la cola
         */
        @Test
        public void testGet() {
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3); //Creamos una cola de capacidad 5

            queue.put(1); //Insertamos un elemento
            queue.put(2); //Insertamos otro elemento
            queue.put(3); //Insertamos otro elemento

            Integer value = queue.get();

            assertThat(value).isEqualTo(1); //El valor recuperado debería ser el primer elemento insertado
            assertThat(queue.size()).isEqualTo(2); //Después de recuperar un elemento, la cola debería tener tamaño 2, porque se ha eliminado un elemento
        }

        /*
         * Test para comprobar que se lanza una excepción si se intenta recuperar un elemento de una cola vacía
         * Se espera que se lance una excepción de tipo EmptyBoundedQueueException
         * Para ello, se utiliza el método assertThatThrownBy de AssertJ, junto con .instanceOf(), que permite comprobar que se lanza una excepción de la clase esperada
         */
        @Test
        public void testGetFromEmptyQueue() {
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3); //Creamos una cola de capacidad 3
    
            assertThatThrownBy(() -> queue.get()) //Debería lanzar una excepción si intentamos recuperar un elemento de una cola vacía
                    .isInstanceOf(EmptyBoundedQueueException.class); //.instanceOf() comprueba que la excepción lanzada es de tipo EmptyBoundedQueueException
        }
    
        /*
         * Test para comprobar que se obtiene el índice del primer elemento de la cola
         * Se espera que el índice devuelto sea 0
         * Para ello, se utiliza el método assertThat de AssertJ, con .isEqualTo() para comprobar que el valor devuelto por el método es el esperado
         */
        @Test
        public void testGetFirst() {
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3); //Creamos una cola de capacidad 3
    
            queue.put(1); //Insertamos un elemento
            queue.put(2); //Insertamos otro elemento
    
            assertThat(queue.getFirst()).isEqualTo(0); //El índice del primer elemento debería ser 0
        }
    
        /*
         * Test para comprobar que se obtiene el índice de la primera posición libre de la cola
         * Se espera que el índice devuelto sea 2
         * Para ello, se utiliza el método assertThat de AssertJ, con .isEqualTo() para comprobar que el valor devuelto por el método es el esperado
         */
        @Test
        public void testGetLast() {
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);
    
            queue.put(1);
            queue.put(2);
    
            assertThat(queue.getLast()).isEqualTo(2); //El índice del próximo elemento libre debería ser 2
        }

        /*
         * Test para comprobar que se obtiene el índice del próximo elemento libre de la cola cuando la cola está llena
         * Se espera que el índice devuelto sea 0, porque el método advance() devuelve el módulo de la longitud del buffer
         * Para ello, se utiliza el método assertThat de AssertJ, con .isEqualTo() para comprobar que el valor devuelto por el método es el esperado
         */
        @Test
        public void testGetLastOfFullQueue(){
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(5); //Creamos una cola de capacidad 5

            for(int i=0; i<5; i++){
                queue.put(i); //Insertamos 5 elementos
            }

            assertThat(queue.getLast()).isEqualTo(0); //El índice del próximo elemento libre debería ser 0, porque el método advance() devuelve el módulo de la longitud del buffer
        }
    }
    
    /*
     * Tests del método iterator de ArrayBoundedQueue
     * Anidamos los tests en una clase interna
     * @Nested permite agrupar tests en una clase interna
     * @DisplayName permite dar un nombre a la clase interna
     */
    @Nested
    @DisplayName("Tests del método iterator")
    class TestIterator{

        /*
         * Test para comprobar que el iterador de la cola tiene un siguiente elemento
         * Se espera que el método hasNext() devuelva true cuando tenga un siguiente elemento, y false cuando no tenga más elementos
         * Para ello, se utiliza el método assertThat de AssertJ, con .isTrue() o .isFalse() para comprobar que el valor devuelto por el método es el esperado
         */
        @Test
        public void testIteratorHasNext(){
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(2); //Creamos una cola de capacidad 2
            queue.put(1); //Insertamos un elemento
            Iterator<Integer> iterator = queue.iterator(); //Creamos un iterador de la cola

            assertThat(iterator.hasNext()).isTrue(); //El iterador debería tener un siguiente elemento, que sería el primer elemento insertado, porque estamos al principio del iterador
            assertThat(iterator.next()).isNotNull(); //El siguiente elemento debería ser el primer elemento insertado
            assertThat(iterator.hasNext()).isFalse(); //Como hemos llegado al final de la cola, el iterador no debería tener más elementos
        }

        /*
         * Test para comprobar que el método next() del iterador de la cola devuelve el elemento correcto
         * Se espera que el método devuelva el elemento correcto
         * Para ello, se utiliza el método assertThat de AssertJ, con .isEqualTo() para comprobar que el valor devuelto por el método es el esperado
         */
        @Test
        public void testIteratorNext(){
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(2);
            queue.put(1); //Insertamos un elemento
            queue.put(2); //Insertamos otro elemento
            Iterator<Integer> iterator = queue.iterator(); //Creamos un iterador de la cola

            assertThat(iterator.next()).isEqualTo(1); //El primer elemento devuelto por el iterador debería ser el primer elemento insertado
            assertThat(iterator.next()).isEqualTo(2); //El segundo elemento devuelto por el iterador debería ser el segundo elemento insertado
        }

        /*
         * Test para comprobar que se lanza una excepción si se intenta acceder al siguiente elemento de un iterador de una cola vacía
         * Se espera que se lance una excepción de tipo NoSuchElementException
         * Para ello, se utiliza el método assertThatThrownBy de AssertJ, junto con .isInstanceOf(), que permite comprobar que se lanza una excepción de la clase esperada y .hasMessage() para comprobar que el mensaje de la excepción es el esperado
         */
        @Test
        public void testIteratorNextEmptyQueue(){
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(2); //Creamos una cola de capacidad 2
            Iterator<Integer> iterator = queue.iterator(); //Creamos un iterador de la cola
            assertThatThrownBy(() -> iterator.next()) //Debería lanzar una excepción si intentamos acceder al siguiente elemento de un iterador de una cola vacía
                    .isInstanceOf(NoSuchElementException.class) //Comprobamos que la excepción lanzada es de tipo NoSuchElementException
                    .hasMessage("next: bounded queue iterator exhausted"); //Comprobamos que el mensaje de la excepción es el esperado
        }

        /*
         * Test para comprobar que se lanza una excepción si se intenta acceder al siguiente elemento de un iterador de una cola que ha llegado al final
         * Se espera que se lance una excepción de tipo NoSuchElementException
         * Para ello, se utiliza el método assertThatThrownBy de AssertJ, junto con .isInstanceOf(), que permite comprobar que se lanza una excepción de la clase esperada y .hasMessage() para comprobar que el mensaje de la excepción es el esperado
         */
        @Test
        @DisplayName("Test que comprueba que se lance una excepción cuando llega al final de la lista")
        public void testIteratorNextException(){
            BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(1); //Creamos una cola de capacidad 1
            queue.put(1); //Insertamos un elemento
            Iterator<Integer> iterator = queue.iterator(); //Creamos un iterador de la cola

            assertThat(iterator.next()).isNotNull(); //El primer elemento devuelto por el iterador debería ser el primer elemento insertado, por lo que no es nulo
            assertThatThrownBy(() -> iterator.next()) //Debería lanzar una excepción si intentamos acceder al siguiente elemento de un iterador que ha llegado al final
                    .isInstanceOf(NoSuchElementException.class) //Comprobamos que la excepción lanzada es de tipo NoSuchElementException
                    .hasMessage("next: bounded queue iterator exhausted"); //Comprobamos que el mensaje de la excepción es el esperado
        }
    }
}



