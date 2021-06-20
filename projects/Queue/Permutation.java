import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> test = new RandomizedQueue<String>();
        int i = 0;
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (i++ < k) test.enqueue(item);  // reservior sampling
            else if (k > 0 && StdRandom.uniform(0, i) < k) {
                test.dequeue();
                test.enqueue(item);
            }

        }
        if (k > 0) {
            for (String item : test) StdOut.println(item);
        }

    }
}
