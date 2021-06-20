public class CornerCase {
    public static void main(String[] args) {
        // corner case test
        String alg1 = args[0];

        // arrays already in order
        int N = 10;
        StdOut.println("Corner Case Test for " + alg1);
        StdOut.println("Test arrays already in order: ");
        StdOut.print("Original Array: ");
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = (double) i / 10;
            StdOut.printf("%.2f ", a[i]);
        }
        StdOut.println();
        SortCompare.time(alg1, a);
        StdOut.print("Sorted Array: ");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%.2f ", a[i]);
        }
        StdOut.println();
        StdOut.println();

        // arrays in reverse order

        StdOut.println("Test arrays in reverse order: ");
        StdOut.print("Original Array: ");

        for (int i = 0; i < N; i++) {
            a[i] = (double) N - i;
            StdOut.printf("%.2f ", a[i]);
        }
        StdOut.println();
        SortCompare.time(alg1, a);
        StdOut.print("Sorted Array: ");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%.2f ", a[i]);
        }
        StdOut.println();
        StdOut.println();

        //arrays where all keys are the same

        StdOut.println("Test arrays with all the same keys: ");
        StdOut.print("Original Array: ");

        for (int i = 0; i < N; i++) {
            a[i] = (double) N;
            StdOut.printf("%.2f ", a[i]);
        }
        StdOut.println();
        SortCompare.time(alg1, a);
        StdOut.print("Sorted Array: ");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%.2f ", a[i]);
        }
        StdOut.println();
        StdOut.println();

        //arrays consisting of only two distinct values

        StdOut.println("Test arrays with only two keys: ");
        StdOut.print("Original Array: ");

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) a[i] = (double) N;
            else a[i] = (double) N / 2;
            StdOut.printf("%.2f ", a[i]);
        }
        StdOut.println();
        SortCompare.time(alg1, a);
        StdOut.print("Sorted Array: ");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%.2f ", a[i]);
        }
        StdOut.println();
        StdOut.println();

        //Empty array

        StdOut.println("Test arrays with empty array: ");
        StdOut.print("Original Array: ");
        Double[] b = new Double[0];

        StdOut.println();
        SortCompare.time(alg1, b);
        StdOut.print("Sorted Array: ");

        StdOut.println();
        StdOut.println();

        // arrays with only one element

        StdOut.println("Test arrays with empty array: ");
        StdOut.print("Original Array: ");
        Double[] c = new Double[1];
        c[0] = 1.0;
        StdOut.println();
        SortCompare.time(alg1, c);
        StdOut.print("Sorted Array: " + c[0]);

        StdOut.println();
        StdOut.println();

    }
}
