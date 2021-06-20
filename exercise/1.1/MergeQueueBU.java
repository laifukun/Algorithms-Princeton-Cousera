public class MergeQueueBU {

    public static <Item> Queue<Queue<Item>> sort(Item[] a) {
        Queue<Queue<Item>> sortedQue = new Queue<Queue<Item>>();
        for (int i = 0; i < a.length; i++) {
            Queue<Item> itemQue = new Queue<Item>();
            itemQue.enqueue(a[i]);
            sortedQue.enqueue(itemQue);
        }
        while (sortedQue.size() > 1) {
            sortedQue.enqueue(merge(sortedQue.dequeue(), sortedQue.dequeue()));
        }
        return sortedQue;
    }

    public static <Item> Queue<Item> merge(Queue<Item> q1, Queue<Item> q2) {

        // merge two sorted queue
        Queue<Item> output = new Queue<Item>();

        while (!q1.isEmpty() || !q2.isEmpty()) {
            if (q1.isEmpty()) output.enqueue(q2.dequeue());
            else if (q2.isEmpty()) output.enqueue(q1.dequeue());
            else {
                Item v1 = q1.peek();
                Item v2 = q2.peek();
                if (less((Comparable) v1, (Comparable) v2)) {
                    output.enqueue(q1.dequeue());
                }
                else {
                    output.enqueue(q2.dequeue());
                }
            }
        }
        return output;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        Double[] a = new Double[N];

        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();

        }

        Queue<Queue<Double>> q3 = sort(a);

        for (double x : q3.peek())
            StdOut.print(x + " ");

    }
}
