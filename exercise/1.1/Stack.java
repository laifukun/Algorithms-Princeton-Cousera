import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

    private Node first;
    private int N;

    private class Node {
        // nested class Node
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;

    }


    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;

    }

    public Item peek() {
        if (N > 0)
            return first.item;
        return null;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

    }
}
