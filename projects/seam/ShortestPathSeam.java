/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Picture;

public class ShortestPathSeam {
    private final double energy[][];
    private double distTo[][];
    private int edgeTo[][];
    private final int width;
    private final int height;

    ShortestPathSeam(Picture pic) {
        width = pic.width();
        height = pic.height();
        energy = new double[height][width];
        edgeTo = new int[height][width];
        distTo = new double[height][width];
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                energy[y][x] = pic.energy(x, y);

    }

    public static void main(String[] args) {

    }
}
