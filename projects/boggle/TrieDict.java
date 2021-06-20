/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class TrieDict {
    private static int nR = 26;  // radix
    private Node root;
    private int n;

    public TrieDict() {
    }

    class Node {
        boolean val = false;
        private Node[] next = new Node[nR];
    }

    public Node root() {
        return root;
    }


    public boolean get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return false;
        return x.val;
    }

    public boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key);
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c - 'A'], key, d + 1);
    }

    public void put(String key, int val) {
        // if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        // if (val == null) delete(key);
        // else
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, int val, int d) {
        if (x == null) x = new Node();
        if (d == val) {
            if (!x.val) n++;
            x.val = true;
            return x;
        }
        int c = key.charAt(d) - 'A';
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isPrefix(String pre) {
        /*
        Node x = get(root, pre, 0);
        if (x == null) return false;
        return true;

         */

        Node x = root;
        int i = 0;
        int nS = pre.length();
        while (x != null) {
            int c = pre.charAt(i++) - 'A';
            if (x.next[c] == null) return false;
            x = x.next[c];
            if (i == nS) return true;
        }
        return true;
    }

    public Node prefixCheck(Node x, char c) {
        int ci = c - 'A';
        if (x.next[ci] == null) return null;
        else return x.next[ci];
    }

    //   public Node i

    /*
        public Iterable<String> keysWithPrefix(String prefix) {
            Queue<String> results = new Queue<String>();
            Node x = get(root, prefix, 0);
            collect(x, new StringBuilder(prefix), results);
            return results;
        }

        private void collect(Node x, String pre, Queue<String> q) {
            if (x == null) return;
            if (x.val != null) q.enqueue(pre);
            for (char c = 0; c < R; c++)
                collect(x.next[c - 'A'], pre + c, q);
        }
    */
    /*
    public void delete(String key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        root = delete(root, key, 0);
    }

     */

    /**
     * delete element from the tree
     *
     * @param x
     * @param key
     * @param d
     * @return
     */
/*
    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.val != null) n--;
            x.val = null;
        }
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) return x;
        for (int c = 0; c < nR; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }

 */

    /**
     * Unit test
     *
     * @param args
     */

    public static void main(String[] args) {
        TrieDict tdict = new TrieDict();
        tdict.put("xxx", 3);
    }
}
