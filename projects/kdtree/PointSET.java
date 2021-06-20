/* *****************************************************************************
 *  Name: Fukun Lai
 *  Date: 08/26/2020
 *  Description: Brute force algorithm
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class PointSET {
    private final SET<Point2D> ptSet;

    public PointSET()                               // construct an empty set of points
    {
        ptSet = new SET<Point2D>();
    }

    public boolean isEmpty()                      // is the set empty?
    {
        return ptSet.isEmpty();
    }

    public int size()                         // number of points in the set
    {
        return ptSet.size();
    }

    public void insert(Point2D p)  // add the point to the set (if it is not already in the set)
    {
        if (p == null) throw new IllegalArgumentException("");
        ptSet.add(p);
    }

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if (p == null) throw new IllegalArgumentException("");
        return ptSet.contains(p);
    }

    public void draw()                         // draw all points to standard draw
    {
        for (Point2D pt : ptSet)
            pt.draw();
    }

    /**
     * return all points in a given rectangle
     *
     * @param rect
     * @return
     */

    public Iterable<Point2D> range(RectHV rect)
    // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null) throw new IllegalArgumentException("");
        ArrayList<Point2D> ptIn = new ArrayList<Point2D>();
        for (Point2D pt : ptSet) {
            if (rect.contains(pt)) ptIn.add(pt);
        }
        return ptIn;
    }

    /**
     * return the nearest point to the given point
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p)
    // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p == null) throw new IllegalArgumentException("");
        Point2D minPt = null;
        double minDist = Double.POSITIVE_INFINITY;
        for (Point2D pt : ptSet) {
            if (p.distanceSquaredTo(pt) < minDist) {
                minPt = pt;
                minDist = p.distanceSquaredTo(pt);
            }
        }
        return minPt;
    }

    public static void main(String[] args)// unit testing of the methods (optional)
    {
        String filename = args[0];
        In in = new In(filename);
        // PointSET brute = new PointSET();
        PointSET pointset = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            pointset.insert(p);
            // brute.insert(p);
        }
        pointset.draw();

        double x = 0.2; // StdDraw.mouseX();
        double y = 0.5; // StdDraw.mouseY();
        Point2D query = new Point2D(x, y);

        StdDraw.setPenRadius(0.02);
        query.draw();
        StdDraw.setPenColor(StdDraw.BLUE);
        pointset.nearest(query).draw();


    }
}

