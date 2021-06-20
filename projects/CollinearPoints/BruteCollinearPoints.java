import java.util.Arrays;

public class BruteCollinearPoints {

    // private Point[] points;
    private LineSegment[] lines;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
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
        Arrays.sort(thePoints);
        findSegments(thePoints);

    }

    public int numberOfSegments()        // the number of line segments
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
        Point[] temp = new Point[4 * points.length];
        int pn = -1;
        int n = 0;
        int ptlength = points.length;
        for (int p = 0; p < ptlength; p++) {
            Point P1 = points[p];
            for (int q = p + 1; q < ptlength; q++) {
                int lineIndicator = 0;
                for (int r = q + 1; r < ptlength; r++)
                    if (P1.slopeTo(points[q]) == P1.slopeTo(points[r])) {

                        for (int s = r + 1; s < ptlength; s++) {
                            if (P1.slopeTo(points[q]) == P1.slopeTo(points[s])) {
                                if (lineIndicator++ == 0) pn++;
                                temp[2 * pn] = P1;
                                temp[2 * pn + 1] = points[s];
                            }
                        }
                    }
            }
        }

        int p = 0;
        while (p <= 2 * pn && pn >= 0) {
            if (temp[p] == null) {
                p += 2;
                continue;
            }
            for (int q = p + 2; q <= 2 * pn; q += 2) {
                if (temp[q] != null && temp[p].slopeTo(temp[p + 1]) == temp[p]
                        .slopeTo(temp[q + 1]) && temp[p].slopeTo(temp[p + 1]) == temp[p]
                        .slopeTo(temp[q])) {
                    if (temp[q].compareTo(temp[p]) < 0) temp[p] = temp[q];
                    if (temp[q + 1].compareTo(temp[p + 1]) > 0) temp[p + 1] = temp[q + 1];
                    temp[q] = null;
                    temp[q + 1] = null;
                }
                if (temp[q] != null && temp[p].compareTo(temp[q]) == 0
                        && temp[p + 1].compareTo(temp[q + 1]) == 0) {
                    temp[q] = null;
                    temp[q + 1] = null;
                }
            }
            if (n >= lines.length) resize(2 * n);
            lines[n++] = new LineSegment(temp[p], temp[p + 1]);
            p += 2;
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

    public static void main(String[] args) {

    }
}
