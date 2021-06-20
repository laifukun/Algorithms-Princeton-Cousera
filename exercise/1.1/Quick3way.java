public class Quick3way extends QuickSort {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        int i = lo + 1, lt = lo, j = hi;
        Comparable v = a[lo];

        while (i <= j) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, i++, lt++);
            else if (cmp > 0) exch(a, i, j--);
            else i++;
        }

        sort(a, lo, lt - 1);
        sort(a, lt + 1, hi);
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

        sort(a);

        for (int i = 0; i < N; i++)
            StdOut.print(a[i] + " ");
    }
}
