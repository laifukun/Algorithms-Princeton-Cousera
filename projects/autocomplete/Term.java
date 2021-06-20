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

public class Term implements Comparable<Term> {

    private final String query;
    private final long weight;

    private static class WeightComparator implements Comparator<Term> {
        public int compare(Term t1, Term t2) {
            if (t1.weight < t2.weight) return 1;
            if (t1.weight > t2.weight) return -1;
            return 0;
        }
    }

    private static class LexicographicComparator implements Comparator<Term> {
        private final int r;

        LexicographicComparator(int r) {
            this.r = r;
        }

        public int compare(Term t1, Term t2) {
            if (r > t1.query.length() || r > t2.query.length()) return t1.query.compareTo(t2.query);
            String t1s = t1.query.substring(0, r);
            String t2s = t2.query.substring(0, r);
            return t1s.compareTo(t2s);
        }
    }

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null || weight < 0) throw new IllegalArgumentException(" ");
        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new WeightComparator();
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) throw new IllegalArgumentException(" ");
        return new LexicographicComparator(r);
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        if (that == null) throw new IllegalArgumentException(" ");
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return weight + "\t\t" + query;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term[] term = new Term[3];
        term[0] = new Term("hello", 100);
        term[1] = new Term("helao", 200);
        term[2] = new Term("abc", 50);

        for (int i = 0; i < 3; i++)
            StdOut.println(term[i]);

        StdOut.println("Sort by full query: ");
        Arrays.sort(term);
        for (int i = 0; i < 3; i++)
            StdOut.println(term[i]);
        StdOut.println("Sort by reverse weight: ");

        Arrays.sort(term, Term.byReverseWeightOrder());
        for (int i = 0; i < 3; i++)
            StdOut.println(term[i]);

        StdOut.println("Sort by by prefixOrder 2: ");

        Arrays.sort(term, Term.byPrefixOrder(2));
        for (int i = 0; i < 3; i++)
            StdOut.println(term[i]);

        StdOut.println(term[0].compareTo(term[1]));

    }

}
