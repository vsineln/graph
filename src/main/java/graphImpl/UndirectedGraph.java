package graphImpl;

import graph.Graph;
import vertex.Vertex;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Class for providing {@link Graph} implementation for undirected Graph.
 */
public class UndirectedGraph extends DirectedGraph {

    /**
     * Create undirected graph using Adjacency List
     *
     * @param vertices
     */
    public UndirectedGraph(ConcurrentMap<Vertex, List<Vertex>> vertices) {
        super(vertices);
    }

    /**
     * Create graph without edges using list of vertices
     *
     * @param set of vertices
     */
    public UndirectedGraph(Set<Vertex> set) {
        super(set);
    }

    @Override
    public void addEdge(Vertex vertex1, Vertex vertex2) {
        super.addEdge(vertex1, vertex2);
        vertices.get(vertex2).add(vertex1);
    }

    @Override
    public String toString() {
        return "UndirectedGraph{" +
                "vertices=" + vertices +
                '}';
    }
}
