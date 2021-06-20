/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private BSTNode root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class BSTNode {
        private Key key;
        private Value val;
        private BSTNode left, right; // left and right children
        private int size;            // size of subtree (not include self)
        private boolean color;

        BSTNode(Key key, Value val, int size, boolean color) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.color = color;
        }
    }

    RedBlackBST() {
        root = null;
    }

    public int size() {
        return size(root);
    }

    private int size(BSTNode node) {
        if (node == null) return 0;
        return node.size;
    }

    public Value get(Key key) {
        return get(key, root);
    }

    private Value get(Key key, BSTNode node) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(key, node.left);
        if (cmp > 0) return get(key, node.right);
        else return node.val;
    }

    public void put(Key key, Value val) {
        root = put(key, val, root);
    }

    private BSTNode put(Key key, Value val, BSTNode node) {
        if (node == null) return new BSTNode(key, val, 1, RED);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(key, val, node.left);
        else if (cmp > 0) node.right = put(key, val, node.right);
        else node.val = val;

        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private boolean isRed(BSTNode node) {
        if (node == null) return false;
        return node.color;
    }

    private BSTNode rotateLeft(BSTNode node) {
        assert (node.right.color == RED);
        BSTNode nR = node.right;
        node.right = nR.left;
        nR.left = node;
        nR.color = node.color;
        node.color = RED;
        nR.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return nR;
    }

    private BSTNode rotateRight(BSTNode node) {
        assert (node.left.color == RED);
        BSTNode nLeft = node.left;
        node.left = nLeft.right;
        nLeft.right = node;
        nLeft.color = node.color;
        nLeft.right.color = RED;
        nLeft.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return nLeft;
    }

    private void flipColors(BSTNode node) {
        assert (node.right.color == RED);
        assert (node.left.color == RED);
        assert (node.color != RED);

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
        // node.size = size(node.left) + size(node.right) + 1;
    }

    public Key min() {
        return min(root).key;
    }

    private BSTNode min(BSTNode node) {
        if (node.left != null) return min(node.left);
        else return node;
    }

    public Key max() {
        return max(root).key;
    }

    private BSTNode max(BSTNode node) {
        if (node.right != null) return max(node.right);
        return node;
    }

    public Key floor(Key key) {
        return floor(key, root).key;
    }

    private BSTNode floor(Key key, BSTNode node) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0) return floor(key, node.left);
        BSTNode t = floor(key, node.right);
        if (t != null) return t;
        else return node;

    }

    public Key ceiling(Key key) {
        return ceiling(key, root).key;
    }

    private BSTNode ceiling(Key key, BSTNode node) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp > 0) return ceiling(key, node.right);
        BSTNode t = ceiling(key, node.left);
        if (t != null) return t;
        else return node;
    }

    public Key select(int k) {
        return select(k, root);
    }

    private Key select(int k, BSTNode node) {
        if (node == null) return null;
        int n = size(node.left);
        if (n > k) return select(k, node.left);
        else if (n < k) return select(k - n - 1, node.right);
        else return node.key;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, BSTNode node) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(key, node.left);
        if (cmp > 0) return 1 + size(node.left) + rank(key, node.right);
        else return size(node.left);
    }

    public void delete(Key key) {
        root = delete(key, root);
    }

    private BSTNode delete(Key key, BSTNode x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(key, x.left);
        else if (cmp > 0) x.right = delete(key, x.right);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            BSTNode t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private BSTNode deleteMin(BSTNode node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }


    private BSTNode deleteMax(BSTNode node) {
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private void print(BSTNode node) {
        if (node == null) return;
        print(node.left);
        StdOut.print(node.key + " ");
        print(node.right);
        // StdOut.println();
    }

    public static void main(String[] args) {
        RedBlackBST<String, Integer> bst = new RedBlackBST<String, Integer>();
        bst.put("S", 1);
        bst.put("E", 2);
        bst.put("X", 3);
        bst.put("A", 4);
        bst.put("C", 5);
        bst.put("R", 6);
        bst.put("H", 7);
        bst.put("G", 8);
        bst.put("M", 9);

        bst.print(bst.root);
        StdOut.println();
        StdOut.println(bst.get("S"));
        StdOut.println(bst.floor("B"));
        StdOut.println(bst.ceiling("B"));
        StdOut.println(bst.select(4));
        StdOut.println(bst.rank("D"));
    }
}
