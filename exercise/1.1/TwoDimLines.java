public class TwoDimLines {
    private int w;
    private int h;
    private int lineNo;

    TwoDimLines(int w, int h, double xmin, double xmax, double ymin, double ymax) {
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(xmin, xmax);
        StdDraw.setYscale(ymin, ymax);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(0, 0, w, 0);
        StdDraw.line(0, 0, 0, h);
    }

    public void addLine(double[] x, double[] y, boolean Marker) {
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.setPenRadius(0.005);

        for (int i = 1; i < x.length; i++)
            StdDraw.line(x[i - 1], y[i - 1], x[i], y[i]);

        if (Marker) {
            StdDraw.setPenRadius(0.01);
            for (int i = 0; i < x.length; i++)
                StdDraw.point(x[i], y[i]);
        }
        lineNo++;

    }

    public static void main(String[] args) {

    }
}
