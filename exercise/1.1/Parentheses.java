public class Parentheses {
    public static void main(String[] args) {
        FixedCapacityStackofStrings s = new FixedCapacityStackofStrings(100);

        //String test = StdIn.readString();
        //StdOut.println(test);
        Boolean flag = true;

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("(") || item.equals("[") || item.equals("{") && !s.isFull())
                s.push(item);
            else if (item.equals(")") && !s.isEmpty() && !s.pop().equals("(")) {
                flag = false;
            }
            else if (item.equals("]") && !s.isEmpty() && !s.pop().equals("[")) {
                flag = false;
            }
            else if (item.equals("}") && !s.isEmpty() && !s.pop().equals("{")) {
                flag = false;
            }

        }
        if (s.isEmpty() && flag)
            StdOut.println(flag);
        else
            StdOut.println(false);


    }
}
