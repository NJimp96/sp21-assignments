package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    //private int first;
    private int nextFirst;
    //private int last;
    private int nextLast;
    private int size;

    /*min size of arraydeque for resizing smaller*/
    private int minResize = 8;

    /*Creates an empty array deque*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    /*Moves the pointer to the base deque to the left*/
    private int movePointerLeft(int pointerPos, int itemsLength) {
        int newPointerPos = pointerPos - 1;

        if (newPointerPos < 0) {
            newPointerPos += itemsLength;
        }
        return newPointerPos;
    }

    /*Moves the pointer to the base deque to the right*/
    private int movePointerRight(int pointerPos, int itemsLength) {
        int newPointerPos = pointerPos + 1;

        if (newPointerPos >= itemsLength) {
            newPointerPos -= itemsLength;
        }
        return newPointerPos;
    }

    /*Transfers items from one array to the another*/
    private int transferArrayItems(int oldStartingPoint, int newStartingPoint, T[] newArray) {
        int oldPointer = oldStartingPoint;
        int newPointer = newStartingPoint;
        int oldLast = movePointerLeft(nextLast, items.length);

        newArray[newPointer] = items[oldPointer];


        while (oldPointer != oldLast) {
            oldPointer = movePointerRight(oldPointer, items.length);
            newPointer = movePointerRight(newPointer, newArray.length);
            newArray[newPointer] = items[oldPointer];
        }
        return movePointerRight(newPointer, newArray.length);
    }

    /*Resizes array to ensure length of base deque is bigger than the list of items
    but also that the items constitute 25% of the length of the base array*/
    private void resize() {
        if ((size >= items.length - 1) || (items.length / 4 > size & size > minResize)) {
            T[] newitems = (T[]) new Object[8];

            if (size >= items.length - 1) {
                int arrayLength = items.length * 2;
                newitems = (T[]) new Object[arrayLength];
            } else if (items.length / 4 > size & size > minResize) {
                int arrayLength = items.length / 2;
                newitems = (T[]) new Object[arrayLength];
            }

            int newNextFirst = newitems.length / 4;
            int oldItemsPointer = movePointerRight(nextFirst, items.length);

            int newNextLast =  transferArrayItems(oldItemsPointer, newNextFirst, newitems);
            items = newitems;
            nextFirst = movePointerLeft(newNextFirst, items.length);
            nextLast = newNextLast;
        }

    }

    public void addFirst(T item) {
        size += 1;
        items[nextFirst] = item;
        int first = nextFirst;
        nextFirst = movePointerLeft(first, items.length);

        resize();
    }

    /* Adds an item of type T to the end of the deque */
    public void addLast(T item) {
        size += 1;
        items[nextLast] = item;
        int last = nextLast;
        nextLast = movePointerRight(last, items.length);

        resize();
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
        if (size == 0) {
            System.out.println("Deque is empty");
        } else {

            int first = movePointerRight(nextFirst, items.length);
            int last = movePointerLeft(nextLast, items.length);

            int pointer = first;
            System.out.print(items[pointer]);
            System.out.print(" ");

            while (pointer != last) {
                pointer = movePointerRight(pointer, items.length);
                System.out.print(items[pointer]);
                System.out.print(" ");
            }

            System.out.print("\n");
        }
    }

    /* Removes and returns the item at the front of the deque.
    If no such item exists, returns null */
    public T removeFirst() {
        resize();
        if (size == 0) {
            return null;
        }
        size -= 1;
        nextFirst = movePointerRight(nextFirst, items.length);


        return items[nextFirst];
    }

    /* Removes and returns the item at the end of the deque. If no such item exists, returns null */
    public T removeLast() {
        resize();
        if (size == 0) {
            return null;
        }
        size -= 1;
        nextLast = movePointerLeft(nextLast, items.length);


        return items[nextLast];
    }

    /*Given an index of the conceptual array. Find the corresponding index in the base array*/
    private int getConceptualDequeIndex(int index) {
        int first = movePointerRight(nextFirst, items.length);
        int conceptualDequeIndex = index + first;

        if (conceptualDequeIndex >= items.length) {
            conceptualDequeIndex -= items.length;
        }
        return conceptualDequeIndex;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int conceptualDequeIndex = getConceptualDequeIndex(index);
        return items[conceptualDequeIndex];
    }

    /*Creates iterator class to make the Deque iterable*/
    private class ArrayListIterator implements Iterator<T> {
        private int wizPos;

        /*Initializes iterator*/
        ArrayListIterator() {
            wizPos = movePointerRight(nextFirst, items.length);
        }

        /*Checks if collection has next item*/
        @Override
        public boolean hasNext() {
            return wizPos != nextLast;
        }

        /*Outputs the next item in the Deque*/
        public T next() {
            T returnItem = items[wizPos];
            wizPos = movePointerRight(wizPos, items.length);
            return returnItem;
        }
    }

    /*Public iterator*/
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    /*Checks if element exists in the ArrayDeque*/
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
