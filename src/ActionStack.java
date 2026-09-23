public class ActionStack {

    private static class Node {
        String action;
        Node next;
        Node(String action) { this.action = action; }
    }

    private Node top;
    private int size;

    public void push(String action) {
        Node n = new Node(action);
        n.next = top;
        top = n;
        size++;
    }

    public String pop() {
        if (top == null) return null;
        String action = top.action;
        top = top.next;
        size--;
        return action;
    }

    public boolean isEmpty() { return top == null; }
    public int size() { return size; }

    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No recent actions recorded.");
            return;
        }
        System.out.println("---- Recent Actions (most recent first) ----");
        Node cur = top;
        int i = 1;
        while (cur != null) {
            System.out.println(i++ + ". " + cur.action);
            cur = cur.next;
        }
    }
}

