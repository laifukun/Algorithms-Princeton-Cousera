public class IndexMinPQ<Item extends Comparable<Item>> {

    private int N;           //number of elements
    private int[] pq;        //index array
    private int[] qp;        //inverse
    private Item[] keys;     //items with priorities

    IndexMinPQ(int maxN) {
        keys = (Item[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) qp[i] = -1;
    }

    public void insert(int k, Item item) {

        pq[++N] = k;
        qp[k] = N;
        keys[k] = item;
        swim(N);
    }

    public void change(int k, Item item) {
        Item oldItem = keys[k];
        keys[k] = item;
        if (contains(k)) {
            if (oldItem.compareTo(item) < 0) sink(qp[k]);
            else if (oldItem.compareTo(item) > 0) swim(qp[k]);
        }
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void delete(int k) {
        exch(qp[k], N--);
        keys[k] = null;
        swim(qp[N + 1]);
        sink(qp[N + 1]);
        qp[N + 1] = -1;
    }

    public Item min() {
        if (isEmpty()) throw new RuntimeException("Empty heap!");
        return keys[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }

    public Item getMin() {
        return keys[pq[1]];
    }

    public int delMin() {
        int minIndex = pq[1];
        exch(1, N--);
        sink(1);
        keys[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;
        return minIndex;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private boolean less(int v, int w) {
        return keys[v].compareTo(keys[w]) > 0;
    }

    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = i;
        qp[pq[j]] = j;

    }

    protected void swim(int k) {
        while (k > 1 && less(pq[k / 2], pq[k])) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    public void printPQ() {
        for (int i = 1; i <= N; i++)
            StdOut.print(pq[i] + " ");
    }

    public void printHeap() {
        for (int i = 1; i <= N; i++)
            StdOut.print(keys[pq[i]] + " ");
    }

    public void printKey() {
        for (int i = 1; i <= N; i++)
            StdOut.print(keys[i] + " ");
    }

    protected void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq[j], pq[j + 1])) j++;
            if (less(pq[k], pq[j])) {
                exch(k, j);
                k = j;
            }
            else break;
        }
    }

    public static void main(String[] args) {
        IndexMinPQ<Integer> mHeap = new IndexMinPQ<Integer>(100);

        mHeap.insert(1, 12);
        mHeap.insert(2, 13);
        mHeap.insert(3, 10);
        mHeap.insert(4, 2);
        mHeap.insert(5, 15);
        mHeap.insert(6, 18);
        mHeap.insert(7, 25);
        mHeap.insert(8, 82);
        mHeap.insert(9, 16);
        mHeap.insert(10, 38);
        mHeap.insert(11, 1);
        mHeap.insert(12, 50);
        mHeap.insert(13, 100);
        mHeap.insert(14, 56);

        StdOut.println(mHeap.getMin());
        mHeap.printKey();
        StdOut.println();
        mHeap.printHeap();
        StdOut.println();

        mHeap.change(5, 19);

        mHeap.printKey();
        StdOut.println();
        mHeap.printHeap();
        StdOut.println();
        mHeap.delete(3);

        while (!mHeap.isEmpty()) {
            StdOut.print(mHeap.getMin() + " ");
            mHeap.delMin();
        }

    }
}
