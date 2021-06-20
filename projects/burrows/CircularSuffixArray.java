/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class CircularSuffixArray {
    private static final int R = 256;
    private final int[] index;
    private CircularSuffix[] aux;

    private class CircularSuffix implements Comparable<CircularSuffix> {
        private final String supStr;
        private final int start;
        // private final int n;

        CircularSuffix(String s, int i) {
            supStr = s;
            // n = s.length();
            start = i;
        }

        char charAt(int i) {

            int n = supStr.length();
            if (start + i > n - 1) return supStr.charAt(start + i - n);
            else return supStr.charAt(start + i);
        }

        int iStart() {
            return start;
        }

        public int compareTo(CircularSuffix x) {
            int n = supStr.length();
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < x.charAt(i)) return -1;
                else if (this.charAt(i) > x.charAt(i)) return 1;
            }
            return 0;
        }

    }

    // circular suffix array of s
    public CircularSuffixArray(String s) {

        if (s == null) throw new IllegalArgumentException();
        int n = s.length();
        index = new int[n];
        CircularSuffix[] cirSuf = new CircularSuffix[n];
        aux = new CircularSuffix[n];
        for (int i = 0; i < n; i++)
            cirSuf[i] = new CircularSuffix(s, i);

        quick3Sort(cirSuf, 0, n - 1, 0);

        for (int i = 0; i < n; i++)
            index[i] = cirSuf[i].iStart();

    }

    private void sort(CircularSuffix[] a, int lo, int hi, int d) {

        if (d >= index.length) return;
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++)
            count[a[i].charAt(d) + 2]++;
        for (int r = 0; r < R + 1; r++)
            count[r + 1] += count[r];
        for (int i = lo; i <= hi; i++)
            aux[count[a[i].charAt(d) + 1]++] = a[i];
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];
        for (int r = 0; r < R; r++)
            if (count[r + 1] - count[r] > 1)
                sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }

    private void quick3Sort(CircularSuffix[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = a[lo].charAt(d);
        int i = lo + 1;
        while (i <= gt) {
            int t = a[i].charAt(d);
            if (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else i++;
        }
        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
        quick3Sort(a, lo, lt - 1, d);
        if (v >= 0 && d < index.length - 1) quick3Sort(a, lt, gt, d + 1);
        quick3Sort(a, gt + 1, hi, d);
    }

    private void exch(CircularSuffix[] a, int i, int j) {
        CircularSuffix t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // length of s
    public int length() {
        return index.length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= index.length) throw new IllegalArgumentException();
        return index[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String s = "*************";
        CircularSuffixArray csa = new CircularSuffixArray(s);
        csa.length();
    }
}
