import java.util.Iterator;

public class ArrayQueue<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int first = 0;
    private int last = 0;
    private int N = 0;


    public void enqueue(Item s) {
        if (N == a.length) resize(2 * N);
        a[++last] = s;
        N++;
    }

    public Item dequeue() {
        Item firstItem = a[first++];
        N--;
        if (first > a.length / 2) resize(2 * N);
        return firstItem;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = first, j = 0; i <= last; i++, j++) {
            temp[j] = a[i];
        }
        a = temp;
        last = N - 1;
        first = 0;

    }

    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<Item> {
        private int i = first;

        public boolean hasNext() {
            return i < last;
        }

        public Item next() {
            return a[i++];
        }

        public void remove() {
        }
    }

    public static void main(String[] args) {

    }
}
