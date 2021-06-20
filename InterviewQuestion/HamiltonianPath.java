/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class HamiltonianPath {
    private boolean[] marked;
    private boolean hamiltonPath;

    HamiltonianPath(Graph G) {
        marked = new boolean[G.V()];
        int v = dfs(G, 0);
        dfs(G, v);
        for (int i = 0; i < G.V(); i++)
            if (!marked[i]) {
                hamiltonPath = false;
                break;
            }
        hamiltonPath = true;
    }

    private int dfs(Graph G, int s) {
        marked[s] = true;
        int w = 0;
        for (int v : G.adj(s)) {
            if (!marked[v]) {
                dfs(G, v);
                w = v;
                //        edgeTo[v] = s;
            }
        }
        return w;
    }

    public boolean hasHamiltonPath() {
        return hamiltonPath;
    }

    public static void main(String[] args) {

    }
}
