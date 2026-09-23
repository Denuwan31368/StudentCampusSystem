import java.util.ArrayList;
import java.util.List;

public class StudentLinkedList {

    private static class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node head;
    private int size;

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    public boolean contains(String id) {
        return findNode(id) != null;
    }

    private Node findNode(String id) {
        Node cur = head;
        while (cur != null) {
            if (cur.data.getStudentId().equalsIgnoreCase(id)) return cur;
            cur = cur.next;
        }
        return null;
    }

    public boolean addStudent(Student s) {
        if (contains(s.getStudentId())) return false;
        Node newNode = new Node(s);
        if (head == null) {
            head = newNode;
        } else {
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = newNode;
        }
        size++;
        return true;
    }

    public Student searchById(String id) {
        Node n = findNode(id);
        return n == null ? null : n.data;
    }

    public boolean updateStudent(String id, String name, String programme, double marks) {
        Node n = findNode(id);
        if (n == null) return false;
        n.data.setName(name);
        n.data.setProgramme(programme);
        n.data.setMarks(marks);
        return true;
    }

    public Student deleteStudent(String id) {
        Node cur = head, prev = null;
        while (cur != null) {
            if (cur.data.getStudentId().equalsIgnoreCase(id)) {
                if (prev == null) head = cur.next;
                else prev.next = cur.next;
                size--;
                return cur.data;
            }
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur.data);
            cur = cur.next;
        }
        return list;
    }

    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-10s %-20s %-15s %6s%n", "ID", "Name", "Programme", "Marks");
        System.out.println("----------------------------------------------------------");
        Node cur = head;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }
        System.out.println("----------------------------------------------------------");
    }
}
