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

    /** Requirement #14: rejects duplicate location names. */
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
}
