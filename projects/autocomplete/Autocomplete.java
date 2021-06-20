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

import java.util.Comparator;

public class Autocomplete {

    private Term[] terms;
    private Term[] auxTerms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) throw new IllegalArgumentException("");
        this.terms = new Term[terms.length];
        this.auxTerms = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null) throw new IllegalArgumentException("");
            this.terms[i] = terms[i];
        }
        sortTerms(this.terms);
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
        // Find the first index and the last index in already sorted terms
        if (prefix == null) throw new IllegalArgumentException("");
        Term[] target;
        Term key = new Term(prefix, 0);
        int r = prefix.length();
        // sortTerms(terms, Term.byPrefixOrder(r));
        int start = BinarySearchDeluxe.firstIndexOf(terms, key, Term.byPrefixOrder(r));
        if (start == -1) return new Term[0];
        int end = BinarySearchDeluxe.lastIndexOf(terms, key, Term.byPrefixOrder(r));
        target = new Term[end - start + 1];
        for (int i = start; i <= end; i++)
            target[i - start] = terms[i];
        sortTerms(target, Term.byReverseWeightOrder());
        return target;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("");
        Term key = new Term(prefix, 0);
        int r = prefix.length();
        int start = BinarySearchDeluxe.firstIndexOf(terms, key, Term.byPrefixOrder(r));
        if (start == -1) return 0;
        int end = BinarySearchDeluxe.lastIndexOf(terms, key, Term.byPrefixOrder(r));
        return end - start + 1;
    }

    private void sortTerms(Term[] a) {
        sortTerms(a, 0, a.length - 1);
    }

    private void sortTerms(Term[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sortTerms(a, lo, mid);
        sortTerms(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private void merge(Term[] a, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) auxTerms[i] = a[i];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if (i > mid) a[k] = auxTerms[j++];
            else if (j > hi) a[k] = auxTerms[i++];
            else if (auxTerms[i].compareTo(auxTerms[j]) < 0) a[k] = auxTerms[i++];
            else a[k] = auxTerms[j++];
    }

    private void sortTerms(Term[] a, Comparator<Term> comparator) {
        sortTerms(a, comparator, 0, a.length - 1);
    }

    private void sortTerms(Term[] a, Comparator<Term> comparator, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sortTerms(a, comparator, lo, mid);
        sortTerms(a, comparator, mid + 1, hi);
        merge(a, comparator, lo, mid, hi);
    }

    private void merge(Term[] a, Comparator<Term> comparator, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) auxTerms[i] = a[i];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if (i > mid) a[k] = auxTerms[j++];
            else if (j > hi) a[k] = auxTerms[i++];
            else if (comparator.compare(auxTerms[i], auxTerms[j]) < 0) a[k] = auxTerms[i++];
            else a[k] = auxTerms[j++];
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
