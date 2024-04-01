package deque;

import java.util.Iterator;

public interface Deque<T> extends Iterable<T> {
    /* Adds an item of type T to the front of the deque */
    void addFirst(T item);

    /* Adds an item of type T to the end of the deque */
    void addLast(T item);

    /* Returns true if deque is empty, false otherwise */
    boolean isEmpty();

    /* Returns the number of items in the deque */
    int size();

    /* Prints the items in the deque from first to last, separated by a space.
    Once all the items have been printed, print out a new line */
    void printDeque();

    /* Removes and returns the item at the front of the deque.
    If no such item exists, returns null */
    T removeFirst();

    /* Removes and returns the item at the end of the deque. If no such item exists, returns null */
    T removeLast();

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque
    */
    T get(int index);

    /* Returns an iterator for the deque */
    Iterator<T> iterator();

    /* Returns whether or not the parameter o is equal to the Deque.
     o is considered equal if it is a Deque and if it contains the same contents
     */
    boolean equals(Object o);

}
