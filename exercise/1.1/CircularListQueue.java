import java.util.Iterator;

public class CircularListQueue<Item> implements Iterable<Item> {
    //private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    CircularListQueue() {
        last = null;
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        Node a = new Node();
        a.item = item;
        if (this.isEmpty()) {
            last = a;
            last.next = a;
        }
        else {
            Node first = last.next;
            a.next = first;
            last.next = a;
            last = a;
        }
        N++;
    }

    public Item dequeue() {
        if (N == 1) {
            Item a = last.item;
            last = null;
            --N;
            return a;
        }
        else if (N == 0) throw new RuntimeException();
        else {
            Item a = last.next.item;
            last.next = last.next.next;
            --N;
            return a;
        }

    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = last.next;

        public boolean hasNext() {
            return current != last;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
        }
    }

    public static void main(String[] args) {
        CircularListQueue<String> cq = new CircularListQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            cq.enqueue(item);
        }
        for (String a : cq)
            StdOut.print(a + " ");
        StdOut.println();

    }
}
