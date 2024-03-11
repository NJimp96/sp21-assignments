package deque;
import edu.princeton.cs.algs4.Stopwatch;

public class ArrayDequeTimingTest {

    private static void printTimingTable(ArrayDeque<Integer> Ns, ArrayDeque<Double> times, ArrayDeque<Integer> opCounts) {
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
        timeAListConstruction();
    }

    public static int createAList(int desiredSize) {
        ArrayDeque<Integer> listToTime = new ArrayDeque<>();
        int ops = 0;

        for(int i = 0; i < desiredSize; i++) {
            listToTime.addLast(i);
            ops++;
        }
        return ops;
    }
    public static void timeAListConstruction() {
        ArrayDeque<Integer> listSizes = new ArrayDeque<>();
        ArrayDeque<Integer> listOps = new ArrayDeque<>();
        ArrayDeque<Double> listTimes = new ArrayDeque<>();


        for(double n = 0; n < 8; n++) {
            int listSize = (int) (Math.pow(2, n) * 1000);
            Stopwatch sw = new Stopwatch();
            int opsCompleted = createAList(listSize);
            double timeInSeconds = sw.elapsedTime();

            listSizes.addLast(listSize);
            listOps.addLast(opsCompleted);
            listTimes.addLast(timeInSeconds);
        }

        printTimingTable(listSizes, listTimes, listOps);

    }
}

