/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ShortestDirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle; // vertices on a cycle (if one exists)
    private int cycleN;
    private boolean[] onStack; // vertices on recursive call stack

    public ShortestDirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        cycleN = Integer.MAX_VALUE;
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) bfs(G, v);
    }

    private void bfs(Digraph G, int v) {

        Queue<Integer> q = new Queue<Integer>();
        for (int i = 0; i < G.V(); i++) onStack[i] = false;
        q.enqueue(v);
        marked[v] = true;
        onStack[v] = true;
        while (!q.isEmpty()) {
            v = q.dequeue();
            marked[v] = true;
            onStack[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    q.enqueue(w);
                }
                else if (onStack[w]) {
                    Stack<Integer> tempcycle = new Stack<Integer>();
                    for (int x = v; x != w; x = edgeTo[x]) {
                        tempcycle.push(x);
                    }
                    tempcycle.push(w);
                    tempcycle.push(v);

                    if (tempcycle.size() < cycleN) {
                        cycle = tempcycle;
                        cycleN = cycle.size();
                    }
                }
            }


        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        Digraph graph = new Digraph(in);
        //StdOut.print(graph);
        int s = 0;
        ShortestDirectedCycle sdc = new ShortestDirectedCycle(graph);
        if (sdc.hasCycle())
            for (int i : sdc.cycle)
                StdOut.print(i + " ");
    }
}

