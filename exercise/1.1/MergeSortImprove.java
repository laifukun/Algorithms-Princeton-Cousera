public class MergeSortImprove extends MergeSort {

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        for (int i = 0; i < aux.length; i++)
            aux[i] = a[i];
        sort(aux, a, 0, a.length - 1);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi - lo + 1 < 16) {
            Insertion.sort(dst, lo, hi);
            return;
        }
        //if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);
        if (!less(src[mid + 1], src[mid])) {
            for (int i = lo; i <= hi; i++)
                dst[i] = src[i];
            return;
        }
        merge(src, dst, lo, mid, hi);
        //show(a, hi, mid, lo);
    }

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        //for (int k = lo; k <= hi; k++) aux[k] = a[k];
        for (int k = lo; k <= hi; k++) {
            if (i > mid) dst[k] = src[j++];
            else if (j > hi) dst[k] = src[i++];
            else if (less(src[i], src[j])) dst[k] = src[i++];
            else dst[k] = src[j++];
        }

    }

    public static void main(String[] args) {
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
        //toString(a);
        sort(a);

        toString(a);
    }
}
