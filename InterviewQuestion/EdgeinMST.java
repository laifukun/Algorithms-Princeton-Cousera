/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class EdgeinMST {
    private double[] distTo;

    EdgeinMST(EdgeWeightedGraph G, Edge e) {
        distTo = new double[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        
    }

    public static void main(String[] args) {

    }
}
