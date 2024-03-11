package deque;
import edu.princeton.cs.algs4.Stopwatch;

public class LLDequeTimingTest {

    private static void printTimingTable(LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static LinkedListDeque<Integer> createSLList(int desiredSize) {
        LinkedListDeque<Integer> listToTime = new LinkedListDeque<>();

        for(int i = 0; i < desiredSize; i++) {
            listToTime.addLast(i);
        }
        return listToTime;
    }

    public static void timeGetLast() {
        LinkedListDeque<Integer> listSizes = new LinkedListDeque<>();
        LinkedListDeque<Integer> listOps = new LinkedListDeque<>();
        LinkedListDeque<Double> listTimes = new LinkedListDeque<>();
        int opsCompleted = 10000;

        for(double n = 0; n < 8; n++) {
            int listSize = (int) (Math.pow(2, n) * 1000);
            LinkedListDeque<Integer> listInUse = createSLList(listSize);
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < opsCompleted; i++) {
                listInUse.removeLast();
            }
            double timeInSeconds = sw.elapsedTime();

            listSizes.addLast(listSize);
            listOps.addLast(opsCompleted);
            listTimes.addLast(timeInSeconds);
        }

        printTimingTable(listSizes, listTimes, listOps);

    }

}

