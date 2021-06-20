/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] charPos = new char[R];
        char[] charSeq = new char[R];
        for (char c = 0; c < R; c++) {
            charPos[c] = c;
            charSeq[c] = c;
        }
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            char code = charPos[c];
            if (code != 0) {
                charPos[c] = 0;
                for (char i = code; i > 0; i--) {
                    charSeq[i] = charSeq[i - 1];
                    charPos[charSeq[i]]++;
                }
                charSeq[0] = c;
            }
            BinaryStdOut.write(code);
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        // char[] charPos = new char[R];
        char[] charSeq = new char[R];
        for (char c = 0; c < R; c++) {
            //   charPos[c] = c;
            charSeq[c] = c;
        }
        while (!BinaryStdIn.isEmpty()) {
            char code = BinaryStdIn.readChar();
            char c = charSeq[code];
            if (code != 0) {
                //  charPos[c] = 0;
                for (char i = code; i > 0; i--) {
                    charSeq[i] = charSeq[i - 1];
                    //   charPos[charSeq[i]]++;
                }
                charSeq[0] = c;
            }
            // char c = positionOf(seq, code);
            //        moveFront(seq, c);
            BinaryStdOut.write(c);
        }

        BinaryStdOut.close();

    }

/*
    private static void moveFront(char[] seq, char i) {

        if (seq[i] == 0) return;
        char c = seq[i];
        seq[i] = 0;
        for (char j = 0; j < R; j++)
            if (seq[j] < c && j != i) seq[j]++;

    }

 */

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");

    }
}
