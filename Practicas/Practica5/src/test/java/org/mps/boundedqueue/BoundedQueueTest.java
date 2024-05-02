package org.mps.boundedqueue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BoundedQueueTest {

    @Test
    public void testPutAndGet() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

        queue.put(1);
        queue.put(2);
        Integer value = queue.get();

        assertThat(value).isEqualTo(1); //El valor recuperado debería ser el primer elemento insertado
        assertThat(queue.size()).isEqualTo(1); //Después de recuperar un elemento, la cola debería tener tamaño 1
    }

    @Test
    public void testIsFull() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(2);

        queue.put(1);
        queue.put(2);

        assertThat(queue.isFull()).isTrue(); //La cola debería estar llena después de insertar 2 elementos en una cola de capacidad 2
    }

    @Test
    public void testIsEmpty() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

        assertThat(queue.isEmpty()).isTrue(); //La cola debería estar vacía inicialmente

        queue.put(1);

        assertThat(queue.isEmpty()).isFalse(); //La cola no debería estar vacía después de insertar un elemento
    }

    @Test
    public void testSize() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

        queue.put(1);
        queue.put(2);

        assertThat(queue.size()).isEqualTo(2); //La cola debería tener tamaño 2 después de insertar 2 elementos
    }

    @Test
    public void testPutToFullQueue() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(2);

        queue.put(1);
        queue.put(2);

        assertThatThrownBy(() -> queue.put(3)) //Debería lanzar una excepción si intentamos insertar un elemento en una cola llena
                .isInstanceOf(FullBoundedQueueException.class);
    }

    @Test
    public void testGetFromEmptyQueue() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

        assertThatThrownBy(queue::get) //Debería lanzar una excepción si intentamos recuperar un elemento de una cola vacía
                .isInstanceOf(EmptyBoundedQueueException.class);
    }

    @Test
    public void testGetFirst() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

        queue.put(1);
        queue.put(2);

        assertThat(queue.getFirst()).isEqualTo(0); //El índice del primer elemento debería ser 0
    }

    @Test
    public void testGetLast() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

        queue.put(1);
        queue.put(2);

        assertThat(queue.getLast()).isEqualTo(2); //El índice del último elemento debería ser 2
    }

    @Test
    public void testIterator() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

        queue.put(1);
        queue.put(2);

        assertThat(queue).containsExactly(1, 2); //El iterador debería devolver los elementos en el orden correcto
    }

    @Test
    public void testIteratorEmptyQueue() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

        assertThat(queue).isEmpty(); //El iterador de una cola vacía debería devolver una colección vacía
    }

    @Test
    public void testIteratorNextOnEmptyQueue() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

        assertThatThrownBy(queue.iterator()::next) //Debería lanzar una excepción si intentamos llamar a next() en un iterador vacío
                .isInstanceOf(java.util.NoSuchElementException.class);
    }

    @Test
    public void testNegativeCapacity() {
        assertThatThrownBy(() -> new ArrayBoundedQueue<>(-1)) //Debería lanzar una excepción si intentamos crear una cola con capacidad negativa
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testNullElement() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(3);

        assertThatThrownBy(() -> queue.put(null)) //Debería lanzar una excepción si intentamos insertar un elemento nulo
                .isInstanceOf(IllegalArgumentException.class);
    }
}



