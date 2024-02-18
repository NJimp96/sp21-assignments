package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        BuggyAList<Integer> buggyResult = new BuggyAList<>();
        AListNoResizing<Integer> correctResult = new AListNoResizing<>();

        for(int i = 0; i< 3; i++) {
            buggyResult.addLast(i+3);
            correctResult.addLast(i+3);
        }

        for(int i = 0; i< 3; i++) {
            assertEquals(correctResult.removeLast(),buggyResult.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> M = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                M.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeM = M.size();
                assertEquals(sizeL, sizeM);
                System.out.println("size: " + sizeL);
            } else if (operationNumber == 2 & L.size() > 0) {
                // getLast
                int lastIntL = L.getLast();
                int lastIntM = M.getLast();
                assertEquals(lastIntL, lastIntM);
                System.out.println("getLast(" + lastIntL + ")");
            } else if (operationNumber == 3 & L.size() > 0) {
                // removeLast
                int removedIntL = L.removeLast();
                int removedIntM = M.removeLast();
                assertEquals(removedIntL, removedIntM);
                System.out.println("removeLast(" + removedIntL + ")");
            }
        }
    }
}
