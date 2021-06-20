/* *****************************************************************************
 *  Name:    Fukun Lai
 *  NetID:   //
 *  Precept: //
 *
 *  Partner Name:    //
 *  Partner NetID:   //
 *  Partner Precept: //
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) throw new IllegalArgumentException(" ");
        int n = a.length;
        int i = 0, j = n - 1;
        int firstIndex = -1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (comparator.compare(key, a[mid]) == 0) {
                firstIndex = mid;
                j = mid - 1;
            }
            if (comparator.compare(key, a[mid]) < 0) j = mid - 1;
            else if (comparator.compare(key, a[mid]) > 0) i = mid + 1;
        }

        return firstIndex;
    }

    // Returns the index of the last key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) throw new IllegalArgumentException(" ");
        int n = a.length;
        int i = 0, j = n - 1;
        int lastIndex = -1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (comparator.compare(key, a[mid]) == 0) {
                lastIndex = mid;
                i = mid + 1;
            }
            if (comparator.compare(key, a[mid]) < 0) j = mid - 1;
            else if (comparator.compare(key, a[mid]) > 0) i = mid + 1;
        }

        return lastIndex;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term[] term = new Term[6];
        term[0] = new Term("hello", 100);
        term[1] = new Term("helao", 200);
        term[2] = new Term("abc", 550);
        term[3] = new Term("abd", 150);
        term[4] = new Term("abf", 10);
        term[5] = new Term("abg", 350);

        Arrays.sort(term, Term.byPrefixOrder(2));
        for (int i = 0; i < 6; i++)
            StdOut.println(term[i]);

        StdOut.println("Index: " + lastIndexOf(term, new Term("abc", 200), Term.byPrefixOrder(2)));

    }
}
