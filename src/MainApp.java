import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static final Scanner sc = new Scanner(System.in);
    private static final StudentLinkedList studentList = new StudentLinkedList(); 
    private static final ActionStack actionStack = new ActionStack();             
    private static final ServiceQueue serviceQueue = new ServiceQueue();          
    private static final StudentBST bst = new StudentBST();                      
    private static final StudentHashTable hashTable = new StudentHashTable();    
    private static final CampusGraph graph = new CampusGraph();                  

    public static void main(String[] args) {
        int choice;
        do {
            clearScreen();
            printMenu();
            choice = readInt("Enter your choice: ");
            System.out.println();
            switch (choice) {
            
                case 1: addStudent(); break;
                case 2: updateStudent(); break;
                case 3: deleteStudent(); break;
                case 4: studentList.displayAll(); break;

                
                case 5: addServiceRequest(); break;
                case 6: processServiceRequest(); break;
                case 7: actionStack.displayAll(); break;

                case 8: bst.displayInOrder(); break;
                case 9: searchByHashing(); break;

                
                case 10: addLocation(); break;
                case 11: removeLocation(); break;
                case 12: addConnection(); break;
                case 13: removeConnection(); break;
                case 14: graph.displayConnections(); break;
                case 15: traverseGraph(); break;

                case 16: System.out.println("Exiting... Goodbye!"); break;
                default: System.out.println("Invalid choice. Please select 1-16.");
            }
            if (choice != 16) pause();
        } while (choice != 16);
        sc.close();
    }

    private static void printMenu() {
        System.out.println("========= University Student Record & Campus Route Management System =========");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Delete Student Record");
        System.out.println("4. Display All Records using Linked List");
        System.out.println("5. Add Service Request to Queue");
        System.out.println("6. Process Next Service Request");
        System.out.println("7. Display Recent Actions using Stack");
        System.out.println("8. Display Students using BST/AVL");
        System.out.println("9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS or DFS");
        System.out.println("16. Exit");
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    private static void pause() {
        System.out.println();
        System.out.print("Press Enter to return to the menu...");
        sc.nextLine();
    }


    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                double marks = Double.parseDouble(line);
                if (marks < 0 || marks > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                    continue;
                }
                return marks;
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks. Please enter a number.");
            }
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("This field cannot be empty.");
        }
    }


    private static void addStudent() {
        String id = readNonEmpty("Enter Student ID: ");
        if (studentList.contains(id)) {
            System.out.println("Error: Student ID already exists.");
            return;
        }
        String name = readNonEmpty("Enter Name: ");
        String programme = readNonEmpty("Enter Programme: ");
        double marks = readMarks("Enter Marks (0-100): ");

        Student s = new Student(id, name, programme, marks);
        studentList.addStudent(s);     
        bst.insert(s);                  
        hashTable.insert(s);            
        actionStack.push("Added student " + id + " (" + name + ")"); 
        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        String id = readNonEmpty("Enter Student ID to update: ");
        Student existing = studentList.searchById(id);
        if (existing == null) {
            System.out.println("Error: Student not found.");
            return;
        }
        String name = readNonEmpty("Enter new Name: ");
        String programme = readNonEmpty("Enter new Programme: ");
        double marks = readMarks("Enter new Marks (0-100): ");

        studentList.updateStudent(id, name, programme, marks);
        actionStack.push("Updated student " + id);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        String id = readNonEmpty("Enter Student ID to delete: ");
        Student removed = studentList.deleteStudent(id);
        if (removed == null) {
            System.out.println("Error: Student not found.");
            return;
        }
        bst.delete(id);
        hashTable.remove(id);
        actionStack.push("Deleted student " + id + " (" + removed.getName() + ")");
        System.out.println("Student deleted successfully.");
    }


    private static void addServiceRequest() {
        String id = readNonEmpty("Enter Student ID: ");
        String details = readNonEmpty("Enter request details (e.g., transcript request): ");
        serviceQueue.enqueue(id, details);
        actionStack.push("Service request queued for " + id);
        System.out.println("Request added to queue.");
    }

    private static void processServiceRequest() {
        String[] req = serviceQueue.dequeue();
        if (req == null) {
            System.out.println("No pending requests.");
            return;
        }
        actionStack.push("Processed request for " + req[0] + ": " + req[1]);
        System.out.println("Processed -> Student: " + req[0] + ", Request: " + req[1]);
    }

    private static void searchByHashing() {
        String id = readNonEmpty("Enter Student ID to search: ");
        Student s = hashTable.search(id);
        System.out.println(s == null ? "Student not found." : "Found: " + s);
    }


    private static void addLocation() {
        String name = readNonEmpty("Enter new campus location name: ");
        if (graph.addLocation(name)) {
            actionStack.push("Added campus location " + name);
            System.out.println("Location added.");
        } else {
            System.out.println("Error: Location already exists.");
        }
    }

    private static void removeLocation() {
        String name = readNonEmpty("Enter campus location to remove: ");
        if (graph.removeLocation(name)) {
            actionStack.push("Removed campus location " + name);
            System.out.println("Location removed.");
        } else {
            System.out.println("Error: Location not found.");
        }
    }

    private static void addConnection() {
        String a = readNonEmpty("Enter first location: ");
        String b = readNonEmpty("Enter second location: ");
        if (graph.addConnection(a, b)) {
            actionStack.push("Connected " + a + " <-> " + b);
            System.out.println("Connection added.");
        } else {
            System.out.println("Error: Check that both locations exist and are not already connected.");
        }
    }

    private static void removeConnection() {
        String a = readNonEmpty("Enter first location: ");
        String b = readNonEmpty("Enter second location: ");
        if (graph.removeConnection(a, b)) {
            actionStack.push("Removed connection " + a + " <-> " + b);
            System.out.println("Connection removed.");
        } else {
            System.out.println("Error: Connection not found.");
        }
    }

    private static void traverseGraph() {
        String start = readNonEmpty("Enter starting location: ");
        if (!graph.hasLocation(start)) {
            System.out.println("Error: Location not found.");
            return;
        }
        int type = readInt("Choose traversal (1 = BFS, 2 = DFS): ");
        List<String> result = (type == 2) ? graph.dfs(start) : graph.bfs(start);
        System.out.println((type == 2 ? "DFS" : "BFS") + " order: " + result);
    }
}