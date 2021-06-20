public class QuickFast3Way extends QuickSort {
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        int i = lo + 1, p = lo, j = hi, q = hi + 1;
        Comparable v = a[lo];

        while (i <= j) {

            int cmp = a[i].compareTo(v);
            if (cmp == 0) exch(a, i++, ++p);
            else if (cmp > 0) exch(a, i, j--);
            else i++;
            if (i > j) break;
            cmp = a[j].compareTo(v);
            if (cmp == 0) exch(a, j--, --q);
            else if (cmp < 0) exch(a, i++, j);
            else j--;
        }
        int k = lo;
        while (k <= p) exch(a, j--, k++);
        k = hi;
        while (k > q) exch(a, i++, k--);

        sort(a, lo, j);
        sort(a, i, hi);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        int w = 600;
        int h = 300;
        // Integer[] a = new Integer[N];
        // double max = Double.MIN_VALUE;
        //for (int i = 0; i < N; i++) {
        //     a[i] = StdRandom.uniform(0, N);
        //    max = max > a[i] ? max : a[i];
        // }

        //int[] a = StdIn.readAllInts();
        Integer[] b = new Integer[N];
        for (int i = 0; i < N; i++)
            b[i] = StdRandom.uniform(0, N);

        for (int i = 0; i < N; i++)
            StdOut.print(b[i] + " ");
        StdOut.println();

        sort(b);

        for (int i = 0; i < N; i++)
            StdOut.print(b[i] + " ");
    }


}
