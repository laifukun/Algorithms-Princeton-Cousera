import java.util.Iterator;

public class ArithExpression {

    public static String[] missLeftParentheses(String[] expres) {
        Stack<String> pars = new Stack<String>();
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        Stack<String> postfix = new Stack<String>();

        for (int i = expres.length - 1; i >= 0; i--) {
            String s = expres[i];
            if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("(")) ;
            else if (s.equals(")")) {
                pars.push("(");
                //  postfix.push(s);
                //  postfix.push(vals.pop().toString());
                //   postfix.push(ops.pop());
                //  postfix.push(vals.pop().toString());
                //  postfix.push(pars.isEmpty() ? "(" : pars.pop());
            }
            else vals.push(Double.parseDouble(s));
        }
        String[] postfixStr = new String[postfix.size()];
        int i = 0;
        for (String s : postfix) {
            postfixStr[i++] = postfix.pop();
        }
        return postfixStr;

    }

    public static int operatorPriority(String ops) {
        switch (ops) {
            case "+":
            case "-":
                return 1;

            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    public static String[] InfixToPostfix(String[] infix) {
        Stack<String> ops = new Stack<String>();
        String str = new String("");

        for (int i = 0; i < infix.length; i++) {
            String s = infix[i];
            if (s.equals("+")) {
                while (!ops.isEmpty() && operatorPriority(ops.peek()) >= operatorPriority("+"))
                    str = str + ops.pop() + " ";
                ops.push(s);
            }
            else if (s.equals("-")) {
                while (!ops.isEmpty() && operatorPriority(ops.peek()) >= operatorPriority("-"))

                    str = str + ops.pop() + " ";
                ops.push(s);
            }
            else if (s.equals("*")) {
                while (!ops.isEmpty() && operatorPriority(ops.peek()) >= operatorPriority("*"))

                    str = str + ops.pop() + " ";
                ops.push(s);
            }
            else if (s.equals("/")) {
                while (!ops.isEmpty() && operatorPriority(ops.peek()) >= operatorPriority("/"))

                    str = str + ops.pop() + " ";
                ops.push(s);
            }
            else if (s.equals("(")) ops.push(s);
            else if (s.equals(")")) {
                while (!ops.peek().equals("("))
                    str = str + ops.pop() + " ";
                ops.pop();

            }
            else str = str + s + " ";
            //vals.push(Double.parseDouble(s));

        }
        while (!ops.isEmpty())
            str = str + ops.pop() + " ";
        return str.split(" ");
    }

    public static double EvaluatePostfix(String[] postfix) {

        Stack<Double> vals = new Stack<Double>();

        for (int i = 0; i < postfix.length; i++) {
            String s = postfix[i];
            if (s.equals("+")) {
                double v = vals.pop();
                v += vals.pop();
                vals.push(v);
            }
            else if (s.equals("-")) {
                double v = vals.pop();
                v = vals.pop() - v;
                vals.push(v);
            }
            else if (s.equals("*")) {
                double v = vals.pop();
                v *= vals.pop();
                vals.push(v);
            }
            else if (s.equals("/")) {
                double v = vals.pop();
                v = vals.pop() / v;
                vals.push(v);
            }
            else vals.push(Double.parseDouble(s));

        }
        return vals.pop();
    }

    public static Stack<String> copy(Stack<String> org) {
        // exercise 1.3.12
        Stack<String> neworg = new Stack<String>();

        for (Iterator<String> iterator = org.iterator(); iterator.hasNext(); ) {
            neworg.push(iterator.next());
        }
        return neworg;
    }

    public static void main(String[] args) {
        String input = StdIn.readLine();
        String[] infix = input.split(" ");
        String[] postfix = InfixToPostfix(infix);
        for (String s : postfix)
            StdOut.print(s);
        StdOut.println();
        StdOut.println(EvaluatePostfix(postfix));

        Stack<String> test1 = new Stack<String>();
        test1.push("first");
        test1.push("second");
        Stack<String> testcopy = copy(test1);
        StdOut.println(testcopy.pop());
        StdOut.println(testcopy.pop());
    }

}
