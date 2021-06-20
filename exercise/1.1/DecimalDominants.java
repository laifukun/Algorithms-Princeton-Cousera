public class DecimalDominants {

    public static void dominant(int[] a, int k) {
        int n = a.length;
        int freq = n / k;
        StdRandom.shuffle(a);

    }

    public static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int gt = hi;
        int lw = lo;
        int v = a[lo];
        while (i <= gt) {
            if (a[i] == v) i++;
            else if (a[i] > v) exch(a, i, gt--);
            else if (a[i] < v) exch(a, i++, lw++);
        }
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {

    }
}
