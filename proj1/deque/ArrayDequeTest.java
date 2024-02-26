package deque;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {

    @Test
    public void addFirstTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addFirst("e");
        ad1.addFirst("d");
        ad1.addFirst("c");
        ad1.addFirst("b");
        ad1.addFirst("a");
        ad1.printDeque();
    }

    @Test
    public void addFirstTestResize() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        for(int i = 19; i >= 0; i--) {
            ad1.addFirst(i);
        }
    }

    @Test
    public void addLastTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("a");
        ad1.addLast("b");
        ad1.addLast("c");
        ad1.addLast("d");
        ad1.addLast("e");
        ad1.printDeque();
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("a");
        ad1.removeFirst();
        ad1.printDeque();
    }

    @Test
    public void removeFirstTestResize() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        for(int i = 1000; i >= 0; i--) {
            ad1.addFirst(i);
        }

        for(double i = 1000; i >= 0; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
//            if(ad1.removeLast() == null) {
//                System.out.println(i);
//            }
            //Object removedObject = ad1.removeLast();
        }
    }

    @Test
    public void removeLastTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("b");
        ad1.addFirst("a");
        ad1.removeLast();
        ad1.printDeque();
    }

    @Test
    public void getTestSimple() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("a");
        ad1.addLast("b");
        ad1.addLast("c");
        ad1.addLast("d");
        ad1.addLast("e");
        assertEquals("Item at position 4 should be 'e'", "e", ad1.get(4));
        assertEquals("Item at position 3 should be 'd'", "d", ad1.get(3));
        ad1.removeFirst();
        assertEquals("Item at position 0 should be 'b'", "b", ad1.get(0));
        ad1.removeLast();
        ad1.printDeque();
    }

    @Test
    public void getTestLargeArray() {
        ArrayDeque<Double> ad1 = new ArrayDeque<>();

        for(double i = 0; i <10000; i++) {
            ad1.addLast(i);
        }

        for(int i = 0; i <10000; i++) {
            assertEquals("Incorrect item", i, (double) ad1.get(i), 0.0);
        }
    }

    @Test
    public void addIsEmptySizeTest() {

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {


        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());
        lld1.printDeque();

        lld1.addLast(20);
        // should not be empty
        assertEquals("lld1 should contain 1 item", 1, lld1.size());
        lld1.printDeque();

        lld1.addFirst(10);
        // should not be empty
        assertEquals("lld1 should contain 2 items", 2, lld1.size());
        lld1.printDeque();

        lld1.addFirst(0);
        // should not be empty
        assertEquals("lld1 should contain 3 item", 3, lld1.size());
        lld1.printDeque();

        lld1.removeFirst();
        // should not be empty
        assertEquals("lld1 should contain 2 items", 2, lld1.size());
        lld1.printDeque();

        lld1.removeLast();
        // should not be empty
        assertEquals("lld1 should contain 1 items", 1, lld1.size());
        lld1.printDeque();

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removing all items", lld1.isEmpty());
        lld1.printDeque();

    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addLast(20);
        // should not be empty
        assertEquals("lld1 should contain 1 item", 1, lld1.size());
        lld1.printDeque();

        lld1.addFirst(10);
        // should not be empty
        assertEquals("lld1 should contain 2 items", 2, lld1.size());
        lld1.printDeque();

        lld1.addFirst(0);
        // should not be empty
        assertEquals("lld1 should contain 3 item", 3, lld1.size());
        lld1.printDeque();

        for (int i = 0; i < lld1.size() + 1; i++) {
            System.out.println(lld1.get(i));
        }
    }


    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {



        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {


        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {



        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            //Object removedInt = lld1.removeLast();
//            if(removedInt == null) {
//                System.out.println(i);
//            }
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }


    }
}
