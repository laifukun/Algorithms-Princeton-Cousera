/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class Palindromic {

    public static int BruteForce(String a) {
        int N = a.length();
        for (int d = N; d >= 2; d--)
            for (int i = 0; i < N - d + 1; i++)
                if (CheckPalindromic(a.substring(i, i + d))) {
                    StdOut.println(a.substring(i, i + d));
                    return d;
                }
        return 0;
    }

    private static boolean CheckPalindromic(String a) {
        int N = a.length();
        int i = 0;
        int j = N - 1;
        while (i < j) {
            if (a.charAt(i++) != a.charAt(j--)) return false;
        }
        return true;
    }

    public static int RabinKarp(String a) {
        for (int L = 2)
    }

    private static boolean CheckPalindromicRK(String a) {
        if (leftHash(a) == rightHash(a)) return true;
        return false;
    }

    private static long leftHash(String key) {
        int L = key.length() / 2;
        long h = 0;
        long Q = 10619863;
        long R = 256;

        for (int j = 0; j < L; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    private static long rightHash(String key) {
        int M = key.length();
        long h = 0;
        long Q = 10619863;
        long R = 256;

        for (int j = M - 1; j > M / 2; j--)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    public static void main(String[] args) {

        String a = "abcababcab";
        int d = Palindromic.BruteForce(a);
        StdOut.println(d);
    }
}
