public class MatrixOperation {

    public static double[][] tranpose(double[][] A) {

        int M = A.length;
        int N = A[0].length;
        double[][] B = new double[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                B[j][i] = A[i][j];  // tranpose of the matrix
            }
        }
        return B;
    }

    public static double dot(double[] a, double[] b) {
        // dot sum of two vectors
        double sum = 0.0;
        for (int i = 0; i < a.length; i++)
            sum += a[i] * b[i];
        return sum;
    }

    public static double[][] mult(double[][] a, double[][] b) {
        // matrix multiply of two matrix c = a*b
        int M = a.length;
        int N = b.length;
        int K = b[0].length;

        double[][] c = new double[M][K];

        for (int i = 0; i < M; i++)
            for (int p = 0; p < K; p++) {

                c[i][p] = 0.0;
                for (int j = 0; j < N; j++)
                    c[i][p] += a[i][j] * b[j][p];
            }
        return c;
    }

    public static double[] mult(double[][] a, double[] x) {
        // matrix- right vector multiply product
        int M = a.length;
        int N = x.length;

        double[] c = new double[M];

        for (int i = 0; i < M; i++) {
            c[i] = 0.0;
            for (int j = 0; j < N; j++)
                c[i] += a[i][j] * x[j];
        }
        return c;
    }

    public static double[] mult(double[] x, double[][] a) {
        // left vector - matrix multiply product
        int M = x.length;
        int N = a[0].length;

        double[] c = new double[N];

        for (int i = 0; i < N; i++) {
            c[i] = 0.0;
            for (int j = 0; j < M; j++)
                c[i] += x[j] * a[j][i];
        }
        return c;
    }

    public static double[] readVector() {
        int length;
        StdOut.print("Enter the length of vector: \n");
        length = StdIn.readInt();
        double[] a = new double[length];
        for (int i = 0; i < length; i++) {

            a[i] = StdIn.readDouble();

        }
        return a;
    }

    public static void printVector(double[] a) {
        StdOut.print("[ ");
        for (int i = 0; i < a.length; i++)
            StdOut.printf("%10.3f", a[i]);
        StdOut.print(" ]\n");
    }

    public static double[][] readMatrix() {
        int M, N;
        StdOut.print("Enter the row of vector: \n");
        M = StdIn.readInt();
        StdOut.print("Enter the column of vector: \n");
        N = StdIn.readInt();
        double[][] a = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++) {

                a[i][j] = StdIn.readDouble();

            }
        return a;
    }

    public static void printMatrix(double[][] a) {

        for (int i = 0; i < a.length; i++) {
            StdOut.print("[ ");
            for (int j = 0; j < a[0].length; j++)
                StdOut.printf("%10.3f", a[i][j]);
            StdOut.print(" ]");
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        double[][] A = { { 4, 5, 6 }, { 3, 2, 1 }, { 9, 8, 7 } };
        double[] B = { 1., 2., 3. };
        double[][] c;
        double[] d;
        c = mult(A, A);
        StdOut.print(" Matrix A*A: \n");
        printMatrix(c);
        d = mult(A, B);
        StdOut.print(" Matrix-vector A*b: \n");
        printVector(d);
        d = mult(B, A);
        StdOut.print(" Vector-Matrix b*A: \n");
        printVector(d);

        // double[][] x = readMatrix();

        // B = tranpose(A);
    }
}
