public class FixedCapacityStackofStrings {

    private String[] s; // stack entries
    private int N;  // size

    public FixedCapacityStackofStrings(int cap) {
        s = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean isFull() {
        return N == s.length;
    }

    public int size() {
        return N;
    }

    public void push(String a) {
        s[N++] = a;
    }

    public String pop() {
        return s[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStackofStrings s = new FixedCapacityStackofStrings(5);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-") && !s.isFull())
                s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + "-");
            StdOut.println("(" + s.size() + " left on stack");
        }
    }

}

