/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */

package com;

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
}
