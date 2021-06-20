import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node left;
    private Node right;
    private int N;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    Deque() {
        left = null;
        right = null;
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void pushLeft(Item item) {

        //push item to the left of the list
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            left = newNode;
            right = newNode;
        }
        else {
            newNode.next = left;
            left.prev = newNode;
            left = newNode;
        }
        N++;
    }

    public void pushRight(Item item) {

        //push item to the right of the list
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            left = newNode;
            right = newNode;
        }
        else {
            newNode.prev = right;
            right.next = newNode;
            right = newNode;
        }
        N++;
    }

    public Item popLeft() {
        // pop element from left of the list
        if (isEmpty()) throw new RuntimeException("Empty Deque!");
        Item item = left.item;
        left = left.next;
        left.prev = null;
        return item;
    }

    public Item popRight() {
        // pop element from left of the list
        if (isEmpty()) throw new RuntimeException("Empty Deque!");
        Item item = right.item;
        right = right.prev;
        right.next = null;
        return item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item i : this)
            s.append(i + " ");
        return s.toString();
    }

    public ListIterator iterator() {
        return new ListIterator();
    }

    public class ListIterator implements Iterator<Item> {
        private Node current = left;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> cq = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            cq.pushRight(item);
        }
        for (String a : cq)
            StdOut.print(a + " ");
        StdOut.println();

    }
}
