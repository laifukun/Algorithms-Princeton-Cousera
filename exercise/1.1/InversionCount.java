public class InversionCount {
    protected static Comparable[] aux;

    public static int countInversions(Comparable[] a) {
        aux = new Comparable[a.length];
        for (int i = 0; i < a.length; i++)
            aux[i] = a[i];
        return countInversions(a, 0, a.length - 1);
    }

    public static int countInversions(Comparable[] a, int lo, int hi) {
        int count = 0;
        if (hi <= lo) return count;
        int mid = lo + (hi - lo) / 2;
        count += countInversions(a, lo, mid);
        count += countInversions(a, mid + 1, hi);
        count += mergeCount(a, lo, mid, hi);

        return count;
    }

    public static int mergeCount(Comparable[] a, int lo, int mid, int hi) {
        int count = 0;
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) aux[k] = a[k];
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                count += mid - i + 1;
            }
            else a[k] = aux[i++];
        }
        return count;
    }

    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        //int N = Integer.parseInt(args[0]);
        int N = 10;
        //Double[] a = new Double[N];
        int[] a = StdIn.readAllInts();
        Integer[] b = new Integer[a.length];
        for (int i = 0; i < a.length; i++)
            b[i] = a[i];
        StdOut.println(countInversions(b));


    }
}
