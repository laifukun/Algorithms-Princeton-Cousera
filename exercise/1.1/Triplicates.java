public class Triplicates {

    public static Comparable commonName(Comparable[] first, Comparable[] second,
                                        Comparable[] third) {

        int N = first.length;
        assert N == second.length;
        assert N == third.length;
        MergeSort.sort(first);
        MergeSort.sort(second);
        MergeSort.sort(third);

        for (int i = 0; i < N; i++) {
            Comparable v = first[i];
            if (BinarySearch.rank(v, second) != -1 && BinarySearch.rank(v, third) != -1)
                return v;
        }
        return null;

    }

    public static void main(String[] args) {

        String[] a = { "John", "Kevin", "Allen", "Evan" };
        String[] b = { "Eva", "Max", "Brian", "Evan" };
        String[] c = { "Justin", "Evan", "Alex", "Shawn" };

        StdOut.println(commonName(a, b, c));

    }
}
