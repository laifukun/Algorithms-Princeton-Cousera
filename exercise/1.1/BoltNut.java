public class BoltNut {

    public static void sort(int[] bolt, int[] nut) {
        int n = bolt.length;
        int lo = 0;
        int hi = n - 1;
        int key = StdRandom.uniform(lo, n);
        sort(bolt, nut, key, lo, hi);

    }

    private static int fit(int bolt, int nut) {
        // fit one bolt to one nut
        if (bolt > nut) return 1;
        if (bolt == nut) return 0;
        return -1;
    }

    private static void sort(int[] bolt, int[] nut, int key, int lo, int hi) {
        if (lo >= hi) return;
        // fit a nut to all bolt and partition the bolts to two piles
        int mid = partition(bolt, key, lo, hi);
        // using the fitted bolt to test all nut and partition the nuts to two piles
        int boltkey = bolt[mid];
        partition(nut, boltkey, lo, hi);


        // Randomly pick a nut on each pile and to test fit of the corresponding pile of bolt
        if (mid - 1 > lo) {
            int nutkey1 = StdRandom.uniform(lo, mid);
            sort(bolt, nut, nutkey1, lo, mid - 1);
        }
        if (mid + 1 < hi) {
            int nutkey2 = StdRandom.uniform(mid + 1, hi);
            sort(bolt, nut, nutkey2, mid + 1, hi);
        }
    }

    private static int partition(int[] a, int key, int lo, int hi) {
        int i = lo;
        int j = hi;
        while (i < j) {
            while (fit(a[i], key) < 0 && i < hi) i++;
            while (fit(a[j], key) > 0 && j > lo) j--;
            if (i >= j) break;
            exch(a, i, j);
        }
        return j;
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int[] bolt = new int[n];
        int[] nut = new int[n];

        for (int i = 0; i < n; i++) {
            bolt[i] = i;
            nut[i] = i;
        }

        StdRandom.shuffle(bolt);
        StdRandom.shuffle(nut);


        for (int i : bolt)
            StdOut.print(i + " ");
        StdOut.println();
        for (int i : nut)
            StdOut.print(i + " ");

        BoltNut.sort(bolt, nut);
        StdOut.println();

        for (int i : bolt)
            StdOut.print(i + " ");
        StdOut.println();
        for (int i : nut)
            StdOut.print(i + " ");

    }
}
