package graphImpl;

import exception.GraphException;
import graph.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vertex.Vertex;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.ConcurrentMap;

/**
 * Class for providing {@link Graph} implementation for directed Graph.
 */
public class DirectedGraph implements Graph {

    private static Logger log = LoggerFactory.getLogger(DirectedGraph.class);
    protected ConcurrentMap<Vertex, List<Vertex>> vertices;

    /**
     * Create directed graph using Adjacency List
     *
     * @param vertices
     */
    public DirectedGraph(ConcurrentMap<Vertex, List<Vertex>> vertices) {
        if (vertices == null) {
            throw new GraphException("Vertices can not be null");
        }
        this.vertices = vertices;
    }

    /**
     * Create graph without edges using list of vertices
     *
     * @param set of vertices
     */
    public DirectedGraph(Set<Vertex> set) {
        if (set == null) {
            throw new GraphException("Vertices can not be null");
        }
        vertices = new ConcurrentHashMap<>();
        set.forEach(t -> vertices.put(t, new LinkedList<>()));
    }

    @Override
    public void addVertex(Vertex vertex) {
        log.debug("Add vertex to graph");

        if (vertex == null) {
            throw new GraphException("Vertex can not be null");
        }

        if (vertices.containsKey(vertex)) {
            throw new GraphException("Vertex already exists");
        }
        vertices.put(vertex, new LinkedList<>());
    }

    @Override
    public void addEdge(Vertex vertex1, Vertex vertex2) {
        log.debug("Add edge to graph");

        if (vertex1 == null || vertex2 == null) {
            throw new GraphException("Vertex can not be null");
        }

        if (!vertices.containsKey(vertex1)) {
            throw new GraphException(String.format("Vertex %d is not found", vertex1.getId()));
        }

        if (!vertices.containsKey(vertex2)) {
            throw new GraphException(String.format("Vertex %d is not found", vertex2.getId()));
        }

        if (getAdjVertices(vertex1).contains(vertex2)) {
            throw new GraphException(String.format("Edge %d-%d already exists", vertex1.getId(), vertex2.getId()));
        }

        vertices.get(vertex1).add(vertex2);
    }

    @Override
    public List<Vertex> getPath(Vertex vertex1, Vertex vertex2) {
        log.debug("Get path between {} and {}", vertex1, vertex2);

        if (vertex1 == null || vertex2 == null) {
            throw new GraphException("Vertex can not be null");
        }

        if (!vertices.containsKey(vertex1) || !vertices.containsKey(vertex2)) {
            throw new GraphException("Both vertices must be in graph");
        }

        if (vertex1.equals(vertex2)) {
            log.debug("Vertices are equal");
            return null;
        }

        Set<Vertex> visited = new HashSet<>();
        Map<Vertex, Vertex> edges = new HashMap<>();
        Deque<Vertex> queue = new ArrayDeque<>();

        queue.offerLast(vertex1);
        while (!queue.isEmpty()) {
            Vertex vertex = queue.pollFirst();
            if (vertex.equals(vertex2)) {
                return restorePath(vertex1, vertex2, edges);
            }
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                if (vertices.containsKey(vertex)) {
                    for (Vertex v : vertices.get(vertex)) {
                        queue.offerLast(v);
                        edges.putIfAbsent(v, vertex);
                    }
                }
            }
        }

        log.debug("Path is not found");
        return null;
    }

    /**
     * Restore path between two vertex
     *
     * @param vertex1 first vertex
     * @param vertex2 second vertex
     * @param edges   edges retrieved during breadth-first search
     * @return list of vertices between vertex1 and vertex2
     */
    private List<Vertex> restorePath(Vertex vertex1, Vertex vertex2, Map<Vertex, Vertex> edges) {
        List<Vertex> path = new LinkedList<>();
        path.add(vertex2);

        while (!vertex1.equals(vertex2)) {
            if (edges.containsKey(vertex2)) {
                Vertex next = edges.get(vertex2);
                path.add(next);
                vertex2 = next;
            }
        }

        log.debug("Path consists of {} vertices", path.size());
        return path;
    }

    @Override
    public int verticesCount() {
        return vertices.size();
    }

    @Override
    public List<Vertex> getAdjVertices(Vertex vertex) {
        if (!vertices.containsKey(vertex)) {
            throw new GraphException(String.format("Vertex %d is not found", vertex.getId()));
        }
        return vertices.get(vertex);
    }

    @Override
    public String toString() {
        return "DirectedGraph{" +
                "vertices=" + vertices +
                '}';
    }
}
