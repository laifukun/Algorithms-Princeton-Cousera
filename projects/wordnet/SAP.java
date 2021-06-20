/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    // constructor takes a digraph (not necessarily a DAG)
    private final Digraph graph;
    private final DeluxeBFS deBFS;


    public SAP(Digraph G) {
        this.graph = new Digraph(G);
        deBFS = new DeluxeBFS(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path

    /**
     * @param v
     * @param w
     * @return
     */
    public int length(int v, int w) {
        if (v >= graph.V() || w >= graph.V() || v < 0 || w < 0)
            throw new IllegalArgumentException();
        if (v == w) return 0;

        return deBFS.distance(v, w);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v >= graph.V() || w >= graph.V() || v < 0 || w < 0)
            throw new IllegalArgumentException();
        if (v == w) return v;

        return deBFS.commonRoot(v, w);

    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        return deBFS.distance(v, w);
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        return deBFS.commonRoot(v, w);
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
/*
        Queue<Integer> v = new Queue<Integer>();
        v.enqueue(9);
        v.enqueue(11);
        v.enqueue(14);
        v.enqueue(15);
        v.enqueue(19);

        Queue<Integer> w = new Queue<Integer>();
        w.enqueue(1);
        w.enqueue(8);
        w.enqueue(13);
        w.enqueue(16);
        w.enqueue(19);


        StdOut.println(sap.length(v, w));
        StdOut.println(sap.ancestor(v, w));

*/


        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }


    }
}
