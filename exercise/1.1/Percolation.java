import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
public class Percolation {

    private final WeightedQuickUnionUF sites;
    private final WeightedQuickUnionUF sites2;
    private final int N;
    private boolean state[];
    private int openSites;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Wrong site numbers!");
        sites = new WeightedQuickUnionUF(n * n + 2);
        sites2 = new WeightedQuickUnionUF(n * n + 1);
        N = n;

        state = new boolean[n * n + 2];
        for (int i = 0; i < n * n + 2; i++)
            state[i] = false;
        state[0] = true;
        state[n * n + 1] = true;

        openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (isOpen(row, col)) return;
        int p = index(row, col);
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            sites.union(index(row, col - 1), p);
            sites2.union(index(row, col - 1), p);
        }
        if (col + 1 <= N && isOpen(row, col + 1)) {
            sites.union(index(row, col + 1), p);
            sites2.union(index(row, col - 1), p);
        }
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            sites.union(index(row - 1, col), p);
            sites2.union(index(row, col - 1), p);
        }
        if (row + 1 <= N && isOpen(row + 1, col)) {
            sites.union(index(row + 1, col), p);
            sites2.union(index(row, col - 1), p);
        }
        if (row == 1) {
            sites.union(0, p);
            sites2.union(index(row, col - 1), p);
        }
        else if (row == N) sites.union(N * N + 1, p);
        state[p] = true;
        openSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        rangeCheck(row, col);
        int p = (row - 1) * N + col;
        return state[p];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        rangeCheck(row, col);
        int p = index(row, col);
        return sites2.connected(p, 0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return sites.connected(0, N * N + 1);
    }

    private void rangeCheck(int row, int col) {
        if (row > N || row < 1 || col > N || col < 1)
            throw new IllegalArgumentException("Out of range!");
    }

    private int index(int row, int col) {
        return (row - 1) * N + col;
    }

    private void printState() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++)
                StdOut.print(state[(i - 1) * N + j] + " ");
            StdOut.println();
        }

    }

    // test client (optional)
    public static void main(String[] args) {

        Percolation testPer = new Percolation(10);
        StdOut.println(testPer.percolates());
        testPer.open(1, 5);
        testPer.open(2, 5);
        testPer.open(3, 5);
        testPer.open(4, 5);
        testPer.open(5, 6);
        testPer.open(6, 6);
        testPer.open(7, 6);
        testPer.open(8, 6);
        //testPer.open(9, 6);
        testPer.open(10, 6);
        testPer.open(5, 5);

        //testPer.printState();
        StdOut.println(testPer.percolates());
    }
}
