import java.util.Iterator;
// exersice 1.3.34

public class RandomBag<Item> implements Iterable<Item> {
    private Item[] s = (Item[]) new Object[1];
    private int N;

    RandomBag() {
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        if (N == s.length) this.resize(2 * N);
        s[N++] = item;

    }

    public void resize(int size) {
        Item[] x = (Item[]) new Object[size];
        for (int i = 0; i < N; i++)
            x[i] = s[i];
        s = x;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item i : this)
            s.append(i + " ");
        return s.toString();
    }

    public BagIterator iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<Item> {
        private int i = StdRandom.uniform(N);
        private int iter = 0;

        public boolean hasNext() {
            return iter <= N;
        }

        public Item next() {
            if (!hasNext()) throw new RuntimeException("No more element!");
            Item item = s[i];
            iter++;
            i = StdRandom.uniform(N);
            return item;
        }
    }

    public static void main(String[] args) {
        RandomBag<String> cq = new RandomBag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            cq.add(item);
        }
        for (String a : cq)
            StdOut.print(a + " ");
        StdOut.println();

    }
}
