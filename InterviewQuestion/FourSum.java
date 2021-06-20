/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class FourSum {
    Node[] twoSum;

    private class Node {
        private int sum;
        private int i;
        private int j;
        private Node next;

        Node(int sum, int i, int j, Node n) {
            this.sum = sum;
            this.i = i;
            this.j = j;
            this.next = n;
        }
    }

    FourSum(int[] a) {
        int n = a.length;
        twoSum = new Node[n];

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                int sum2 = a[i] + a[j];
                int k = sum2 % n;
                check(k, sum2, a[i], a[j]);
            }


    }

    private void check(int k, int sum2, int i, int j) {
        if (twoSum[k] == null) {
            twoSum[k] = new Node(sum2, i, j, null);
            return;
        }
        Node node = twoSum[k];
        while (node != null) {
            if (node.sum == sum2)
                StdOut.printf("%8d: %8d %8d, %8d %8d\n", sum2, node.i, node.j, i, j);
            node = node.next;
        }
        node = twoSum[k];
        twoSum[k] = new Node(sum2, i, j, node);
    }


    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(100 * n);
        FourSum sum4 = new FourSum(a);
    }
}
