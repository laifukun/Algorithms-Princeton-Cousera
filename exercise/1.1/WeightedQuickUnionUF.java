public class WeightedQuickUnionUF {
    // weighted tree
    private int[] id;
    private int[] treeDepth;
    private int count;

    WeightedQuickUnionUF(int N) {
        id = new int[N];
        treeDepth = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            treeDepth[i] = 1;
        }
        count = N;
    }

    public void union(int p, int q) {
        // find root of p and root of q, if they point to the same root, return
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        // if p and q not connected, make them connnected and redirect all the nodes connecting q
        if (treeDepth[pRoot] < treeDepth[qRoot]) {
            id[pRoot] = qRoot;
            treeDepth[qRoot] += treeDepth[pRoot];
        }
        else {
            id[qRoot] = pRoot;
            treeDepth[pRoot] += treeDepth[qRoot];
        }

        count--;
    }

    public int find(int p) {
        while (id[p] != p) p = id[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i : id)
            s.append(i + " ");
        return s.toString();
    }


    public static void main(String[] args) {

        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
        StdOut.println(uf);

    }
}
