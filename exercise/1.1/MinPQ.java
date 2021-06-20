public class MinPQ<Item extends Comparable<Item>> {

    private Item[] pq;
    private int N;

    MinPQ(int maxCap) {
        pq = (Item[]) new Comparable[maxCap + 1];
        N = 0;
    }

    public Item getMin() {
        return pq[1];
    }

    public void insert(Item item) {
        if ((N + 1) >= pq.length)
            resize(2 * N);
        pq[++N] = item;
        swim(N);
    }

    public Item delMin() {
        if (!isEmpty()) {
            Item max = pq[1];
            pq[1] = pq[N--];
            sink(1);
            return max;
        }
        else throw new RuntimeException("Empty MaxPQ!");
    }

    public Item max() {

        if (isEmpty()) throw new RuntimeException("Empty MaxPQ!");
        Item max = pq[N / 2];
        int i = N / 2;
        while (i++ <= N) max = less(max, pq[i]) ? pq[i] : max;
        return max;
    }

    private void resize(int N) {
        Item[] newpq = (Item[]) new Comparable[N + 1];
        for (int i = 0; i < pq.length; i++)
            newpq[i] = pq[i];

        pq = newpq;
    }

    protected void swim(int k) {
        while (k > 1 && less(pq[k], pq[k / 2])) {
            exch(pq, k / 2, k);
            k = k / 2;
        }
    }

    protected void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq[j + 1], pq[j])) j++;
            if (less(pq[j], pq[k])) {
                exch(pq, k, j);
                k = j;
            }
            else break;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void print() {
        for (int i = 1; i <= N; i++)
            StdOut.print(pq[i] + " ");
    }

    public static void main(String[] args) {

        MinPQ<String> testPQ = new MinPQ<String>(100);
        testPQ.insert("P");
        testPQ.insert("Q");
        testPQ.insert("E");
        testPQ.delMin();
        testPQ.insert("X");
        testPQ.insert("A");
        testPQ.insert("M");
        testPQ.delMin();
        testPQ.insert("P");
        testPQ.insert("L");
        testPQ.insert("E");
        testPQ.delMin();
        StdOut.println(testPQ.max());
        testPQ.print();


    }
}
