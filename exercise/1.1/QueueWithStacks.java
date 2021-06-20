public class QueueWithStacks<Item> {
    Stack<Item> inbox = new Stack<Item>();
    Stack<Item> outbox = new Stack<Item>();

    public boolean isEmpty() {
        return inbox.isEmpty() && outbox.isEmpty();
    }

    public int size() {
        return inbox.size() + outbox.size();
    }

    public void enqueue(Item item) {
        inbox.push(item);
    }

    public Item dequeue() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) outbox.push(inbox.pop());
        }
        return outbox.pop();
    }


    public static void main(String[] args) {

    }
}
