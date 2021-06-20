/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BSTCheck {
    Stack<Node> saveNode;   // a stack to save the right node of a tree

    public static boolean isBST(BST bst) {
        saveNode = new Stack<Node>();
        return checkTree(bst.root);
    }

    public static boolean checkTree(Node node) {
        // if the current node is null, and the stack is not empty, pop a node from the stack
        if (node == null && !saveNode.isEmpty()) {
            node = saveNode.pop();
            checkTree(node);
        }

        // if all the nodes are checked, return true
        if (node == null && saveNode.isEmpty()) return true;

        // if the current node satisfy bst, to check the left node and push the right node into stack
        if (node.key > node.left.key && node.key < node.right.key) {
            checkTree(node.left);
            saveNode.push(node.right);
        }
        else return false;

    }

    public static void inorderTraverse(BST bst) {
        inorderTraverse(bst.root);
    }

    public static void inorderTraverse(Node node) {
        if (node == null) return;
        inorderTraverse(node.left);
        StdOut.print(node);
        inorderTraverse(node.right);
    }

    public static void main(String[] args) {

    }
}
