/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {
    private final int V;
    private int E;

    private Bag<Integer>[] adj;

    Graph(int V) // create an empty graph with V verties
    {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<Integer>();
    }

    Graph(In in) { // create a graph from input stresm
        V = Integer.parseInt(in.readLine());
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<Integer>();
        E = Integer.parseInt(in.readLine());
        while (!in.isEmpty()) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public int V() {
        return V;
    } // number of verties

    public int E() {
        return E;
    }  // number of edges

    public Iterable<Integer> adj(int v) { // verties adjacent to v or directly connected to v
        return adj[v];
    }

    public String toString() {
        StringBuilder str = new StringBuilder(
                "Graph: \n" + "Verties: " + V + "\nEdges: " + E + "\n");
        for (int v = 0; v < V; v++)
            for (int w : adj[v])
                str.append(v + " - " + w + "\n");
        return str.toString();
    }

    public static void main(String[] args) {

    }
}
