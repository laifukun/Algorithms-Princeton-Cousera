import java.util.Arrays;

public class FastCollinearPoints {
    // private Point[] points;
    private LineSegment[] lines;

    public FastCollinearPoints(Point[] points)
    // finds all line segments containing 4 or more points
    {
        if (points == null) throw new IllegalArgumentException(" ");
        // if (points.length < 4) throw new IllegalArgumentException(" ");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException(" ");
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null) throw new IllegalArgumentException(" ");
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException(" ");
            }

        }
        Point[] thePoints = new Point[points.length];
        for (int i = 0; i < points.length; i++)
            thePoints[i] = points[i];
        findSegments(thePoints);
    }

    public int numberOfSegments()        // the number of line segments {
    {
        return lines.length;
    }

    public LineSegment[] segments()                // the line segments
    {
        LineSegment[] linecopy = new LineSegment[lines.length];
        for (int i = 0; i < lines.length; i++)
            linecopy[i] = lines[i];
        return linecopy;
    }

    private void findSegments(Point[] points) {
        lines = new LineSegment[points.length];
        int pn = -1;
        int n = 0;
        int ptlength = points.length;
        Arrays.sort(points);
        // sort the points in natural order
        for (int p = 0; p < ptlength; p++) {
            Point P1 = points[p];
            Point[] temp = new Point[points.length];
            for (int j = 0; j < ptlength; j++)
                temp[j] = points[j];
            Arrays.sort(temp, P1.slopeOrder()); // sort the points wrt slope of P1
            int j = 0;
            while (j + 2 < temp.length) {
                int q = j;
                while (++q < temp.length && P1.slopeTo(temp[j]) == P1.slopeTo(temp[q])) ;
                // scan to find the same slope points, if consecutive 3 or more points have the same slopes,
                // and P1 is the first point, then save point P1 and last points.
                // If P1 is not the first point in natural order, that means this line has been saved before
                if (q - j >= 3 && P1.compareTo(temp[j]) < 0) {
                    if (n >= lines.length) resize(2 * n);
                    lines[n++] = new LineSegment(P1, temp[q - 1]);
                }
                j = q;
            }

        }
        resize(n);

    }

    private void resize(int n) {

        LineSegment[] newlines = new LineSegment[n];
        int minN = n < lines.length ? n : lines.length;
        for (int i = 0; i < minN; i++)
            newlines[i] = lines[i];
        lines = newlines;

    }
}
