package deque;


import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> dequeComparator;

    public MaxArrayDeque(Comparator<T> c) {

        super();
        dequeComparator = c;
    }

    public T max() {
        T maxItem = this.get(0);

        for (T x: this) {
            if (dequeComparator.compare(x, maxItem) > 0) {
                maxItem = x;
            }
        }
        return maxItem;
    }

}
