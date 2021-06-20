/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;

public class BoardString {
    private final HashSet<String> allWords;
    // private final ArrayList<ArrayList<Integer>> adj;
    private final int[][] adj;
    private final char[] bd;
    private final int m, n;
    private boolean[] marked;
    private final StringBuilder word;

    BoardString(BoggleBoard board) {
        m = board.rows();
        n = board.cols();
        int nL = m * n;

        allWords = new HashSet<String>();
        marked = new boolean[nL];
        bd = new char[nL];
        // adj = new ArrayList<ArrayList<Integer>>(nL);
        adj = new int[nL][8];

        for (int i = 0; i < nL; i++)
            for (int j = 0; j < 8; j++)
                adj[i][j] = -1;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                int len = index2DTo1D(i, j);
                bd[len] = board.getLetter(i, j);
                // adj.add(new ArrayList<Integer>());

                addAdj(len, i + 1, j, 0);
                addAdj(len, i - 1, j, 1);
                addAdj(len, i + 1, j - 1, 2);
                addAdj(len, i, j - 1, 3);
                addAdj(len, i - 1, j - 1, 4);
                addAdj(len, i + 1, j + 1, 5);
                addAdj(len, i, j + 1, 6);
                addAdj(len, i - 1, j + 1, 7);
                //      }
            }
        word = new StringBuilder();
    }

    private int index2DTo1D(int i, int j) {
        //  if (i < m && i >= 0 && j < n && j >= 0)
        return i * n + j;
        //   else return -1;
    }

    private void addAdj(int len, int i, int j, int p) {
        if (i > m - 1 || i < 0 || j > n - 1 || j < 0)
            return;
        int q = index2DTo1D(i, j);
        adj[len][p] = q;
        // adj.get(len).add(q);
        // adj[len].add(q);

    }

    private void dfs(int len, TrieDict.Node node, TrieDict dict) {

        marked[len] = true;
        char c = bd[len];
        if (c == 'Q') {
            node = dict.prefixCheck(node, 'Q');
            if (node != null)
                node = dict.prefixCheck(node, 'U');
        }
        else {
            node = dict.prefixCheck(node, c);
        }

        if (node == null) {
            marked[len] = false;
            return;
        }

        if (c == 'Q') word.append("QU");
        else word.append(c);
        int nL = word.length();
        if (nL >= 3 && node.val) allWords.add(word.toString());

        // String test = word.toString();
        /*
        if (nL >= 2 && !dict.isPrefix(test))
        {
            marked[len] = false;
            if (bd[len] == 'Q')
                word.delete(nL - 2, nL);
            else
                word.deleteCharAt(nL - 1);
            return;
        }
        else if (nL >= 3 && dict.get(test))
            allWords.add(test);

         */
        for (int p : adj[len])
            if (p != -1 && !marked[p]) dfs(p, node, dict);

        marked[len] = false;
        if (bd[len] == 'Q')
            word.delete(nL - 2, nL);
        else
            word.deleteCharAt(nL - 1);
    }

    public Iterable<String> getAllString(TrieDict dict) {


        for (int i = 0; i < m * n; i++) {
            // String words = "";
            TrieDict.Node root = dict.root();
            dfs(i, root, dict);
        }

        return allWords;
    }


    public static void main(String[] args) {


        String word = "abcdefg";
        word = "" + word.charAt(word.length() - 1);
        StdOut.println(word);

    }
}
