public class Josephus {

    // Josephus problem 1.3.37
    private int N;
    private int M;

    Josephus(int n, int m) {
        N = n;
        M = m;
    }

    public void eliminateSeq() {
        CircularListQueue<Integer> posQue = new CircularListQueue<Integer>();
        for (int i = 0; i < N; i++) {
            posQue.enqueue(i);
        }
        while (!posQue.isEmpty()) {
            for (int i = 0; i < M; i++) {
                if (i == M - 1) StdOut.print(posQue.dequeue() + " ");
                else posQue.enqueue(posQue.dequeue());
            }
        }
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);

        Josephus jp = new Josephus(N, M);
        jp.eliminateSeq();

    }
}
