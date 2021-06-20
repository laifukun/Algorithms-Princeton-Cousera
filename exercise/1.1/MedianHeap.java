public class MedianHeap<Item extends Comparable<Item>> {
    MaxPQ<Item> maxHeap;
    MinPQ<Item> minHeap;

    MedianHeap(int maxCap) {
        maxHeap = new MaxPQ<Item>(maxCap);
        minHeap = new MinPQ<Item>(maxCap);
    }

    public void insert(Item item) {
        if (maxHeap.isEmpty()) maxHeap.insert(item);

        else if (minHeap.isEmpty()) minHeap.insert(item);

        else if (less(minHeap.getMin(), item)) minHeap.insert(item);
        else if (less(item, maxHeap.getMax())) maxHeap.insert(item);
        balance();

        StdOut.println("MaxHeap Size: " + maxHeap.size());
        StdOut.println("MinHeap Size: " + minHeap.size());
    }

    private void balance() {
        while (maxHeap.size() > minHeap.size() + 1) minHeap.insert(maxHeap.delMax());
        while (minHeap.size() > maxHeap.size() + 1) maxHeap.insert(minHeap.delMin());
    }

    public Item getMedian() {
        if (maxHeap.size() > minHeap.size()) return maxHeap.getMax();
        else return minHeap.getMin();
    }

    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public void print() {
        maxHeap.print();
        StdOut.println();
        minHeap.print();
    }

    public static void main(String[] args) {

        MedianHeap<Integer> mHeap = new MedianHeap<Integer>(100);

        mHeap.insert(12);
        mHeap.insert(13);
        mHeap.insert(10);
        mHeap.insert(2);
        mHeap.insert(15);
        mHeap.insert(18);
        mHeap.insert(25);
        mHeap.insert(82);
        mHeap.insert(16);
        mHeap.insert(38);
        mHeap.insert(1);
        mHeap.insert(50);
        mHeap.insert(100);
        mHeap.insert(56);

        StdOut.println(mHeap.getMedian());
        mHeap.print();


    }
}
