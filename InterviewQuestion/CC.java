/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class CC {
    private final int[] id;
    private final boolean marked[];
    private int count;

    CC(Graph G) //find connected components in G
    {
        id = new int[G.V()];
        marked = new boolean[G.V()];
        count = 0;
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }

        }
    }

    public boolean connected(int v, int w) //are v and w connected?
    {
        return id[v] == id[w];
    }

    public int count() //number of connected components
    {
        return count;
    }

    public int id(int v) //component identifier for v
    {
        return id[v];
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        id[s] = count;
        for (int w : G.adj(s))
            if (!marked[w]) dfs(G, w);
    }

    public static void main(String[] args) {

    }
}
