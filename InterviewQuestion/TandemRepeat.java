/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class TandemRepeat {
    private static int R = 256;

    public static int repeat(String b, String s) {
        int M = b.length();
        int N = s.length();
        int[][] dfa = new int[R][M + 1];
        dfa[b.charAt(0)][0] = 1;
        int X, j;
        for (X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X];
            dfa[b.charAt(j)][j] = j + 1;
            X = dfa[b.charAt(j)][X];
        }
        for (int c = 0; c < R; c++)
            dfa[c][M] = dfa[c][X];
        dfa[b.charAt(M - 1)][M] = 0;

        int i, last = -2;
        int length = 0;
        int maxLength = 0;
        for (i = 0, j = 0; i < N; i++) {
            j = dfa[s.charAt(i)][j];
            if (j == M && last == i - M) {
                j = 0;
                last = i;
                length++;
            }
            else if (j == M) {
                if (maxLength < length) maxLength = length;
                length = 1;
                last = i;
                //      j = 0;
            }
        }
        if (maxLength < length) maxLength = length;
        return maxLength;

    }

    public static void main(String[] args) {

        String s = "abcabdcababcababcababcabdabcab";
        String b = "abcab";

        StdOut.println(TandemRepeat.repeat(b, s));

    }
}
