public class VisualCounter {
    private int count;
    private int oper;
    private final String id;
    private final int maxOp;
    private final int maxAbsCount;

    public VisualCounter(String name, int N, int max) {
        id = name;
        maxOp = N;
        maxAbsCount = max;
    }

    public void increment() {
        if (oper < maxOp && Math.abs(count) < maxAbsCount) {
            count++;
            draw(count);
        }
    }

    public void decrement() {
        if (oper < maxOp && Math.abs(count) < maxAbsCount) {
            count--;
            draw(count);
        }
    }

    public static void draw(int count) {
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, 500);
        StdDraw.setYscale(0, 50);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.001);

        //for (int i = 0; i < N; i++) {

        double rw = 100.0;
        double x = 500.0 / 2;
        double y = count;

        double rh = count;
        StdDraw.filledRectangle(x, y, rw, rh);
        //}
    }

    public int tally() {
        return count;
    }

    public String toString() {
        return count + " " + id;
    }

    public static void main(String[] args) {
        VisualCounter vc = new VisualCounter("head", 100, 60);
        while (!StdIn.isEmpty()) {
            int c = StdIn.readInt();
            if (c > 0) vc.increment();
            else vc.decrement();

        }


    }
}
