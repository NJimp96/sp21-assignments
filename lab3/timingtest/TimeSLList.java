package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(SLList<Integer> Ns, SLList<Double> times, SLList<Integer> opCounts) {
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

    public static SLList<Integer> createSLList(int desiredSize) {
        SLList<Integer> listToTime = new SLList<>();

        for(int i = 0; i < desiredSize; i++) {
            listToTime.addLast(i);
        }
        return listToTime;
    }

    public static void timeGetLast() {
        SLList<Integer> listSizes = new SLList<>();
        SLList<Integer> listOps = new SLList<>();
        SLList<Double> listTimes = new SLList<>();
        int opsCompleted = 10000;

        for(double n = 0; n < 8; n++) {
            int listSize = (int) (Math.pow(2, n) * 1000);
            SLList<Integer> listInUse = createSLList(listSize);
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < opsCompleted; i++) {
                listInUse.getLast();
            }
            double timeInSeconds = sw.elapsedTime();

            listSizes.addLast(listSize);
            listOps.addLast(opsCompleted);
            listTimes.addLast(timeInSeconds);
        }

        printTimingTable(listSizes, listTimes, listOps);

    }

}
