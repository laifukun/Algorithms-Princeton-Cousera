import java.util.Iterator;
import java.util.NoSuchElementException;

public class Steque<Item> implements Iterable<Item> {

    private Node top;
    private Node bottom;
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

    public Item pop() {
        // pop item from the top of stack
        if (isEmpty()) throw new RuntimeException("Empty Stack!");
        Item item = top.item;
        top = top.next;
        top.next = null;
        return item;

    }

    public void push(Item item) {

        // push item to the top of stack
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            top = newNode;
            bottom = newNode;
        }
        else {
            newNode.next = top;
            top = newNode;
        }
        N++;

    }

    public Item peek() {
        if (isEmpty()) throw new RuntimeException("Empty Stack!");
        return top.item;
    }

    public void enqueue(Item item) {
        // enqueue item to the bottom of stack
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            top = newNode;
            bottom = newNode;
        }
        else {
            bottom.next = newNode;
            bottom = newNode;
        }
        N++;
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

    private class ListIterator implements Iterator<Item> {
        private Node current = top;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) {
        Steque<String> cq = new Steque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            cq.push(item);
        }
        cq.enqueue("OK");
        StdOut.println(cq);

    }
}
