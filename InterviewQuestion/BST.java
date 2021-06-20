/* *****************************************************************************
 *  Name: Fukun Lai
 *  Date: 08/24/2020
 *  Description: A generic Bineary Tree
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class BST<Key extends Comparable<Key>, Value> {
    private BSTNode root;

    private class BSTNode {
        private Key key;
        private Value val;
        private BSTNode left, right; // left and right children
        private int size;            // size of subtree (not include self)

        BSTNode(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    BST() {
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
        if (node == null) return new BSTNode(key, val, 0);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(key, val, node.left);
        else if (cmp > 0) node.right = put(key, val, node.right);
        else node.val = val;
        node.size = 1 + size(node.left) + size(node.right);
        return node;
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
        BST<String, Integer> bst = new BST<String, Integer>();
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
    }
}
