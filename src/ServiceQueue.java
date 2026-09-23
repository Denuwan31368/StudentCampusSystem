public class ServiceQueue {

    private static class Node {
        String studentId;
        String requestDetails;
        Node next;
        Node(String studentId, String requestDetails) {
            this.studentId = studentId;
            this.requestDetails = requestDetails;
        }
    }

    private Node front, rear;
    private int size;

    public void enqueue(String studentId, String requestDetails) {
        Node n = new Node(studentId, requestDetails);
        if (rear == null) {
            front = rear = n;
        } else {
            rear.next = n;
            rear = n;
        }
        size++;
    }

    
    public String[] dequeue() {
        if (front == null) return null;
        String[] result = { front.studentId, front.requestDetails };
        front = front.next;
        if (front == null) rear = null;
        size--;
        return result;
    }

    public boolean isEmpty() { return front == null; }
    public int size() { return size; }

    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("---- Pending Service Requests (front to rear) ----");
        Node cur = front;
        int i = 1;
        while (cur != null) {
            System.out.println(i++ + ". [" + cur.studentId + "] " + cur.requestDetails);
            cur = cur.next;
        }
    }
}

