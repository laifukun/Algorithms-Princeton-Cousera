/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DiameterCenter {
    private final boolean[] marked;
    private final int[] distTo;
    private final int[] edgeTo;
    private final int maxsr;
    private final int maxdest;

    DiameterCenter(Graph G) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        // int maxLength = 0;
        int s = 0;

        maxsr = bfs(G, s);
        for (int i = 0; i < G.V(); i++) {
            marked[i] = false;
            edgeTo[i] = 0;
        }
        maxdest = bfs(G, maxsr);

    }


    public Iterable<Integer> longestPath() {
        Stack<Integer> path = new Stack<Integer>();
        for (int x = maxdest; x != maxsr; x = edgeTo[x])
            path.push(x);
        path.push(maxsr);
        return path;
    }

    private int bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(s);
        marked[s] = true;
        distTo[s] = 0;
        int v = -1;
        while (!q.isEmpty()) {
            v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.enqueue(w);
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                    edgeTo[w] = v;
                }
            }
        }
        return v;
    }

    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        Graph graph = new Graph(in);
        //StdOut.print(graph);
        int s = 0;
        DiameterCenter dc = new DiameterCenter(graph);
        for (int i : dc.longestPath())
            StdOut.print(i + " ");
    }
}

