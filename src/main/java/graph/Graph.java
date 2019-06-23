package graph;

import vertex.Vertex;

import java.util.List;

/**
 * Interface with common methods for Graph creation and traversing
 */
public interface Graph {

    /**
     * Adds vertex to the Graph
     *
     * @param vertex which will be added
     */
    void addVertex(Vertex vertex);

    /**
     * Adds edge to the Graph
     *
     * @param vertex1 the start vertex for edge
     * @param vertex2 the end vertex for edge
     */
    void addEdge(Vertex vertex1, Vertex vertex2);

    /**
     * Finds the path between two vertices
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the path of vertices between vertex1 and vertex2, or null if there is no path between them
     */
    List<Vertex> getPath(Vertex vertex1, Vertex vertex2);

    /**
     * Finds number of vertices in graoh
     *
     * @return number of vertices in graph
     */
    int verticesCount();

    /**
     * Gets all adjacent vertices for certain vertex
     *
     * @param vertex to find adjacent vertices
     * @return list of adjacent vertices
     */
    List<Vertex> getAdjVertices(Vertex vertex);
}
