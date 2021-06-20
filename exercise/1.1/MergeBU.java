public class MergeBU extends MergeSort {
    //private static Comparable[] aux;

    public static void sort(Comparable[] a) {

        aux = new Comparable[a.length];
        int N = a.length;
        for (int sz = 1; sz < N; sz = sz + sz)
            for (int lo = 0; lo < N - sz; lo += sz + sz)
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
        //show(a, hi, mid, lo);
    }

    public static void main(String[] args) {
        // Read strings from standard input, sort them, and print.
        // String[] a = In.readStrings();
        // sort(a);
        // assert isSorted(a);
        // show(a);

        int N = Integer.parseInt(args[0]);

        int w = 600;
        int h = 300;
        Double[] a = new Double[N];
        double max = Double.MIN_VALUE;
        for (
                int i = 0;
                i < N; i++) {
            a[i] = StdRandom.uniform();
            max = max > a[i] ? max : a[i];
        }

        StdDraw.setCanvasSize(w, h);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0, 1 + 0.4 / N);
        StdDraw.setYscale(0, max);
        //show(a);

        sort(a);

        toString(a);

    }
}
