/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class GeneralizedQueue<Item> {
    private RedBlackBST<Integer, Item> gq;
    private int first;
    private int last;

    GeneralizedQueue() {
        gq = new RedBlackBST<Integer, Item>();
        first = 0;
        last = 0;
    }

    public int size() {
        return gq.size();
    }

    public void append(Item item) {
        gq.put(last++, item);
    }

    public void removeFront() {
        gq.delete(first++);
    }

    public Item get(int i) {
        int key = first + i;
        return gq.get(key);
    }

    public void remove(int i) {
        int key = first + i;
        gq.delete(key);
    }

    private void print() {
        for (int i = first; i < last; i++) {
            if (gq.get(i) != null)
                StdOut.print(gq.get(i) + " ");
        }
        StdOut.println();
    }


    public static void main(String[] args) {
        GeneralizedQueue<String> bst = new GeneralizedQueue<String>();
        bst.append("S");
        bst.append("E");
        bst.append("X");
        bst.append("A");
        bst.append("C");
        bst.append("R");
        bst.append("H");
        bst.append("G");
        bst.append("M");

        bst.print();
        bst.remove(3);
        bst.print();
        bst.removeFront();
        bst.print();


    }
}
