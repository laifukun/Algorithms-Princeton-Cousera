/* *****************************************************************************
 *  Name: Fukun Lai
 *  Date: 08/26/2020
 *  Description: The {@code KdTree} class represents a KdTree that contains a
 *  set of points between (0, 1).
 *  The class supports usual operations include: put, get, contain, size,
 *  isEmpty, insert, etc. The critical operations that the class support is to
 *  find the points inside a given rectangle, range(), and the nearest point to
 *  a given point, nearest().
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.Color;
import java.util.ArrayList;

public class KdTree {
    /* KdTree class uses Binary Tree as data structure. A nested class {@code
    Node} as the binary tree node.
     */
    private Node root; // root of the binary tree.
    private int n;  // size of tree

    private class Node {
        private Point2D pt;  // point
        private Node left;   // left child
        private Node right;  // right child
        private RectHV rect; //axis rectangel associated with the current point

        /* Rect is the minimum rect so far that contains the current point only.
         */
        Node(Point2D p, RectHV rt) {
            this.pt = p;
            this.rect = rt;
        }

        Node() {
        }
    }

    public KdTree()                               // construct an empty set of points
    {
        root = null;
        n = 0;
    }

    public boolean isEmpty()                      // is the set empty?
    {
        return root == null;
    }

    public int size()                         // number of points in the set
    {
        return n;
    }

    /**
     * Insert the point
     *
     * @param p
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */

    public void insert(Point2D p)  // add the point to the set (if it is not already in the set)
    {
        if (p == null) throw new IllegalArgumentException("");
        // The associated rect for the first point is the defined rectangle
        if (root == null)
            root = insert(root, new RectHV(0.0, 0.0, 1.0, 1.0), p, 0);
        else
            root = insert(root, root.rect, p, 0);
    }

    /**
     * Insert a point from the current node, the possible rectangle associated
     * with the node is created before and passed in.
     *
     * @param node  current node
     * @param rect  rect associate with the next node
     * @param p     point
     * @param level current level
     * @return recursively return Node
     */

    private Node insert(Node node, RectHV rect, Point2D p, int level) {
        if (node == null) {
            n++;
            return new Node(p, rect);
        }
        if (node.pt.equals(p)) return node; // check if the point is already in
        RectHV rt;
        // check if this level should be partitioned by x-coordinate
        // or y-coordinate
        if (level % 2 == 0) {
            if (p.x() < node.pt.x()) {
                if (node.left == null)
                    rt = new RectHV(node.rect.xmin(), node.rect.ymin(), node.pt.x(),
                                    node.rect.ymax());
                else rt = node.left.rect;
                node.left = insert(node.left, rt, p, level + 1);
            }
            else {
                if (node.right == null)
                    rt = new RectHV(node.pt.x(), node.rect.ymin(), node.rect.xmax(),
                                    node.rect.ymax());
                else rt = node.right.rect;
                node.right = insert(node.right, rt, p, level + 1);
            }
        }
        else {
            if (p.y() < node.pt.y()) {
                if (node.left == null)
                    rt = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(),
                                    node.pt.y());
                else rt = node.left.rect;
                node.left = insert(node.left, rt, p, level + 1);
            }
            else {
                if (node.right == null)
                    rt = new RectHV(node.rect.xmin(), node.pt.y(), node.rect.xmax(),
                                    node.rect.ymax());
                else rt = node.right.rect;
                node.right = insert(node.right, rt, p, level + 1);
            }
        }
        return node;
    }

    /**
     * check if the set contain point p
     *
     * @param p
     * @return
     */

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if (p == null) throw new IllegalArgumentException("");
        // int level = 0;
        return contains(root, p, 0);
    }

    /**
     * Recursively go down to the tree and check wheter p is in
     *
     * @param node
     * @param p
     * @param level
     * @return
     */

    private boolean contains(Node node, Point2D p, int level) {
        if (node == null) return false;
        if (node.pt.equals(p)) return true;
        if (level % 2 == 0) {
            if (p.x() < node.pt.x()) return contains(node.left, p, level + 1);
            else return contains(node.right, p, level + 1);
        }
        else {
            if (p.y() < node.pt.y()) return contains(node.left, p, level + 1);
            else return contains(node.right, p, level + 1);
        }
        // return false;
    }

    public void draw()                        // draw all points to standard draw
    {
        draw(root, 0);
    }

    private void draw(Node node, int level) {
        if (node == null) return;

        if (level % 2 == 0) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(node.pt.x(), node.rect.ymin(), node.pt.x(), node.rect.ymax());
        }
        else {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(node.rect.xmin(), node.pt.y(), node.rect.xmax(), node.pt.y());
        }


        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(node.pt.x(), node.pt.y());
        // StdDraw.setPenRadius(0.001);
        draw(node.left, level + 1);
        draw(node.right, level + 1);
    }

    /**
     * Return all point that inside rect
     *
     * @param rect the given rectangle
     * @return
     */

    public Iterable<Point2D> range(RectHV rect)
    // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null) throw new IllegalArgumentException("");
        return range(rect, root);
    }

    /**
     * Recursively go down the tree
     *
     * @param rect
     * @param node
     * @return
     */

    private Iterable<Point2D> range(RectHV rect, Node node) {
        ArrayList<Point2D> ptIn = new ArrayList<Point2D>();
        range(ptIn, rect, node);
        return ptIn;
    }

    /**
     * If the rect associated with the point intersect with the given rectangle
     * go down to check the points that in the rect. Else no need to check the
     * points in the rect.
     *
     * @param list
     * @param rect
     * @param node
     */

    private void range(ArrayList<Point2D> list, RectHV rect, Node node) {
        if (node == null) return;
        if (rect.intersects(node.rect)) {
            if (rect.contains(node.pt)) list.add(node.pt);
            range(list, rect, node.left);
            range(list, rect, node.right);
        }

    }

    /**
     * Find the nearest neighbor of given point p
     *
     * @param p
     * @return
     */

    public Point2D nearest(Point2D p)
    // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p == null) throw new IllegalArgumentException("");
        // double minDist = Double.MAX_VALUE;
        if (isEmpty()) return null;
        Node minNode = root;
        minNode = nearest(p, root, minNode);
        // StdOut.println(minDist);
        return minNode.pt;
    }

    /**
     * Recursively go down to the tree to find the nearest point to a given point
     * Always check the rect that's closer to the given point firstly to save
     * running time.
     *
     * @param p
     * @param node
     * @param minNode
     * @return
     */

    private Node nearest(Point2D p, Node node, Node minNode) {
        if (node == null) return minNode;
        // StdOut.println(node.pt.x() + " " + node.pt.y());
        double minDist = minNode.pt.distanceSquaredTo(p);
        double rectDist = node.rect.distanceSquaredTo(p);
        if (rectDist < minDist) {
            double curDist = node.pt.distanceSquaredTo(p);
            if (curDist < minDist) minNode = node;
            double rtLeftDist = Double.POSITIVE_INFINITY;
            double rtRightDist = Double.POSITIVE_INFINITY;
            if (node.left != null)
                rtLeftDist = node.left.rect.distanceSquaredTo(p);
            if (node.right != null)
                rtRightDist = node.right.rect.distanceSquaredTo(p);
            if (rtLeftDist < rtRightDist) {
                minNode = nearest(p, node.left, minNode);
                minNode = nearest(p, node.right, minNode);
            }
            else {
                minNode = nearest(p, node.right, minNode);
                minNode = nearest(p, node.left, minNode);
            }
        }

        return minNode;
    }

    public static void main(String[] args)// unit testing of the methods (optional)
    {
        String filename = args[0];
        In in = new In(filename);

        KdTree kdtree = new KdTree();

        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);

        }
        kdtree.draw();

        //   kdtree.insert(new Point2D(.7, 0.2));
        //  kdtree.insert(new Point2D(.5, 0.4));
        StdOut.println(kdtree.size());
        StdOut.println(kdtree.contains(new Point2D(0.5, 0.5)));
        StdOut.println(kdtree.contains(new Point2D(0.2, 0.5)));
        double x = 0.78; // StdDraw.mouseX();
        double y = 0.34; // StdDraw.mouseY();
        Point2D query = new Point2D(x, y);

        StdDraw.setPenRadius(0.02);
        query.draw();
        StdDraw.setPenColor(StdDraw.BLUE);
        kdtree.nearest(query).draw();


    }
}
