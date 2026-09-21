public class StudentHashTable {

    private static class HashNode {
        Student data;
        HashNode next;
        HashNode(Student data) { this.data = data; }
    }

    private final int capacity;
    private final HashNode[] table;
    private int count;

    public StudentHashTable() { this(53); } 

    public StudentHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new HashNode[capacity];
    }

    private int hash(String id) {
        int hash = 7;
        for (char c : id.toCharArray()) {
            hash = (hash * 31 + c) % capacity;
        }
        return Math.abs(hash);
    }

    public boolean insert(Student s) {
        if (search(s.getStudentId()) != null) return false;
        int idx = hash(s.getStudentId());
        HashNode newNode = new HashNode(s);
        newNode.next = table[idx];
        table[idx] = newNode;
        count++;
        return true;
    }

    public Student search(String id) {
        int idx = hash(id);
        HashNode cur = table[idx];
        while (cur != null) {
            if (cur.data.getStudentId().equalsIgnoreCase(id)) return cur.data;
            cur = cur.next;
        }
        return null;
    }

    public boolean remove(String id) {
        int idx = hash(id);
        HashNode cur = table[idx], prev = null;
        while (cur != null) {
            if (cur.data.getStudentId().equalsIgnoreCase(id)) {
                if (prev == null) table[idx] = cur.next;
                else prev.next = cur.next;
                count--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public int size() { return count; }
}

