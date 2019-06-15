package graphImpl;

import graph.Graph;
import lombok.extern.slf4j.Slf4j;
import sun.security.provider.certpath.Vertex;

import java.util.LinkedList;

/**
 * Class for providing {@link Graph} interface implementation for directed Graph.
 */
@Slf4j
public class DirectedGraph implements Graph {


    public boolean addVertex(Vertex vertex) {
        return false;
    }

    public boolean addEdge(Vertex vertex1, Vertex vertex2) {
        return false;
    }

    public LinkedList getPath(Vertex vertex1, Vertex vertex2) {
        return null;
    }
}
