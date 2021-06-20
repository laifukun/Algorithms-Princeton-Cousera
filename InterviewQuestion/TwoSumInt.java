/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class TwoSumInt {
    private void sort(long a[]) {
        int R = 2;
        int N = a.length;
        long[] aux = new long[N];
        int W = 64;
        long bit = 0x0000000000000001;
        for (int d = 0; d < W; d++) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                long t = (a[i] >> d);
                count[(int) (t & bit) + 1]++;
            }
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];
            for (int i = 0; i < N; i++) {
                long t = (a[i] >> d);
                aux[count[(int) (t & bit)]++] = a[i];
            }
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }

    }

    public TwoSumInt(long a[], long T) {
        sort(a);
        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            if (a[left] + a[right] == T) {
                if (a[left] != a[right])
                    StdOut.println(a[left] + " + " + a[right] + " = " + T);
                left++;
            }
            if (a[left] + a[right] < T) left++;
            if (a[left] + a[right] > T) right--;
        }
    }

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);

        long[] a = new long[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform((long) N);
        for (int i = 0; i < N; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
        TwoSumInt twoSum = new TwoSumInt(a, N);
        for (int i = 0; i < N; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
        long bit = 0x0000000000000001;
        long t = 1;
        long x = t & bit;
        int intx = (int) (t & bit);
        int intx1 = (int) (x + 1);
        long intx2 = (t & bit) + 1;
        StdOut.println("Long And " + x);
        StdOut.println("Long and convert to int: " + intx);
        StdOut.println("Long and convert to int + 1: " + intx1);
        StdOut.println("Long and convert to int + 1: " + intx2);

    }
}
