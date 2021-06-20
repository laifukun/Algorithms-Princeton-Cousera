/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class CyclicString {
    private static int R = 256;

    public static boolean KMP(String a, String b) {

        int M = b.length();
        int[][] dfa = new int[R][M];

        String aa = a + a;
        int N = aa.length();
        dfa[b.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X];
            dfa[b.charAt(j)][j] = j + 1;
            X = dfa[b.charAt(j)][X];
        }
        int i, j = 0;
        for (i = 0; i < N && j < M; i++)
            j = dfa[aa.charAt(i)][j];
        if (j == M) return true;
        else return false;
    }

    public static void main(String[] args) {
        String a = "winterbreak";
        String b = "breakwinter";

        StdOut.println(CyclicString.KMP(a, b));

    }
}
