import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Misc {
    public static int lg(int N) {

        // find the largest integer less than lg2(N)
        if (N < 0) return 0;
        int base = 0B01000000000000000000000000000000;

        for (int i = 0; i < 30; i++) {
            if ((base & N) != 0)
                return 31 - i - 1;
            base = base >> 1;
        }
        return 0;
    }

    public static int[] histogram(int[] a, int M) {
        // histogram of array a with M
        int[] hist = new int[M];
        Arrays.fill(hist, 0);

        for (int i = 0; i < a.length; i++) {
            if (a[i] < M) hist[a[i]] += 1;
        }
        return hist;
    }

    public static void histogram(double[] a, int N, double l, double r) {

        // exercise 1.1.32
        //StdOut.println(" Input N: ");
        //int N = StdIn.readInt();
        //StdOut.println("Input Lower bound: ");
        //double l = StdIn.readDouble();
        //StdOut.println("Input Upper bound: ");
        //double r = StdIn.readDouble();
        int[] hist = new int[N];
        double[] range = new double[N + 1];
        range[0] = l;
        range[N] = r;
        for (int i = 1; i < N; i++) {
            range[i] = range[i - 1] + (r - l) / N;
            hist[i - 1] = 0;
        }

        for (int i = 0; i < a.length; i++)
        //for (int j = 1; j < range.length; j++)
        {
            //if (a[i] >= range[j - 1] && a[i] < range[j]) hist[j - 1]++;
            int index = (int) ((a[i] - l) / ((r - l) / N));
            if (index < 0 || index > N - 1) continue;
            hist[index]++;
            // StdOut.println("a: " + a[i] + " " + index);
        }

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, 500);
        StdDraw.setYscale(0, 50);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.001);

        for (int i = 0; i < N; i++) {

            double rw = 100.0 / N;
            double x = 500.0 * i / N + 1.5 * rw;
            double y = hist[i];

            double rh = hist[i];
            StdDraw.filledRectangle(x, y, rw, rh);
        }

    }

    public static double binomial(int N, int k, double p) {
        // exersice 1.1.27
        if ((N == 0) || (k < 0)) return 1.0;
        return (1.0 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
    }

    public static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    public static int mystery(int a, int b) {
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }

    public static double ln(int N) {
        // exeercise 1.1.20
        if (N == 1) return 0.0;
        return Math.log(N) + ln(N - 1);
    }

    public static void tabulateAve() {
        // exercises 1.1.21
        // need to improve

        Scanner scanner = new Scanner(System.in);
        List<String> tokens = new ArrayList<String>();
        while (scanner.hasNext()) {
            tokens.add(scanner.nextLine());
        }
        for (int i = 0; i < tokens.size(); i++) {
            String lines[] = tokens.get(i).split(",");

            StdOut.printf("%10s", lines[0]);
            int num1 = Integer.parseInt(lines[1].trim());
            int num2 = Integer.parseInt(lines[2].trim());

            StdOut.printf("%5d ", num1);
            StdOut.printf("%5d ", num2);
            StdOut.printf("%10.3f ", Double.valueOf(num1) / Double.valueOf(num2));
            StdOut.println();
        }

    }


    public static int gcd(int p, int q) {
        StdOut.println("p: " + p + ", q:" + q);
        if (p == 0) return q;
        int r = q % p;
        return gcd(r, p);
    }

    public static void Euclid() {
        int p, q;
        StdOut.print("Enter first number p: \n");
        p = StdIn.readInt();
        StdOut.print("Enter second number q: \n");
        q = StdIn.readInt();
        gcd(p, q);
    }

    public static boolean[][] primeMatrix(int N) {
        // exersise 1.1.30
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {

                if (gcd(i, j) == 1) a[i][j] = true;
                else a[i][j] = false;
            }
        return a;
    }

    public static void randomConnections(int N, double p) {
        int w = 1000, h = 1000;
        double x0 = w / 2.0, y0 = h / 2.0, r = 400.;
        double[] x = new double[N];
        double[] y = new double[N];

        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(x0, y0, r);
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.RED);
        // plot points
        for (int i = 0; i < N; i++) {
            double theta = 2 * Math.PI / N * i;
            x[i] = r * Math.cos(theta) + x0;
            y[i] = r * Math.sin(theta) + y0;
            StdDraw.point(x[i], y[i]);
        }
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.setPenRadius(0.005);
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++) {
                if (StdRandom.uniform() >= p) StdDraw.line(x[i], y[i], x[j], y[j]);
            }
    }

    public static boolean circularShift(String s, String t) {
        // exercise 1.2.6
        return s.length() == t.length() && (s + s).contains(t);
    }

    public static void main(String[] args) {
        //int N;
        //StdOut.print("Enter a integer: \n");
        //N = StdIn.readInt();
        //StdOut.print(lg(N));
        int[] a = { 1, 2, 3, 5, 1, 6, 2, 3, 5, 9 };
        int M = 10;
        int[] b;
        b = histogram(a, M);
        for (int i = 0; i < b.length; i++) {
            StdOut.print(b[i]);
            StdOut.print(" ");
        }
        StdOut.print(exR1(6));
        StdOut.print("" + 5364);
        StdOut.println();
        StdOut.println(mystery(2, 25));
        StdOut.println("1.1.20 answer: " + ln(10));

        //tabulateAve();


        //StdOut.println(gcd(1111111, 1234567));
        //Euclid();
        //StdOut.println(binomial(10, 2, 0.5));
        //randomConnections(20, 0.8);


        //double[] d = new double[200];
        //for (int i = 0; i < 200; i++)
        //    d[i] = StdRandom.uniform();
        //histogram(d);

        String s = "Hello World";
        String s2 = s;
        s = "world";
        //s.toUpperCase();
        //s.substring(6, 11);
        StdOut.println(s);
        StdOut.println(s2);
    }
}
