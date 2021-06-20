import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF sites;
    // private final WeightedQuickUnionUF sites2;
    private final int N;
    private byte[] state;
    private int openSites; // number of open sites
    private boolean percolated;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Wrong site numbers!");
        sites = new WeightedQuickUnionUF(n * n + 2);
        // sites2 = new WeightedQuickUnionUF(n * n + 1);
        N = n;

        state = new byte[n * n + 2];
        for (int i = 0; i < n * n + 2; i++)
            state[i] = 0x00;
        state[0] = 0x03;
        state[n * n + 1] = 0x05;
        openSites = 0;
        percolated = false;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (isOpen(row, col)) return;
        int p = index(row, col);
        byte status = 0x01;
        int q;
        if (row == 1) {
            // sites.union(0, p);
            // state[p] = (byte) (state[sites.find(0)] | state[p]);
            state[p] = (byte) (state[p] | 0x03);
            status = 0x03;
            // sites2.union(0, p);
        }
        if (row == N) {
            // sites.union(N * N + 1, p);
            // state[p] = (byte) (state[sites.find(N * N + 1)] | state[p]);
            state[p] = (byte) (state[p] | 0x05);
            status = 0x05;
        }
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            q = index(row, col - 1);
            status = (byte) (status | state[sites.find(q)]);
            sites.union(q, p);
            // state[p] = (byte) (state[sites.find(q)] | state[p] | state[q]);
            // sites2.union(index(row, col - 1), p);
        }
        if (col + 1 <= N && isOpen(row, col + 1)) {
            q = index(row, col + 1);
            status = (byte) (status | state[sites.find(q)]);
            sites.union(q, p);
            // state[p] = (byte) (state[sites.find(q)] | state[p] | state[q]);
            // sites2.union(index(row, col + 1), p);
        }
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            q = index(row - 1, col);
            status = (byte) (status | state[sites.find(q)]);
            sites.union(q, p);
            // state[p] = (byte) (state[sites.find(q)] | state[p] | state[q]);
            // sites2.union(index(row - 1, col), p);
        }
        if (row + 1 <= N && isOpen(row + 1, col)) {
            q = index(row + 1, col);
            status = (byte) (status | state[sites.find(q)]);
            sites.union(q, p);
            // state[p] = (byte) (state[sites.find(q)] | state[p] | state[q]);
            // sites2.union(index(row + 1, col), p);
        }
        status = (byte) (status | state[sites.find(p)]);

        // state[p] = (byte) (state[sites.find(p)] | state[p] | 0x01);
        state[sites.find(p)] = status; // (byte) (state[sites.find(p)] | state[p]);
        state[p] = status;
        openSites++;
        if ((state[sites.find(p)] & 0x07) == 0x07) percolated = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        rangeCheck(row, col);
        int p = (row - 1) * N + col;
        return state[p] >= 0x01;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        rangeCheck(row, col);
        int p = index(row, col);
        return (state[sites.find(p)] & 0x03) == 0x03;
        // sites.find(p) == sites.find(0) &&
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // return sites.find(0) == sites.find(N * N + 1);
        return percolated;
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
        int N = StdIn.readInt();
        Percolation testPer = new Percolation(N);
        int i = 0;
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt() + 1;
            int q = StdIn.readInt() + 1;
            testPer.open(p, q);
            if (++i == 204) break;

        }
        StdOut.println(testPer.isFull(1, 9));

        // testPer.printState();
        //StdOut.println(testPer.percolates());
    }
}
