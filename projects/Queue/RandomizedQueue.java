import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] data;
    private int nN;

    // construct an empty randomized queue
    public RandomizedQueue() {
        data = (Item[]) new Object[1];
        nN = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return nN == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return nN;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument!");
        if (nN == data.length) resize(2 * nN);
        data[nN++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("No element!");
        int rdIndex = StdRandom.uniform(nN);
        Item item = data[rdIndex];
        exch(rdIndex, --nN);
        data[nN] = null;
        if (nN < data.length / 4) resize(data.length / 4);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("No element!");
        int rdIndex = StdRandom.uniform(nN);
        return data[rdIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private final int[] rdIndex;
        private int i;

        RandomIterator() {
            rdIndex = new int[nN];
            for (int j = 0; j < nN; j++)
                rdIndex[j] = j;
            StdRandom.shuffle(rdIndex);
            i = 0;
        }

        public boolean hasNext() {
            return i < nN;
        }

        public Item next() {
            if (i >= nN) throw new NoSuchElementException("No element!");
            Item a = data[rdIndex[i++]];
            return a;
        }

        public void remove() {
            throw new UnsupportedOperationException("No such operation!");
        }
    }

    private void resize(int n) {
        Item[] newData = (Item[]) new Object[n];
        for (int i = 0; i < nN; i++)
            newData[i] = data[i];
        data = newData;
    }

    private void exch(int i, int j) {
        Item item = data[i];
        data[i] = data[j];
        data[j] = item;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(5);
        test.enqueue(8);
        for (int i : test)
            StdOut.print(i + " ");
        StdOut.println();
        test.dequeue();
        test.dequeue();
        test.dequeue();
        for (int i : test)
            StdOut.print(i + " ");
    }

}
