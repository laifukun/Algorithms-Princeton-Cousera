public class BinarySearch {
    public static int rank(Comparable key, Comparable[] a) {
        //int layer = 0;
        return rank(key, a, 0, a.length - 1);
    }

    public static int rank(Comparable key, Comparable[] a, int lo, int hi, int layer) {
        // Binary search
        layer++;
        StdOut.print("Layer: " + layer + "; hi: " + hi + "; lo: " + lo);
        StdOut.println();
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key.compareTo(a[mid]) < 0) return rank(key, a, lo, mid - 1, layer);
        else if (key.compareTo(a[mid]) > 0) return rank(key, a, mid + 1, hi, layer);
        else return mid;
    }

    public static int rank(Comparable key, Comparable[] a, int lo, int hi) {
        // Binary search

        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key.compareTo(a[mid]) < 0) return rank(key, a, lo, mid - 1);
        else if (key.compareTo(a[mid]) > 0) return rank(key, a, mid + 1, hi);
        else return mid;
    }

    public static int rankFibonacci(int key, int[] a) {
        int i = 0;
        int j = a.length - 1;
        int maxFibo1 = 0;
        int maxFibo2 = 1;

        while (maxFibo2 < a.length - 1) {
            int temp = maxFibo2 + maxFibo1;
            maxFibo2 = temp;
            maxFibo1 = maxFibo2;
        }
        maxFibo2 = maxFibo2 - maxFibo1;

        while (i <= j) {
            int mid = maxFibo1;
            if (key == a[mid]) return mid;
            else if (mid != a.length - 1 && key > a[mid]) i = mid + 1;
            else if (mid == 0 && key < a[mid]) j = mid - 1;

        }
        return -1;
    }

    public static int rankcount(int key, int[] a) {

        // exercise 1.1.29
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return lo;


    }

    public static void main(String[] args) {
        int[] c = { 1, 2, 3, 4, 6, 7, 8, 9, 10 };
        int key = 6;
        StdOut.println("Numbers less than " + key + ": " + rankcount(key, c));
        // rank(2, c);
        // int[] whitelist = StdIn.readAllInts();
        //Arrays.sort(whitelist);
        // while (!StdIn.isEmpty()) {
        //   int key = StdIn.readInt();
        //   if (rank(key, whitelist) == -1)
        //       StdOut.println(key);
        //}
    }
}
