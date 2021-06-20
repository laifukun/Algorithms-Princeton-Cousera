/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        for (int v : G.adj(s)) {
            if (!marked[v]) {
                dfs(G, v);
                edgeTo[v] = s;
            }
        }
    }

    private void nonRecurDFS(Graph G, int s) {
        //marked[s] = true;
        Stack<Integer> st = new Stack<Integer>();
        st.push(s);
        // marked[s] = true;

        while (!st.isEmpty()) {
            int v = st.pop();
            marked[v] = true;
            for (int w : G.adj(v))
                if (!marked[w]) {
                    st.push(w);
                    edgeTo[w] = v;
                }

        }
    }


    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }


    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        Graph graph = new Graph(in);
        //StdOut.print(graph);
        int s = 0;
        DepthFirstSearch dfs = new DepthFirstSearch(graph, s);
        for (int i : dfs.pathTo(4))
            StdOut.print(i + " ");
    }
}
