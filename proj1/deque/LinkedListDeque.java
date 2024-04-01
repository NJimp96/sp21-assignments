package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    public class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node sentinel;
    private int size;
    private Node last;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        last = sentinel.next;
    }

    /*Creates an empty linked list deque*/
    public LinkedListDeque(T x) {
        size = 1;
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(x, null, sentinel);
        sentinel.prev = sentinel.next;
        last = sentinel.prev;
    }

    @Override
    public void addFirst(T y) {
        size += 1;
        Node newFirst = new Node(y, sentinel.next, sentinel);
        sentinel.next.prev = newFirst;
        sentinel.next = newFirst;
        last = sentinel.prev;
    }

    /* Adds an item of type T to the front of the deque. Item is never null*/
    public void addLast(T z) {
        size += 1;
        Node newLast = new Node(z, sentinel, last);
        last.next = newLast;
        sentinel.prev = newLast;
        last = sentinel.prev;
    }

    /* Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /* Returns the number of items in the deque */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space.
    Once all the items have been printed, print out a new line */
    public void printDeque() {
        Node currentNode = sentinel.next;

        while (currentNode != sentinel) {
            System.out.print(currentNode.item);
            System.out.print(" ");
            currentNode = currentNode.next;
        }
        System.out.print("\n");
    }

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;

        Node formerFirst = sentinel.next;

        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        formerFirst.next = null;
        formerFirst.prev = null;

        last = sentinel.prev;

        return formerFirst.item;
    }

    /* Removes and returns the item at the end of the deque. If no such item exists, returns null */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        Node formerLast = last;

        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        formerLast.next = null;
        formerLast.prev = null;

        last = sentinel.prev;

        return formerLast.item;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque
     */
    public T get(int index) {
        if (index >= size || index < 0) {
            System.out.println("Index out of bounds for the given Linked List Deque");
            return null;
        }

        Node currentNode = sentinel;

        for (int i = 0; i <= index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.item;
    }

    /* Recursively goes through the nodes in a linked list deque until the required index is reached. */
    private T getNodeRecursive(int index, Node currentNode) {

        if (index == 0) {
            return currentNode.item;
        }

        return getNodeRecursive(index - 1, currentNode.next);
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque.
    Uses recursion
     */
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            System.out.println("Index out of bounds for the given Linked List Deque");
            return null;
        }

        return getNodeRecursive(index, sentinel.next);
    }

    /*Creates iterator class to make the Deque iterable*/
    private class LLIterator implements Iterator<T> {
        Node wizpos;

        /*Initializes iterator*/
        public LLIterator() {
            wizpos = sentinel.next;
        }

        @Override
        /*Checks if collection has next item*/
        public boolean hasNext() {
            return wizpos != sentinel;
        }

        /*Outputs the next item in the Deque*/
        public T next() {
            T returnItem = wizpos.item;
            wizpos = wizpos.next;
            return returnItem;
        }
    }

    /*Public iterator*/
    public Iterator<T> iterator() {
        return new LLIterator();
    }

    /*Checks if element exists in the LinkedListDeque*/
    private boolean contains(T x) {
        for (T y: this) {
            if (x.equals(y)) {
                return true;
            }
        }

        return false;
    }

    /* Returns whether or not the parameter o is equal to the Deque.
     o is considered equal if it is a Deque and if it contains the same contents
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Deque) {
            if (this.size != ((Deque<T>) other).size()) {
                return false;
            }
            for (int i = 0; i < ((Deque<T>) other).size(); i++) {
                if (!(this.contains(((Deque<T>) other).get(i)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
