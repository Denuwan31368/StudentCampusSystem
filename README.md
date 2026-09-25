## Group Members

| # | Student ID | Full Name | Assigned Responsibility | Individual Contribution|
|---|-----------|-----------|--------------------------|-------------------------------------|
| 1 | 23DA2-0401| R.M.M.E Rathnayaka| Linked list & student-record management | Implemented StudentLinkedList.java (add, update, delete, search, display with duplicate-ID handling), | 
| 2 | 23DA2-0056| Chamika Sithum | Stack & queue implementation | Implemented ActionStack.java (recent-action/undo history using a linked-node stack) and ServiceQueue.java (student service requests using a linked-node queue), including safe handling of empty-stack/queue cases. |
| 3 | 23DA2-0428| A.M.D.C Pilimathalawwa | BST + hashing implementation | Set up the Github repository and shared Student.java model and Implemented StudentBST.java (insert, search, delete, and sorted in-order display) and StudentHashTable.java (custom hash function with separate chaining for fast Student ID lookup). |
| 4 | 23DA2-0313| Hashan Madhura| Graph, campus locations & BFS/DFS | Implemented CampusGraph.java using an adjacency list — add/remove locations and connections, network display, and both BFS and DFS traversal. |

# University Student Record and Campus Route Management System

**Module:** CIT300 - Data Structures and Algorithms
**Assessment:** Graded Practical Assignment 1 (Week 10)
**Coverage:** Weeks 1–9 - Linear Data Structures, Trees, Hashing, and Graphs

## 2. Objective

Develop a Java console application that manages university student records
and represents connections between campus locations, demonstrating the
practical use of linked lists, stacks, queues, trees, hashing, and graphs —
the topics covered in Weeks 1–9 of CIT300.

---

## 3. Requirements Coverage

| # | Requirement | Status | Implemented In |
|---|---|---|---|
| 1 | Store Student ID, Name, Programme, Marks | Done | `Student.java` |
| 2 | Linked list to store/manage student records | Done | `StudentLinkedList.java` |
| 3 | Stack for recent actions / undo-history | Done | `ActionStack.java` |
| 4 | Queue for service requests in arrival order | Done | `ServiceQueue.java` |
| 5 | BST/AVL to organise/search students by ID | Done | `StudentBST.java` |
| 6 | Hashing for efficient Student ID search | Done | `StudentHashTable.java` |
| 7 | Graph representing campus locations/connections | Done | `CampusGraph.java` |
| 8 | Graph represented via adjacency list | Done | `CampusGraph.java` |
| 9 | Add/remove campus locations and connections | Done | `CampusGraph.java`, `MainApp.java` |
| 10 | Display connected locations / campus network | Done | `CampusGraph.displayConnections()` |
| 11 | Graph traversal — BFS and DFS | Done | `CampusGraph.bfs()`, `CampusGraph.dfs()` |
| 12 | Add, update, delete, search, display student records | Done | `StudentLinkedList.java`, `MainApp.java` |
| 13 | Menu-driven console interface with input validation | Done | `MainApp.java` |
| 14 | Handle invalid input, duplicates, missing records, unavailable connections | Done | `MainApp.java` (validation helpers + error checks throughout) |

---

## 4. Menu

```
1.  Add Student Record
2.  Update Student Record
3.  Delete Student Record
4.  Display All Records using Linked List
5.  Add Service Request to Queue
6.  Process Next Service Request
7.  Display Recent Actions using Stack
8.  Display Students using BST/AVL
9.  Search Student using Hashing
10. Add Campus Location
11. Remove Campus Location
12. Add Campus Connection/Road
13. Remove Campus Connection/Road
14. Display Campus Connections
15. Traverse Campus Locations using BFS or DFS
16. Exit
```

---

## 5. Project Structure

```
StudentCampusSystem/
├── README.md
└── src/
    ├── Student.java           # Data model: Student ID, Name, Programme, Marks
    ├── StudentLinkedList.java # Rathnayaka - linked list storage & CRUD operations
    ├── ActionStack.java       # Sithum - stack for recent actions / history
    ├── ServiceQueue.java      # Sithum - queue for service requests
    ├── StudentBST.java        # Pilimathalawwa - BST keyed on Student ID
    ├── StudentHashTable.java  # Pilimathalawwa - hash table for fast ID search
    ├── CampusGraph.java       # Madhura - graph, adjacency list, BFS/DFS
    └── MainApp.java           # Shared - 16-option menu, integration, validation
```

---

## 6. Technology Stack

- **Language:** Java (JDK 11+)
- **Interface:** Console-based (`java.util.Scanner`)
- **Data structures:** All core structures (linked list, stack, queue, BST,
  hash table) are implemented from scratch using custom node classes rather
  than `java.util.LinkedList`/`Stack`, to demonstrate understanding of the
  underlying mechanics as expected in a Data Structures & Algorithms module.
  The graph uses `java.util.Map`/`List` only as the underlying adjacency-list
  container — the graph logic (add/remove/BFS/DFS) itself is custom.

---

## 8. Testing

The system was manually tested end-to-end against all 16 menu options and
all 14 requirements, including deliberate invalid-input cases:
non-numeric menu choices, out-of-range/non-numeric marks, empty text
fields, duplicate Student IDs, duplicate location names, operations on
missing students/locations, and connections to non-existent locations.
All cases were confirmed to fail gracefully with a clear error message
rather than crashing the program.

---

## 9. GitHub Collaboration

- Each member worked on their own feature branch (`feature/linked-list`,
  `feature/stack-queue`, `feature/bst-hashing`, `feature/graph`), committing
  incrementally as each method was completed.
- Work was merged into `main` via individual Pull Requests, each reviewed
  by at least one other team member before merging.
- `MainApp.java` was integrated last, once all four branches were merged,
  since it depends on every other class.
- Full commit history is visible under the repository's Commits tab;
  merged Pull Requests are visible under the Pull requests tab.

## 10. Demonstration Video

A merged video demonstrating the complete system is included with this
submission. Each member explains and demonstrates their own component:

| Segment | Member | Covers |
|---|---|---|
| Intro | A.M.D.C Pilimathalawwa | Project overview |
| 1 | R.M.M.E Rathnayaka | Linked list & student records (options 1-4) |
| 2 | Chamika Sithum | Stack & queue (options 5-7) |
| 3 | A.M.D.C Pilimathalawwa | BST & hashing (options 8-9) |
| 4 | Hashan Madhura | Graph, BFS/DFS (options 10-15) |
| Outro | A.M.D.C Pilimathalawwa | GitHub collaboration evidence |


