public class PQTree<Item extends Comparable<Item>> {
    private Node root;

    private int N;

    private class Node {
        Item item;
        int size;
        Node lChild;
        Node rChild;
        Node parent;

        Node(Item a) {
            item = a;
            size = 1;
            lChild = null;
            rChild = null;
            parent = null;
        }
    }

    PQTree() {
        root = null;

        N = 0;
    }

    public Item delMax() {
        if (root == null) throw new RuntimeException("Empty MaxPQ!");
        Item max = root.item;
        Node lNode = lastNode(root);
        exch(root, lNode);
        Node lastNodeParent = lNode.parent;
        if (lastNodeParent.lChild == lNode) lastNodeParent.lChild = null;
        else lastNodeParent.rChild = null;
        while (lastNodeParent != null) {
            lastNodeParent.size--;
            lastNodeParent = lastNodeParent.parent;
        }
        sink(root);
        return max;
    }

    public void insert(Item item) {

        if (root == null) {
            root = new Node(item);
        }
        else {
            Node lastNode = insert(root, item);
            swim(lastNode);
        }

    }

    private Node insert(Node node, Item item) {
        node.size++;
        if (node.lChild == null) {
            node.lChild = new Node(item);
            node.lChild.parent = node;
            return node.lChild;
        }
        if (node.rChild == null) {
            node.rChild = new Node(item);
            node.rChild.parent = node;
            return node.rChild;
        }
        if (node.lChild.size <= node.rChild.size || node.lChild.size % 2 == 0) {
            return insert(node.lChild, item);
        }
        else {
            return insert(node.rChild, item);
        }
    }

    protected void swim(Node k) {
        //Node<Item> temp = k;
        while (k != root && less(k.parent.item, k.item)) {
            exch(k, k.parent);
            k = k.parent;
        }
    }

    protected void sink(Node k) {
        if (k == null) return;
        while (k.lChild != null && k.rChild != null) {
            if (less(k.lChild.item, k.item) && less(k.rChild.item, k.item)) return;
            else if (less(k.lChild.item, k.rChild.item)) {
                exch(k, k.rChild);
                k = k.rChild;
            }
            else {
                exch(k, k.lChild);
                k = k.lChild;
            }
        }

        if (k.lChild != null && less(k.item, k.lChild.item)) exch(k, k.lChild);
        if (k.rChild != null && less(k.item, k.rChild.item)) exch(k, k.rChild);
    }

    private Node lastNode(Node cur) {
        if (cur == null) return null;
        if (cur.lChild == null && cur.rChild == null) return cur;
        if (nodeSize(cur.lChild) > nodeSize(cur.rChild)) return lastNode(cur.lChild);
        else return lastNode(cur.rChild);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return N;
    }

    private int nodeSize(Node node) {
        if (node == null) return 0;
        return node.size;
    }


    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected void exch(Node p, Node q) {
        Item temp = q.item;
        q.item = p.item;
        p.item = temp;

    }

    private void printPQ(Node node) {
        if (node == null) return;
        StdOut.print(node.item + " ");
        printPQ(node.lChild);
        printPQ(node.rChild);
    }

    public void print() {
        printPQ(root);
    }


    public static void main(String[] args) {
        PQTree<String> testPQ = new PQTree<String>();
        testPQ.insert("P");
        testPQ.insert("Q");
        testPQ.insert("E");
        testPQ.delMax();
        testPQ.insert("X");
        testPQ.insert("A");
        testPQ.insert("M");
        testPQ.delMax();
        testPQ.insert("P");
        testPQ.insert("L");
        testPQ.insert("E");
        testPQ.delMax();
        testPQ.print();

    }
}
