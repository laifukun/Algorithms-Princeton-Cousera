import java.util.Iterator;

public class LinkedList<Item extends Comparable<Item>> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int N;

    private static class Node<Item> {
        // nested class Node
        Item item;
        Node<Item> next;

        Node(Item x) {
            item = x;
            next = null;
        }

        Node(Item x, Node<Item> list) {
            item = x;
            next = list;
        }
    }

    LinkedList() {
        head = null;
        tail = null;
        N = 0;
    }

    public void delete(int k) {
        if (k < N) {
            if (k == 0) head = head.next;
            else {
                Node<Item> temp = head;

                for (int i = 0; i < k - 1; i++) temp = temp.next;
                temp.next = temp.next.next;
            }
            N--;
        }
        else throw new RuntimeException("Out of Range!");
    }

    public Item max() {
        if (head == null) throw new RuntimeException("Empty List!");
        else if (head.next == null) return head.item;
        Item maxkey = head.item;
        Node<Item> temp = head;
        while (temp.next != null) {
            temp = temp.next;
            if (maxkey.compareTo(temp.item) < 0) maxkey = temp.item;
        }
        return maxkey;

    }

    public void removeAfter(Item k) {
        if (head == null) throw new RuntimeException("Empty List!");
        Node<Item> previous = head;
        Iterator<Item> iterator = this.iterator();
        while (iterator.hasNext() && previous.next != null) {
            if (iterator.next().equals(k)) {
                previous.next = previous.next.next;
                N--;
                break;
            }
            previous = previous.next;
        }
    }

    public void remove(Item item) {

        // remove all nodes that has the value item from the list
        if (head == null) throw new RuntimeException("Empty List!");

        Node<Item> previous = head;

        while (previous.next != null) {
            if (head.item.equals(item)) {
                head = head.next;
                previous = head;
                N--;
            }
            else if (previous.next.item.equals(item)) {
                previous.next = previous.next.next;
                N--;
            }
            else
                previous = previous.next;


        }
    }


    public void add(Item x) {
        // append an item to the list
        if (head == null) {
            head = new Node<Item>(x);
            tail = head;

        }
        else {
            Node<Item> newNode = new Node<Item>(x);
            tail.next = newNode;
            tail = tail.next;
        }
        N++;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void addFist(Item a) {
        head = new Node<Item>(a, head);
        N++;
    }

    public void addLast(Item a) {
        if (!isEmpty()) {
            tail.next = new Node<Item>(a);
            tail = tail.next;
        }
        else {
            head = new Node<Item>(a);
            tail = head;
        }
        N++;
    }

    public void insertAfter(Item a, Item b) {

        if (head == null) throw new RuntimeException("Empty List!");

        Node<Item> present = head;
        Node<Item> x = new Node<Item>(b);

        while (present != null && !present.item.equals(a)) {
            present = present.next;
        }
        if (present != null) {
            x.next = present.next;
            present.next = x;
            N++;
        }
        else
            throw new RuntimeException("No Item " + a + " is found!");

    }

    public boolean find(Item item) {

        // find the first item in the list
        Iterator<Item> iterator = this.iterator();
        while (iterator.hasNext())
            if (iterator.next().equals(item)) return true;

        return false;
    }

    public static <Item extends Comparable<Item>> void printList(LinkedList<Item> list) {
        Iterator<Item> iterator = list.iterator();

        while (iterator.hasNext()) {
            StdOut.print(iterator.next() + " ");
        }
        StdOut.println();

    }

    public void naturalMerge() {

        Node<Item> p0 = this.head;
        Node<Item> pend1 = this.head;
        Node<Item> pend2 = this.head;

        while (pend2.next != null && pend2.item.compareTo(pend2.next.item) <= 0)
            pend2 = pend2.next;
        while (p0 != null) {

            pend1 = p0;
            pend2 = pend1;
            while (pend2.next != null && pend2.item.compareTo(pend2.next.item) <= 0)
                pend2 = pend2.next;
            p0 = pend2.next;
            head = merge(head, pend1, pend2);
        }
    }

    public <Item extends Comparable<Item>> Node<Item> merge(Node<Item> lo, Node<Item> mid,
                                                            Node<Item> hi) {

        Node<Item> hiNext = hi.next;
        Node<Item> midNext = mid;
        Node<Item> p0 = lo;
        Node<Item> p1 = mid;

        Node<Item> newlow = null;
        if ((p0.item).compareTo(p1.item) < 0) {
            newlow = p0;
            p0 = p0.next;
        }
        else {
            newlow = p1;
            p1 = p1.next;
        }
        Node<Item> iter = newlow;

        while (p0 != midNext || p1 != hiNext) {
            if (p0 == midNext) {
                iter.next = p1;
                p1 = p1.next;
            }
            else if (p1 == hiNext) {
                iter.next = p0;
                p0 = p0.next;
            }
            else if (p0.item.compareTo(p1.item) > 0) {
                iter.next = p1;
                p1 = p1.next;
            }
            else {
                iter.next = p0;
                p0 = p0.next;
            }
            iter = iter.next;
        }
        iter.next = hiNext;
        return newlow;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }


    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = head;

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
        LinkedList<Integer> list = new LinkedList<Integer>();
        //while(!StdIn.isEmpty()) {
        //int[] a = StdIn.readAllInts();
        //}
        int N = Integer.parseInt(args[0]);
        //int N = a.length;
        for (int i = 0; i < N; i++)
            list.add(StdRandom.uniform(0, N));
        //list.add(a[i]);
        LinkedList.printList(list);

        //StdOut.println(list.find(3));
        //StdOut.println(list.find(30));
        //list.delete(5);
        //list.remove(6);
        LinkedList.printList(list);
        //StdOut.println(list.max());
        list.naturalMerge();
        LinkedList.printList(list);

    }
}
