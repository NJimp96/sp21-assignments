package deque;

import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest {

    public class IntComparator implements Comparator<Integer> {

        public IntComparator() { };
        public int compare(Integer i1, Integer i2) {

            return i1.compareTo(i2);
        }


    }

    @Test
    public void compareTest() {

        Comparator<Integer> ic = new IntComparator();
        MaxArrayDeque<Integer> ma1 = new MaxArrayDeque(ic);

        ma1.addFirst(0);
        ma1.addFirst(1);
        ma1.addFirst(10);
        ma1.addFirst(10);
        ma1.addFirst(-100);

        ma1.max();
    }


}
