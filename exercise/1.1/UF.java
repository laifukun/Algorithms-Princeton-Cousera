public class UF {
    // flat tree
    private int[] id;
    private int count;

    UF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
        count = N;
    }

    public void union(int p, int q) {
        // find root of p and root of q, if they point to the same root, return
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        // if p and q not connected, make them connnected and redirect all the nodes connecting q
        // point to p

        // for (int i = 0; i < id.length; i++)
        //    if (find(i) == qRoot) id[i] = pRoot;
        id[pRoot] = qRoot;
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
        UF uf = new UF(N);
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
