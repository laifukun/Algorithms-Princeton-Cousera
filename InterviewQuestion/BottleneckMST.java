/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class BottleneckMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    BottleneckMST(EdgeWeightedGraph ewg) {
        marked = new boolean[ewg.V()];
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();

        visit(ewg, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            if (marked[v]) visit(ewg, w);
            else if (marked[w]) visit(ewg, w);
        }
    }


    private void visit(EdgeWeightedGraph ewg, int w) {
        marked[w] = true;
        for (Edge e : ewg.adj(w))
            if (!marked[e.other(w)]) pq.insert(e);
    }

    public static void main(String[] args) {

    }
}
