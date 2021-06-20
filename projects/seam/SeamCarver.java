/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private static final boolean ORIGINAL = true;
    private static final boolean TRANSPOSED = false;
    private int pic[][];
    private boolean state;
    private int width;
    private int height;
    private double[][] energy;
    //  private int[][] edgeTo;
    //   private double[][] distTo;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {

        if (picture == null) throw new IllegalArgumentException();
        // pic = new Picture(picture);
        width = picture.width();
        height = picture.height();
        pic = new int[height][width];
        state = ORIGINAL;

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                pic[i][j] = picture.getRGB(j, i);

        energy = new double[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                energy[y][x] = energy(x, y);
        //    edgeTo = new int[pic.height()][pic.width()];
        //    distTo = new double[pic.height()][pic.width()];
    }


    // current picture
    public Picture picture() {

        Picture picture = new Picture(width, height);
        for (int y = 0; y < picture.height(); y++)
            for (int x = 0; x < picture.width(); x++) {
                picture.setRGB(x, y, pic[y][x]);
            }
        return picture;
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        // StdOut.println("width: " + width + " height: " + height);
        if (x < 0 || x > width - 1) throw new IllegalArgumentException();
        if (y < 0 || y > height - 1) throw new IllegalArgumentException();
        if (x == 0 || x == width - 1) return 1000;
        if (y == 0 || y == height - 1) return 1000;

        int[] right = getRGB(x + 1, y);
        int[] left = getRGB(x - 1, y);
        int[] up = getRGB(x, y + 1);
        int[] down = getRGB(x, y - 1);
        double ener = 0.0;
        for (int i = 0; i < 3; i++) {
            ener += (right[i] - left[i]) * (right[i] - left[i]);
        }
        for (int i = 0; i < 3; i++) {
            ener += (up[i] - down[i]) * (up[i] - down[i]);
        }

        return Math.sqrt(ener);
    }

    private int[] getRGB(int x, int y) {
        // int[] rgb = new int[3];
        int c = pic[y][x];
        return new int[] { (c >> 16) & 0xFF, (c >> 8) & 0xFF, c & 0xFF };
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        // if (state == ORIGINAL) tranpose();
        state = TRANSPOSED;
        int[] seam = findShortestPath();
        state = ORIGINAL;
        return seam;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        if (state == TRANSPOSED) state = ORIGINAL;
        return findShortestPath();
    }

    private double[][] getEnergy() {

        if (state == ORIGINAL) {
            double[][] picEnergy = new double[height][width];
            for (int x = 0; x < width; x++)
                for (int y = 0; y < height; y++)
                    picEnergy[y][x] = energy[y][x];
            return picEnergy;
        }
        else {
            double[][] picEnergy = new double[width][height];
            for (int x = 0; x < width; x++)
                for (int y = 0; y < height; y++)
                    picEnergy[x][y] = energy[y][x];
            return picEnergy;
        }

    }

    private int[] findShortestPath() {
        double[][] ener = getEnergy();
        int wth = ener[0].length;
        int ht = ener.length;
        int[][] edgeTo = new int[ht][wth];
        double[][] distTo = new double[ht][wth];


        for (int x = 0; x < wth; x++) {
            distTo[0][x] = 1000;
            for (int y = 1; y < ht; y++)
                distTo[y][x] = Double.POSITIVE_INFINITY;
        }
        double minDist = Double.POSITIVE_INFINITY;
        int minCol = 0;
        for (int y = 0; y < ht; y++)
            for (int x = 0; x < wth; x++) {
                relax(x, y, ener, distTo, edgeTo);
                if (y == ht - 1 && distTo[y][x] < minDist) {
                    minDist = distTo[y][x];
                    minCol = x;
                }
            }
        int[] path = new int[ht];
        int x = minCol;
        for (int y = ht - 2; y >= 0; x = edgeTo[y + 1][x], --y)
            path[y] = edgeTo[y + 1][x];
        path[ht - 1] = minCol;
        return path;
    }

    // pixel at column x and row y
    private void relax(int x, int y, double[][] ener, double[][] distTo, int[][] edgeTo) {
        int wth = ener[0].length;
        int ht = ener.length;
        if (y >= ht - 1) return;
        if (distTo[y + 1][x] > distTo[y][x] + ener[y + 1][x]) {
            distTo[y + 1][x] = distTo[y][x] + ener[y + 1][x];
            edgeTo[y + 1][x] = x;
        }

        if (x + 1 <= wth - 1 && distTo[y + 1][x + 1] > distTo[y][x] + ener[y + 1][x
                + 1]) {
            distTo[y + 1][x + 1] = distTo[y][x] + ener[y + 1][x + 1];
            edgeTo[y + 1][x + 1] = x;
        }
        if (x - 1 >= 0 && distTo[y + 1][x - 1] > distTo[y][x] + ener[y + 1][x - 1]) {
            distTo[y + 1][x - 1] = distTo[y][x] + ener[y + 1][x - 1];
            edgeTo[y + 1][x - 1] = x;
        }

    }


    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (height <= 1) throw new IllegalArgumentException();
        if (seam == null) throw new IllegalArgumentException();
        if (seam.length != width) throw new IllegalArgumentException();
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] >= height || seam[i] < 0) throw new IllegalArgumentException();
            if (i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1) throw new IllegalArgumentException();
        }

        for (int x = 0; x < width; x++)
            for (int y = seam[x] + 1; y < height; y++) {
                pic[y - 1][x] = pic[y][x];
                energy[y - 1][x] = energy[y][x];
            }
        height--;
        for (int x = 0; x < width; x++) {
            int y = seam[x];

            updateEnergy(x, y - 1);
            updateEnergy(x, y);
            updateEnergy(x, y + 1);
            updateEnergy(x - 1, y - 1);
            updateEnergy(x - 1, y);
            updateEnergy(x - 1, y + 1);
            updateEnergy(x + 1, y - 1);
            updateEnergy(x + 1, y);
            updateEnergy(x + 1, y + 1);

        }

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (width <= 1) throw new IllegalArgumentException();
        if (seam == null) throw new IllegalArgumentException();
        if (seam.length != height) throw new IllegalArgumentException();
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] >= width || seam[i] < 0) throw new IllegalArgumentException();
            if (i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1) throw new IllegalArgumentException();
        }
        // Picture picTemp = new Picture(pic.width() - 1, pic.height());


        for (int y = 0; y < height; y++) {
            System.arraycopy(pic[y], seam[y] + 1, pic[y], seam[y], width - seam[y] - 1);
            System.arraycopy(energy[y], seam[y] + 1, energy[y], seam[y], width - seam[y] - 1);
        }
        width--;

        for (int y = 0; y < height; y++) {
            int x = seam[y];

            updateEnergy(x, y - 1);
            updateEnergy(x, y);
            updateEnergy(x, y + 1);
            updateEnergy(x - 1, y - 1);
            updateEnergy(x - 1, y);
            updateEnergy(x - 1, y + 1);
            updateEnergy(x + 1, y - 1);
            updateEnergy(x + 1, y);
            updateEnergy(x + 1, y + 1);

        }

    }

    private void updateEnergy(int x, int y) {
        if (x < 0 || x > width - 1) return;
        if (y < 0 || y > height - 1) return;
        energy[y][x] = energy(x, y);
    }


    /*
        private void tranpose() {
            Picture picTemp = new Picture(pic.height(), pic.width());

            for (int y = 0; y < picTemp.height(); y++)
                for (int x = 0; x < picTemp.width(); x++)
                    picTemp.set(x, y, pic.get(y, x));
            pic = picTemp;
            if (state == ORIGINAL) state = TRANSPOSED;
            else state = ORIGINAL;
        }
    */
    //  unit testing (optional)
    public static void main(String[] args) {

    }

}
