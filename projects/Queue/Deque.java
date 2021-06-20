import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

// import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item> {

    private Node first;  // first node
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node prev;
        Node next;

        Node(Item a) {
            item = a;
            prev = null;
            next = null;
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument!");
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            first.prev = newNode;
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("bad argument!");
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("No element!");
        Item a = first.item;
        if (size() == 1) {
            first = null;
            last = null;
            size--;
            return a;
        }
        first = first.next;
        first.prev = null;
        size--;
        return a;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("No element!");
        Item a = last.item;
        if (size() == 1) {
            first = null;
            last = null;
            size--;
            return a;
        }
        last = last.prev;
        last.next = null;
        size--;
        return a;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException("No element!");
            Item a = current.item;
            current = current.next;
            return a;
        }

        public void remove() {
            throw new UnsupportedOperationException("No such operation!");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> test = new Deque<Integer>();
        test.addFirst(1);
        test.addFirst(2);
        test.addLast(5);
        test.addLast(8);
        for (int i : test)
            StdOut.print(i + " ");
        StdOut.println();
        test.removeFirst();
        test.removeLast();
        test.removeLast();
        for (int i : test)
            StdOut.print(i + " ");
    }

}
