public class Selection2Arrays {

    public static int rank(int[] a, int[] b, int k) {

        // int n1 = a.length;
        int i = 0;
        int j = b.length - 1;

        while (i < j) {
            int mid = i + (j - i) / 2;
            int p = rank(a, b[mid]);
            if (p + mid > k) j = mid;
            if (p + mid < k) i = mid;
            if (p + mid == k) return b[mid];
        }

        return -1;

    }

    public static int rank(int[] a, int key) {
        if (key >= a[a.length - 1]) return a.length;
        if (key <= a[0]) return 0;
        return rank(a, key, 0, a.length - 1);
    }

    public static int rank(int[] a, int key, int lo, int hi) {
        if (hi < lo) return -1;
        int i = lo;
        int j = hi;
        int mid = lo + (hi - lo) / 2;
        while (i <= j) {
            mid = i + (j - i) / 2;
            if (key >= a[mid]) i = mid + 1;
            if (key < a[mid]) j = mid - 1;
        }
        return mid + 1;
    }

    public static void main(String[] args) {

        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        int[] a = new int[n1];
        int[] b = new int[n2];

        for (int i = 0; i < n1; i++) {
            a[i] = 2 * i;

        }
        for (int i = 0; i < n2; i++) {
            b[i] = 2 * i + 1;

        }

        for (int i : a)
            StdOut.print(i + " ");
        StdOut.println();
        for (int i : b)
            StdOut.print(i + " ");
        StdOut.println();

        StdOut.println(rank(a, b, k));


    }
}
