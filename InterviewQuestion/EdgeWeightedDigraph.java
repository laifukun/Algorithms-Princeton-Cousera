/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    EdgeWeightedDigraph(In in) {
        this.V = Integer.parseInt(in.readLine());
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();

        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<DirectedEdge>();
        E = Integer.parseInt(in.readLine());
        while (!in.isEmpty()) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    void addEdge(DirectedEdge e) {
        int v = e.from();
        adj[v].add(e);
        E++;

    }

    Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> eIter = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge e : adj[v])
                eIter.add(e);
        return eIter;
    }

    public static void main(String[] args) {

    }
}
