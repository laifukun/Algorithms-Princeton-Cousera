public class MergeSort {
    protected static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        if (less(a[mid + 1], a[mid])) mergeHalfSpace(a, lo, mid, hi);
        // show(a, hi, mid, lo);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) aux[k] = a[k];
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }

    }

    public static void mergeHalfSpace(Comparable[] a, int lo, int mid, int hi) {
        Comparable[] aux2 = new Comparable[mid - lo + 1];
        int i = 0;
        int n = aux2.length;
        int j = mid + 1;
        for (int k = lo; k <= mid; k++) aux2[k - lo] = a[k];
        for (int k = lo; k <= hi; k++) {
            if (i > n - 1) a[k] = a[j++];
            else if (j > hi) a[k] = aux2[i++];
            else if (less(aux2[i], a[j])) a[k] = aux2[i++];
            else a[k] = a[j++];
        }

    }

    public static void sort3way(Comparable[] a) {
        aux = new Comparable[a.length];
        sort3way(a, 0, a.length - 1);
    }

    private static void sort3way(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid1 = lo + (hi - lo) / 3;
        int mid2 = mid1 + (hi - lo) / 3;
        sort3way(a, lo, mid1);
        sort3way(a, mid1 + 1, mid2);
        sort3way(a, mid2 + 1, hi);
        merge3way(a, lo, mid1, mid2, hi);
    }

    public static void merge3way(Comparable[] a, int lo, int mid1, int mid2, int hi) {
        int i = lo;
        int j = mid1 + 1;
        int k = mid2 + 1;
        for (int m = lo; m <= hi; m++) aux[m] = a[m];
        for (int m = lo; m <= hi; m++) {
            if (i <= mid1 && j <= mid2 && k <= hi) {
                if (less(a[i], a[j])) {
                    if (less(a[i], a[k])) a[m] = aux[i++];
                    else a[m] = aux[k++];
                }
                else {
                    if (less(a[j], a[k])) a[m] = aux[j++];
                    else a[m] = aux[k++];
                }
            }
            else if (j <= mid2 && k <= hi) {
                if (less(a[j], a[k])) a[m] = aux[j++];
                else a[m] = aux[k++];
            }
            else if (i <= mid1 && k <= hi) {
                if (less(a[i], a[k])) a[m] = aux[i++];
                else a[m] = aux[k++];
            }
            else if (i <= mid1 && j <= mid2) {
                if (less(a[i], a[j])) a[m] = aux[i++];
                else a[m] = aux[j++];
            }
            else if (i <= mid1) a[m] = aux[i++];
            else if (j <= mid2) a[m] = aux[j++];
            else a[m] = aux[k++];

        }
    }


    protected static boolean less(Comparable v, Comparable w) {
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

    protected static void toString(Comparable[] a) { // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
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
        Integer[] a = new Integer[N];
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(2 * N);
            max = max > a[i] ? max : a[i];
        }

        //StdDraw.setCanvasSize(w, h);
        //StdDraw.setPenColor(StdDraw.GRAY);
        //StdDraw.setPenRadius(0.01);
        //StdDraw.setXscale(0, 1 + 0.4 / N);
        //StdDraw.setYscale(0, max);
        //show(a);

        //sort3way(a);
        sort(a);

        toString(a);


    }
}
