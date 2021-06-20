import java.util.Iterator;

public class DoubleNode<Item> implements Iterable<Item> {
    private Item item;
    private Node head;
    private Node tail;
    private int N;

    private class Node {
        Item item;
        Node next;
        Node preced;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return N;
    }

    public void insertBegin(Item a) {
        Node node = new Node();
        node.item = a;
        if (isEmpty()) {
            head = node;
            node.next = null;
            node.preced = null;
            tail = node;
        }
        else {
            Node first = head;
            head = node;
            head.next = first;
            first.preced = head;
            head.preced = null;
        }
        N++;

    }

    public Node find(Item a) {
        if (isEmpty()) return null;
        else {
            //ListIterator<Item> iterator = this.iterator();
            //while (iterator.hasNext()) {
            // if (iterator.next().equals(a)) return
            Node current = head;
            while (current != null)
                if (current.item.equals(a)) return current;
                else current = current.next;
        }
        return null;
    }

    public void insertBefore(Item a, Item b) {
        Node newNode = new Node();
        newNode.item = a;
        Node exist = this.find(b);

        if (exist != null && exist != head) {
            newNode.next = exist;
            newNode.preced = exist.preced;
            exist.preced.next = newNode;
            exist.preced = newNode;
            N++;
        }
        else if (exist == head) {
            this.insertBegin(a);
            N++;
        }

    }

    public void insertAfter(Item a, Item b) {
        Node newNode = new Node();
        newNode.item = a;
        Node exist = this.find(b);

        if (exist != null && exist != tail) {
            newNode.preced = exist;
            newNode.next = exist.next;
            exist.next.preced = newNode;
            exist.next = newNode;
            N++;
        }
        else if (exist == tail) {
            this.insertEnd(a);
            N++;
        }

    }

    public void insertEnd(Item a) {
        Node node = new Node();
        node.item = a;
        if (isEmpty()) {
            head = node;
            node.next = null;
            node.preced = null;
            tail = node;
        }
        else {
            Node end = tail;
            tail = node;
            tail.next = null;
            tail.preced = end;
            end.next = tail;
        }
        N++;
    }

    public void removeBegin() {
        if (!isEmpty() && N > 1) {
            head = head.next;
            head.preced = null;
        }
        else {
            head = null;
            tail = null;
        }
        N--;
    }

    public void remove(Item a) {
        // remove a given node
        //Node newNode = new Node();
        //newNode.item = a;
        Node exist = this.find(a);

        if (exist != null && exist != tail && exist != head) {
            exist.next.preced = exist.preced;
            exist.preced.next = exist.next;
            N--;
        }
        else if (exist != null && exist == tail) this.removeEnd();
        else if (exist != null && exist == head) this.removeBegin();

    }

    public void removeEnd() {
        if (!isEmpty() && N > 1) {
            tail = tail.preced;
            tail.next = null;
        }
        else {
            head = null;
            tail = null;
        }
        N--;
    }

    public ListIterator iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public void remove() {
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item a = current.item;
            current = current.next;
            return a;
        }
    }

    public static void main(String[] args) {
        DoubleNode<String> cq = new DoubleNode<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            cq.insertEnd(item);
        }
        cq.remove("to");
        for (String a : cq)
            StdOut.print(a + " ");
        StdOut.println();
    }
}
