/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DeluxeBFS {

    private final HashMap<Integer, Integer> vVisited;
    private final HashMap<Integer, Integer> wVisited;
    private final Digraph graph;
    private final LinkedHashMap<List<Integer>, int[]> cache;

    public DeluxeBFS(Digraph G) {
        this.graph = new Digraph(G);
        vVisited = new HashMap<Integer, Integer>();
        wVisited = new HashMap<Integer, Integer>();
        cache = new LinkedHashMap<List<Integer>, int[]>(5000) {
            public boolean removeEldestEntry(Map.Entry<List<Integer>, int[]> eldest) {
                return size() > 5000;
            }
        };
        //   vMarked = new HashMap<Integer,Integer>();
        //   wMarked = new HashMap<Integer, Integer> ();
    }

    private int[] searchRootKernel(Queue<Integer> qv, Queue<Integer> qw) {
        int minLength = Integer.MAX_VALUE;

        int dist = 0;
        int minAnct = -1;
        int vCount, wCount;
        int vNewCount = qv.size();
        int wNewCount = qw.size();
        while (!qv.isEmpty() || !qw.isEmpty()) {
            vCount = vNewCount;
            wCount = wNewCount;
            vNewCount = 0;
            wNewCount = 0;
            for (int i = 0; i < vCount; i++) {
                int sv = qv.dequeue();
                int vDist = vVisited.get(sv);
                if (vDist >= minLength)
                    return new int[] { minAnct, minLength };

                for (int p : graph.adj(sv)) {
                    if (!vVisited.containsKey(p)) {
                        //  vMarked[p] = true;
                        //  vDistTo[p] = vDistTo[sv] + 1;
                        vVisited.put(p, vDist + 1);
                        qv.enqueue(p);
                        ++vNewCount;

                        if (wVisited.containsKey(p)) {
                            // dist = vDistTo[p] + wDistTo[p];
                            dist = vDist + 1 + wVisited.get(p);
                            if (dist < minLength) {
                                minLength = dist;
                                minAnct = p;
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < wCount; i++) {
                int sw = qw.dequeue();
                int wDist = wVisited.get(sw);
                if (wDist >= minLength)
                    return new int[] { minAnct, minLength };

                for (int p : graph.adj(sw)) {
                    if (!wVisited.containsKey(p)) {
                        // wMarked[p] = true;
                        // wDistTo[p] = wDistTo[sw] + 1;
                        wVisited.put(p, wDist + 1);
                        qw.enqueue(p);
                        ++wNewCount;

                        if (vVisited.containsKey(p)) {
                            // dist = vDistTo[p] + wDistTo[p];
                            dist = vVisited.get(p) + wDist + 1;
                            if (dist < minLength) {
                                minLength = dist;
                                minAnct = p;
                            }
                        }
                    }
                }
            }
        }
        if (minAnct == -1) return new int[] { -1, -1 };
        return new int[] { minAnct, minLength };

    }

    public int commonRoot(int v, int w) {
        resetVisited();
        return searchRoot(v, w)[0];
    }

    public int distance(int v, int w) {
        resetVisited();
        return searchRoot(v, w)[1];
    }

    public int commonRoot(Iterable<Integer> vIter, Iterable<Integer> wIter) {
        resetVisited();
        return searchRoot(vIter, wIter)[0];
    }

    public int distance(Iterable<Integer> vIter, Iterable<Integer> wIter) {
        resetVisited();
        return searchRoot(vIter, wIter)[1];
    }


    private int[] searchRoot(int v, int w) {
        LinkedList<Integer> vw = new LinkedList<Integer>();
        vw.add(Math.min(v, w));
        vw.add(Math.max(v, w));

        if (cache.containsKey(vw)) return cache.get(vw);
        Queue<Integer> qv = new Queue<Integer>();
        qv.enqueue(v);
        vVisited.put(v, 0);
        if (v == w)
            return new int[] { v, 0 };

        Queue<Integer> qw = new Queue<Integer>();
        qw.enqueue(w);
        wVisited.put(w, 0);
        int[] res = searchRootKernel(qv, qw);
        cache.put(vw, res);
        return res;
    }

    private int[] searchRoot(Iterable<Integer> vIter, Iterable<Integer> wIter) {
        Queue<Integer> qv = new Queue<Integer>();
        Queue<Integer> qw = new Queue<Integer>();
        LinkedList<Integer> vw = new LinkedList<Integer>();
        for (Integer v : vIter) {
            if (v == null || v >= graph.V() || v < 0)
                throw new IllegalArgumentException();
            qv.enqueue(v);
            vVisited.put(v, 0);
            vw.add(v);
        }

        for (Integer w : wIter) {
            if (w == null || w >= graph.V() || w < 0)
                throw new IllegalArgumentException();
            //    if (vMarked[w]) return w;
            if (vVisited.containsKey(w)) {
                return new int[] { w, 0 };
            }
            wVisited.put(w, 0);
            qw.enqueue(w);
            vw.add(w);
        }
        if (cache.containsKey(vw)) return cache.get(vw);

        int[] res = searchRootKernel(qv, qw);
        cache.put(vw, res);
        return res;
    }

    private void resetVisited() {
        if (!vVisited.isEmpty()) vVisited.clear();

        if (!wVisited.isEmpty()) wVisited.clear();
    }
/*
    public void bfs(int v) {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(v);
        vMarked[v] = true;
        vDistTo[v] = 0;
        while (!q.isEmpty()) {
            int s = q.dequeue();
            for (int w : graph.adj(s)) {
                if (!vMarked[w]) {
                    vMarked[w] = true;
                    vDistTo[w] = vDistTo[s] + 1;
                    q.enqueue(w);
                }
            }
        }
    }

 */

    /**
     * @param args
     */

    public static void main(String[] args) {

    }
}
