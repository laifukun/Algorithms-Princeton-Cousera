import java.util.Arrays;

public class ArrayOperation {

    public static double closestPair(double[] a) {
        // exersice 1.4.16 find the closed pair in an array
        Arrays.sort(a);
        double min = Double.MAX_VALUE;
        for (int i = 1; i < a.length; i++) {
            double temp = a[i] - a[i - 1];
            temp = temp > 0 ? temp : -temp;
            if (temp < min) min = temp;
        }
        return min;
    }

    public static double farthestPair(double[] a) {
        // exersice 1.4.17 find the farthest pair in an array
        Arrays.sort(a);
        double max = Double.MIN_VALUE;
        for (int i = 1; i < a.length; i++) {
            double temp = a[i] - a[i - 1];
            temp = temp > 0 ? temp : -temp;
            if (temp > max) max = temp;
        }
        return max;
    }

    public static int localMinimum(int[] a) {
        // a local minimum of an array exersice 1.4.18

        int i = 0;
        int j = a.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2 + 1;

            if (mid != a.length - 1 && a[mid] > a[mid + 1]) i = mid + 1;
            else if (mid != 0 && a[mid] > a[mid - 1]) j = mid - 1;
            else return mid;
        }
        return i;

    }

    public static int[] localMinimum(int[][] a) {
        // a local minimum of an array exersice 1.4.19
        int i = 0;
        int j = a.length - 1;
        int k = 0;
        int l = a[0].length - 1;
        int rMid = i + (j - i) / 2 + 1;
        int cMid = l;
        int access = 0;
        int[] b = { rMid, cMid };

        while (i < j && k < l) {
            StdOut.println(access++ + ": " + rMid + " " + cMid);

            if (rMid != a.length - 1 && a[rMid][cMid] > a[rMid + 1][cMid]) {
                i = rMid + 1;
                rMid = i + (j - i) / 2 + 1;
            }

            else if (rMid != 0 && a[rMid][cMid] > a[rMid - 1][cMid]) {
                j = rMid - 1;
                rMid = i + (j - i) / 2 + 1;
            }

            else if (cMid != a[0].length - 1 && a[rMid][cMid] > a[rMid][cMid + 1]) {
                k = cMid + 1;
                cMid = k + (l - k) / 2 + 1;
            }

            else if (cMid != 0 && a[rMid][cMid] > a[rMid][cMid - 1]) {
                l = cMid - 1;
                cMid = k + (l - k) / 2 + 1;
            }
            else {
                b[0] = rMid;
                b[1] = cMid;
                return b;
            }
        }
        b[0] = i;
        b[1] = k;
        return b;

    }

    public static int BitonicSearch(int key, int[] a) {
        // exersice 1.4.20

        // find maximum value first
        int max = -1;
        int i = 0;
        int j = a.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2 + 1;

            if (mid != a.length - 1 && a[mid] < a[mid + 1]) i = mid + 1;
            else if (mid != 0 && a[mid] < a[mid - 1]) j = mid - 1;
            else {
                max = mid;
                break;
            }
        }
        if (max == -1) throw new RuntimeException("Not Bitonic Array!");
        StdOut.println(max);
        i = 0;
        j = max;

        // search the incresing half
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (mid != max && a[mid] < key) i = mid + 1;
            else if (mid != 0 && a[mid] > key) j = mid - 1;
            else if (key == a[mid]) return mid;
        }

        i = max + 1;
        j = a.length - 1;

        // search the descreasing half

        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (mid != a.length - 1 && a[mid] > key) i = mid + 1;
            else if (mid != max + 1 && a[mid] < key) j = mid - 1;
            else if (key == a[mid]) return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int N = 10;
        int[][] a = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = i * j;//StdRandom.uniform(N * N);
                StdOut.print(a[i][j] + " ");
            }
            StdOut.println();
        }
        int[] b = new int[2];
        b = localMinimum(a);
        StdOut.println(b[0] + " " + b[1]);

        int[] c = { 10, 20, 30, 40, 50, 48, 46 };
        StdOut.println(BitonicSearch(50, c));

    }
}
