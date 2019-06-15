package graph;

import sun.security.provider.certpath.Vertex;

import java.util.LinkedList;

/**
 * Interface with common graph methods
 */
public interface Graph {

    /**
     * Adds vertex to the Graph
     *
     * @param vertex which will be added
     * @return true if vertex was added
     */
    boolean addVertex(Vertex vertex);

    /**
     * Adds edge to the Graph
     *
     * @param vertex1 the start vertex for edge
     * @param vertex2 the end vertex for edge
     * @return true if edge was added
     */
    boolean addEdge(Vertex vertex1, Vertex vertex2);

    /**
     * Finds the path between two vertices
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the list of edges between 2 vertices
     */
    LinkedList getPath(Vertex vertex1, Vertex vertex2);
}
