public class ShellSort {
    public static void sort(Comparable[] a) {

        int h = 1;
        int N = a.length;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {

            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                    // show(a, i, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a, int iCur, int jTouch, int kTouch) {
        StdDraw.clear();
        int N = a.length;
        for (int i = 0; i < N; i++) {
            double x = 1.0 * i / N + 0.6 / N;
            double y = (double) a[i] / 2.0;
            double rw = 0.4 / N;
            double rh = (double) a[i] / 2.0;
            if (i > iCur) StdDraw.setPenColor(StdDraw.GRAY);
            if (i <= iCur) StdDraw.setPenColor(StdDraw.BLACK);
            if (i == jTouch || i == kTouch) StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledRectangle(x, y, rw, rh);
        }

    }

    public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }


    public static void main(
            String[] args) { // Read strings from standard input, sort them, and print.
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
            a[i] = StdRandom.random();
            max = max > a[i] ? max : a[i];
        }

        StdDraw.setCanvasSize(w, h);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0, 1 + 0.4 / N);
        StdDraw.setYscale(0, max);
        //show(a);

        sort(a);
    }
}
