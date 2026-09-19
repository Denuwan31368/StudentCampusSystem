public class StudentBST {

    private static class TreeNode {
        Student data;
        TreeNode left, right;
        TreeNode(Student data) { this.data = data; }
    }

    private TreeNode root;

    public boolean insert(Student s) {
        if (search(s.getStudentId()) != null) return false;
        root = insertRec(root, s);
        return true;
    }

    private TreeNode insertRec(TreeNode node, Student s) {
        if (node == null) return new TreeNode(s);
        int cmp = s.getStudentId().compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) node.left = insertRec(node.left, s);
        else if (cmp > 0) node.right = insertRec(node.right, s);
        return node;
    }

    public Student search(String id) {
        TreeNode cur = root;
        while (cur != null) {
            int cmp = id.compareToIgnoreCase(cur.data.getStudentId());
            if (cmp == 0) return cur.data;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return null;
    }

    public boolean delete(String id) {
        if (search(id) == null) return false;
        root = deleteRec(root, id);
        return true;
    }

    private TreeNode deleteRec(TreeNode node, String id) {
        if (node == null) return null;
        int cmp = id.compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, id);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TreeNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getStudentId());
        }
        return node;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No records in the tree.");
            return;
        }
        System.out.println("---- Students sorted by ID (BST in-order traversal) ----");
        inOrderRec(root);
    }

    private void inOrderRec(TreeNode node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.println(node.data);
        inOrderRec(node.right);
    }
}

