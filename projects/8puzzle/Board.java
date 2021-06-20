/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Board {

    private final int[][] tiles;
    private final int n;
    private final int hamming;
    private final int manhattan;
    // private final int iBlank;
    // private final int jBlank;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        this.n = tiles.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        // find the hamming
        int outOfPlace = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != (i) * n + j + 1 && tiles[i][j] != 0)
                    ++outOfPlace;
            }
        // if (tiles[n - 1][n - 1] == 0) outOfPlace--;
        hamming = outOfPlace;

        outOfPlace = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != (i) * n + j + 1 && tiles[i][j] != 0)
                    outOfPlace += distance(tiles[i][j], i, j);
            }
        manhattan = outOfPlace;

    }
    /*
    private int tileAt(int row, int col) {
        return tiles[row][col];
    }

     */

    // string representation of this board
    public String toString() {
        StringBuilder str = new StringBuilder(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                str.append(tiles[i][j] + "\t");
            str.append("\n");
        }
        return str.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (getClass() != y.getClass()) return false;
        if (n != ((Board) y).dimension()) return false;
        if (((Board) y).tiles == null) throw new IllegalArgumentException("");

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (((Board) y).tiles[i][j] != this.tiles[i][j]) return false;
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbor = new ArrayList<Board>();
        int iBlank = 0, jBlank = 0;
        Search0:
        for (iBlank = 0; iBlank < n; iBlank++)
            for (jBlank = 0; jBlank < n; jBlank++)
                if (tiles[iBlank][jBlank] == 0) break Search0;

        if (jBlank == 0) neighbor.add(new Board(moveLeft(iBlank, jBlank + 1)));
        else if (jBlank == n - 1) neighbor.add(new Board(moveRight(iBlank, jBlank - 1)));
        else {
            neighbor.add(new Board(moveRight(iBlank, jBlank - 1)));
            neighbor.add(new Board(moveLeft(iBlank, jBlank + 1)));
        }

        if (iBlank == 0) neighbor.add(new Board(moveUp(iBlank + 1, jBlank)));
        else if (iBlank == n - 1) neighbor.add(new Board(moveDown(iBlank - 1, jBlank)));
        else {
            neighbor.add(new Board(moveUp(iBlank + 1, jBlank)));
            neighbor.add(new Board(moveDown(iBlank - 1, jBlank)));
        }
        return neighbor;
    }

    // a board that is obtained by exchanging  any pair of tiles
    public Board twin() {
        int iBlank = 0, jBlank = 0;
        Search0:
        for (iBlank = 0; iBlank < n; iBlank++)
            for (jBlank = 0; jBlank < n; jBlank++)
                if (tiles[iBlank][jBlank] == 0) break Search0;

        if (iBlank == 0) iBlank++;
        else iBlank--;
        // if (iBlank == n - 1) iBlank--;
        // if (iBlank == jBlank && jBlank < n - 1) jBlank++;
        // else if (iBlank == jBlank && jBlank == n - 1) jBlank--;
        //if (jBlank == n - 1) jBlank--;
        //int i = StdRandom.uniform(0, n);
        //int j = StdRandom.uniform(0, n);
        // int p = StdRandom.uniform(0, n);
        // int q = StdRandom.uniform(0, n);
        // while (tileAt(i, j) == 0) i = StdRandom.uniform(0, n);
        // while ((p == i && q == j) || tileAt(p, q) == 0) q = StdRandom.uniform(0, n);

        return new Board(exch(iBlank, 0, iBlank, n - 1));
    }

    private int[][] exch(int i, int j, int m, int k) {
        int[][] b = new int[n][n];
        for (int p = 0; p < n; p++)
            for (int q = 0; q < n; q++)
                b[p][q] = tiles[p][q];
        int temp = b[i][j];
        b[i][j] = b[m][k];
        b[m][k] = temp;
        return b;
    }

    private int distance(int tile, int i, int j) {
        int iPos = (tile - 1) / n;
        int jPos = (tile - 1) % n;
        int hDist = jPos - j > 0 ? jPos - j : j - jPos;
        int vDist = iPos - i > 0 ? iPos - i : i - iPos;
        return hDist + vDist;
    }

    private int[][] moveLeft(int i, int j) {
        int[][] b = new int[n][n];
        for (int p = 0; p < n; p++)
            for (int q = 0; q < n; q++)
                b[p][q] = tiles[p][q];
        if (j > 0) b[i][j - 1] = b[i][j];
        b[i][j] = 0;
        return b;
    }

    private int[][] moveRight(int i, int j) {
        int[][] b = new int[n][n];
        for (int p = 0; p < n; p++)
            for (int q = 0; q < n; q++)
                b[p][q] = tiles[p][q];
        if (j < n - 1) b[i][j + 1] = b[i][j];
        b[i][j] = 0;
        return b;
    }

    private int[][] moveUp(int i, int j) {
        int[][] b = new int[n][n];
        for (int p = 0; p < n; p++)
            for (int q = 0; q < n; q++)
                b[p][q] = tiles[p][q];
        if (i > 0) b[i - 1][j] = b[i][j];
        b[i][j] = 0;
        return b;
    }

    private int[][] moveDown(int i, int j) {
        int[][] b = new int[n][n];
        for (int p = 0; p < n; p++)
            for (int q = 0; q < n; q++)
                b[p][q] = tiles[p][q];
        if (i < n - 1) b[i + 1][j] = b[i][j];
        b[i][j] = 0;
        return b;
    }


    // unit testing (not graded)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        StdOut.println(initial);

        for (Board bd : initial.neighbors())
            StdOut.println(bd);

        StdOut.println(initial.twin());

        StdOut.println("Hammming = " + initial.hamming());
        StdOut.println("Manhattan = " + initial.manhattan());
        StdOut.println("IsGoal = " + initial.isGoal());
    }

}
