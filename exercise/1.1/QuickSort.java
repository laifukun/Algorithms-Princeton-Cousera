public class QuickSort {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) if (i >= hi) break;
            while (less(v, a[--j])) if (j <= lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void sortMedian(Comparable[] a, int order) {
        sortMedian(a, 0, a.length - 1, order);
    }

    public static void sortMedian(Comparable[] a, int lo, int hi, int order) {
        if (lo >= hi - order) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int j = partitionMedian(a, lo, hi, order);
        sortMedian(a, lo, j - 1, order);
        sortMedian(a, j + 1, hi, order);
    }

    public static int partitionMedian(Comparable[] a, int lo, int hi, int order) {
        int i = lo;
        int j = hi + 1;
        int m = StdRandom.uniform(lo + order / 2, hi - order / 2);
        Comparable v = a[m];
        exch(a, lo, m - order / 2);
        exch(a, hi, m + order / 2);

        while (true) {
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void median(Comparable[] a, int left, int right) {
        sort(a, left, right);
    }

    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);

        int w = 600;
        int h = 300;
        Double[] a = new Double[N];
        double max = Double.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
            max = max > a[i] ? max : a[i];
        }

        sortMedian(a, 3);

    }
}
