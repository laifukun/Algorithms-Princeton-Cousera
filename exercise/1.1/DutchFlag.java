// import edu.princeton.cs.algs4.StdOut;
// import edu.princeton.cs.algs4.StdRandom;

public class DutchFlag {

    public static void sort(int[] a) {
        int n = a.length;
        // int pointerOne = -1;
        int v = 1;
        int lt = 0, gt = n - 1;
        // while (++i < n && color(a, i) == colorOne) ;
        // if (i >= n - 1) return;
        // swap(a, i, --j);
        // pointerOne = i - 1;
        // int colorThree = color(a, n - 1);
        // while (color(a, j) == colorThree && --j > i) ;
        // if (j <= i) return;
        int i = lt;
        while (i <= gt) {
            // int colorI = color(a, i);
            //while (++i <= gt && color(a, i) < v) ;
            if (color(a, i) < v) swap(a, lt++, i++);
            if (color(a, i) > v) swap(a, gt--, i);
            else i++;
            // if (colorI == colorThree) swap(a, i, j--);
            // else if (colorI == colorOne) swap(a, ++pointerOne, i++);
            // else i++;
        }
    }

    private static int color(int[] a, int i) {
        return a[i];
    }

    private static void swap(int[] a, int i, int j) {
        int sw = a[i];
        a[i] = a[j];
        a[j] = sw;
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int[] a = new int[n];
        //int[] a = { 2, 0, 0, 0, 2, 1, 2, 0, 0, 0 };
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(0, 3);

        for (int i = 0; i < n; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();

        sort(a);
        for (
                int i = 0;
                i < n; i++)
            StdOut.print(a[i] + " ");
    }
}
