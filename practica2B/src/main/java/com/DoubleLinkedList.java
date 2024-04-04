/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */

package com;

import java.util.Comparator;

public class DoubleLinkedList<T> implements DoubleLinkedQueue<T> {

    private LinkedNode<T> first;
    private LinkedNode<T> last;
    private int size;

    public DoubleLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void prepend(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, null, first);
        if (first == null) {
            first = newNode;
            last = newNode;
        }else{
            newNode.setNext(first);
            first.setPrevious(newNode);
            first = newNode;
        }
        size++;
    }

    @Override
    public void append(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, last, null);
        if (last == null) {
            first = newNode;
            last = newNode;
        }else{
            newNode.setPrevious(last);
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    @Override
    public void deleteFirst() {
        if (first != null) {
            LinkedNode<T> nextNode = first.getNext();
            if (nextNode != null) {
                nextNode.setPrevious(null);
            } else {
                last = null;
            }
            first = nextNode;
            size--;
        }
    }

    @Override
    public void deleteLast() {
        if (last == null) {
            return;
        }
        LinkedNode<T> previousNode = last.getPrevious();
        if (previousNode != null) {
            previousNode.setNext(null);
        } else {
            first = null;
        }
        last = previousNode;
        size--;
    }

    @Override
    public T first() {
        if (first != null) {
            return first.getItem();
        }
        return null;
    }

    @Override
    public T last() {
        if (last != null) {
            return last.getItem();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        LinkedNode<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getItem();
    }

    @Override
    public boolean contains(T value){
        LinkedNode<T> current = first;
        while (current != null) {
            if (current.getItem().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void remove(T value){
        LinkedNode<T> current = first;
        while (current != null) {
            if (current.getItem().equals(value)) {
                if (current == first) {
                    deleteFirst();
                } else if (current == last) {
                    deleteLast();
                } else {
                    LinkedNode<T> previous = current.getPrevious();
                    LinkedNode<T> next = current.getNext();
                    previous.setNext(next);
                    next.setPrevious(previous);
                    size--;
                }
            }
            current = current.getNext();
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator){
        if (size < 2) {
            return;
        }
        LinkedNode<T> current = first;
        while (current != null) {
            LinkedNode<T> next = current.getNext();
            while (next != null) {
                if (comparator.compare(current.getItem(), next.getItem()) > 0) {
                    T temp = current.getItem();
                    current.setItem(next.getItem());
                    next.setItem(temp);
                }
                next = next.getNext();
            }
            current = current.getNext();
        }
    }
}
