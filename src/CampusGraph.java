import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusGraph {

    private final Map<String, List<String>> adjList = new LinkedHashMap<>();

    public boolean hasLocation(String name) {
        return adjList.containsKey(name);
    }

    public boolean addLocation(String name) {
        if (adjList.containsKey(name)) return false;
        adjList.put(name, new ArrayList<>());
        return true;
    }

    public boolean removeLocation(String name) {
        if (!adjList.containsKey(name)) return false;
        adjList.remove(name);
        for (List<String> neighbours : adjList.values()) {
            neighbours.remove(name);
        }
        return true;
    }

    public boolean addConnection(String a, String b) {
        if (!adjList.containsKey(a) || !adjList.containsKey(b)) return false;
        if (adjList.get(a).contains(b)) return false;
        adjList.get(a).add(b);
        adjList.get(b).add(a);
        return true;
    }

    public boolean removeConnection(String a, String b) {
        if (!adjList.containsKey(a) || !adjList.containsKey(b)) return false;
        boolean removed1 = adjList.get(a).remove(b);
        boolean removed2 = adjList.get(b).remove(a);
        return removed1 || removed2;
    }

    public void displayConnections() {
        if (adjList.isEmpty()) {
            System.out.println("No campus locations added yet.");
            return;
        }
        System.out.println("---- Campus Network (Adjacency List) ----");
        for (String loc : adjList.keySet()) {
            System.out.println(loc + " -> " + adjList.get(loc));
        }
    }

    public List<String> bfs(String start) {
        List<String> visitedOrder = new ArrayList<>();
        if (!adjList.containsKey(start)) return visitedOrder;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            visitedOrder.add(cur);
            for (String neighbour : adjList.get(cur)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        return visitedOrder;
    }

    public List<String> dfs(String start) {
        List<String> visitedOrder = new ArrayList<>();
        if (!adjList.containsKey(start)) return visitedOrder;
        Set<String> visited = new HashSet<>();
        dfsHelper(start, visited, visitedOrder);
        return visitedOrder;
    }

    private void dfsHelper(String cur, Set<String> visited, List<String> order) {
        visited.add(cur);
        order.add(cur);
        for (String neighbour : adjList.get(cur)) {
            if (!visited.contains(neighbour)) {
                dfsHelper(neighbour, visited, order);
            }
        }
    }
}
