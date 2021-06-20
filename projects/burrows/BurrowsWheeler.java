/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int R = 256;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        // char[] input = s.toCharArray();

        int n = csa.length();
        for (int i = 0; i < n; i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            int j = csa.index(i);
            if (j == 0)
                BinaryStdOut.write(s.charAt(n - 1));
            else
                BinaryStdOut.write(s.charAt(j - 1));

        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {

        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();
        char[] e = t.toCharArray();

        int n = t.length();
        char[] s = new char[n];
        int[] count = new int[R + 1];
        int[] next = new int[n];

        for (int i = 0; i < n; i++)
            count[e[i] + 1]++;
        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];

        for (int i = 0; i < n; i++) {
            s[count[e[i]]] = e[i];
            next[count[e[i]]++] = i;
        }

        for (int i = first, k = 0; k < n; i = next[i], k++)
            BinaryStdOut.write(s[i]);

        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else throw new IllegalArgumentException("Illegal command line argument");

    }
}
