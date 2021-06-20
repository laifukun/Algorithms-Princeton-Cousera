import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

    // normal queue
    // first in first out FIFO
    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {


        Node newItem = new Node();
        newItem.item = item;

        if (isEmpty()) {
            first = newItem;
            last = newItem;

        }
        else {

            last.next = newItem;
            last = newItem;
        }

        N++;

    }

    public Item dequeue() {
        if (this.isEmpty()) throw new RuntimeException("Empty Queue!");
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Item peek() {
        if (this.isEmpty()) throw new RuntimeException("Empty Queue!");
        return first.item;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) throw new RuntimeException("No more Element!");
            Item item = current.item;
            current = current.next;
            return item;

        }
    }

    public static void main(String[] args) {
        Queue<Double> q1 = new Queue<Double>();
        Queue<Double> q2 = new Queue<Double>();

        for (int i = 0; i < 10; i++) {
            q1.enqueue(2.0 * i + 1);
            q2.enqueue(2.0 * i);
        }

        for (double x : q1)
            StdOut.print(x + " ");

    }
}
