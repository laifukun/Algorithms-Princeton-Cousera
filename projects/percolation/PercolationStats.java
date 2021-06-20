import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENT_95 = 1.96;
    private final int nT;
    private final double[] threshold;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials < 1) throw new IllegalArgumentException("Invalid Arument!");
        nT = trials;
        threshold = new double[nT];

        for (int i = 0; i < nT; i++) {
            Percolation test = new Percolation(n);
            while (!test.percolates()) {
                int p = StdRandom.uniform(1, n + 1);
                int q = StdRandom.uniform(1, n + 1);
                test.open(p, q);
            }
            threshold[i] = (double) test.numberOfOpenSites() * 1.0 / n / n;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double mean = mean();
        double s = stddev();
        return mean - CONFIDENT_95 * s / Math.sqrt(nT);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double mean = mean();
        double s = stddev();
        return mean + CONFIDENT_95 * s / Math.sqrt(nT);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats pStats = new PercolationStats(n, T);

        StdOut.println("mean                    = " + pStats.mean());
        StdOut.println("stddev                  = " + pStats.stddev());
        StdOut.println(
                "95% confidence interval = [" + pStats.confidenceLo() + ", " + pStats.confidenceHi()
                        + "]");
    }

}
