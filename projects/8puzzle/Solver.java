/* *****************************************************************************
 *  Name: Fukun Lai
 *  Date: 08/23/2020
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Comparator;

public class Solver {
    // private final Board initBd;
    private final boolean solvable;
    private final int minMove;
    private final ArrayList<Board> solu;

    private class Node {
        Board board;
        int move;
        Node prev;
        int manhattanPriority;
        int hammingPriority;

        public Node(Board bd, int mv, Node pv) {
            board = bd;
            move = mv;
            prev = pv;
            manhattanPriority = board.manhattan() + mv;
            hammingPriority = board.hamming() + mv;
        }

        public Node(Board bd, int mv) {
            board = bd;
            move = mv;
            prev = null;
            manhattanPriority = board.manhattan() + mv;
            hammingPriority = board.hamming() + mv;
        }

        public Node(Board bd) {
            board = bd;
            move = 0;
            prev = null;
            manhattanPriority = board.manhattan();
            hammingPriority = board.hamming();
        }

        public int hammingPriority() {
            return hammingPriority;
        }

        public int manhattanPriority() {
            return manhattanPriority;
        }
        /*
        public int compareTo(Node that) {
            if (this.manhattanPriority() < that.manhattanPriority()) return -1;
            if (this.manhattanPriority() > that.manhattanPriority()) return 1;
            return 0;
        }

         */

    }

    private class HammingComparator implements Comparator<Node> {
        public int compare(Node nd1, Node nd2) {
            if (nd1.hammingPriority() < nd2.hammingPriority()) return -1;
            if (nd1.hammingPriority() > nd2.hammingPriority()) return 1;
            return 0;
        }
    }

    private class ManhattanComparator implements Comparator<Node> {
        public int compare(Node nd1, Node nd2) {
            if (nd1.manhattanPriority() < nd2.manhattanPriority()) return -1;
            if (nd1.manhattanPriority() > nd2.manhattanPriority()) return 1;
            return 0;
        }
    }


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("");
        // this.initBd = initial;
        MinPQ<Node> initPQ = new MinPQ<Node>(new ManhattanComparator());
        MinPQ<Node> swapPQ = new MinPQ<Node>(new ManhattanComparator());
        Node originNode = new Node(initial);
        Node swapNode = new Node(initial.twin());

        initPQ.insert(originNode);
        swapPQ.insert(swapNode);
        originNode = initPQ.delMin();
        swapNode = swapPQ.delMin();
        int mv = 0;
        while (!originNode.board.isGoal() && !swapNode.board.isGoal()) {
            mv = originNode.move + 1;
            for (Board bd : originNode.board.neighbors()) {
                if (originNode.prev == null || !bd.equals(originNode.prev.board))
                    initPQ.insert(new Node(bd, mv, originNode));
            }
            mv = swapNode.move + 1;
            for (Board bd : swapNode.board.neighbors()) {
                if (swapNode.prev == null || !bd.equals(swapNode.prev.board))
                    swapPQ.insert(new Node(bd, mv, swapNode));
            }
            originNode = initPQ.delMin();
            swapNode = swapPQ.delMin();
        }

        if (originNode.board.isGoal()) {
            //mv = 0;
            minMove = originNode.move;
            solvable = true;
            solu = new ArrayList<Board>(1);
            while (originNode != null) {
                ++mv;
                solu.add(0, originNode.board);
                originNode = originNode.prev;
            }
            //minMove = mv - 1;
        }
        else {
            solvable = false;
            minMove = -1;
            solu = null;
        }

        // StdOut.println(originNode.board);

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (solvable) return minMove;
        else return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!solvable) return null;

        return solu;
    }
    /*
    private boolean checkSolveable(Board initial) {
        int inversions = 0;
        int n = initial.dimension();
        for (int i = 0; i < initial.dimension(); i++)
            for (int j = 0; j < initial.dimension(); j++)
                if (i * n + j + 1 < initial.tileAt(i, j))
    }
    */


    // test client (see below)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);
        for (Board bd : solver.solution())
            StdOut.println(bd);
    }

}
