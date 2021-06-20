/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int p) {
        if (p == v) return w;
        else if (p == w) return v;
        else throw new IllegalArgumentException();
    }

    public int compareTo(Edge that) {
        if (this.weight < that.weight) return -1;
        else if (this.weight > that.weight) return 1;
        else return 0;
    }

    public String toString() {
        return "v - w: " + weight;
    }

    public static void main(String[] args) {

    }
}
