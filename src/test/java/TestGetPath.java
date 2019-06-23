import exception.GraphException;
import graph.Graph;
import graphImpl.DirectedGraph;
import graphImpl.UndirectedGraph;
import org.junit.jupiter.api.Test;
import vertex.Vertex;

import static org.junit.jupiter.api.Assertions.*;

public class TestGetPath {

    private Graph directedGraph;
    private Graph undirectedGraph;
    private Vertex vertex0 = new Vertex(0);
    private Vertex vertex1 = new Vertex(1);
    private Vertex vertex3 = new Vertex(3);
    private Vertex vertex4 = new Vertex(4);
    private Vertex vertex5 = new Vertex(5);
    private Vertex vertex6 = new Vertex(6);
    private Vertex vertex7 = new Vertex(7);
    private Vertex vertex11 = new Vertex(11);
    private Vertex vertex14 = new Vertex(14);
    private Vertex vertex15 = new Vertex(15);

    @Test
    public void testGetPathInDirectedGraph() {
        directedGraph = new DirectedGraph(TestUtil.getGraphFromFile("directGraph.txt"));

        assertEquals(2, directedGraph.getPath(vertex0, vertex6).size());
        assertEquals(3, directedGraph.getPath(vertex0, vertex3).size());
        assertEquals(3, directedGraph.getPath(vertex0, vertex4).size());
        assertEquals(4, directedGraph.getPath(vertex3, vertex11).size());
        assertEquals(5, directedGraph.getPath(vertex0, vertex11).size());

        directedGraph.addVertex(vertex15);
        assertNull(directedGraph.getPath(vertex0, vertex15));

        assertNull(directedGraph.getPath(vertex3, vertex3));

        GraphException exception1 = assertThrows(GraphException.class, () -> directedGraph.getPath(vertex3, vertex14));
        assertEquals(exception1.getMessage(), "Both vertices must be in graph");
    }

    @Test
    public void testGetPathInUndirectedGraph() {
        undirectedGraph = new UndirectedGraph(TestUtil.getGraphFromFile("undirectGraph.txt"));

        assertEquals(4, undirectedGraph.getPath(vertex0, vertex4).size());
        assertEquals(3, undirectedGraph.getPath(vertex0, vertex6).size());
        assertEquals(3, undirectedGraph.getPath(vertex5, vertex1).size());
        assertEquals(3, undirectedGraph.getPath(vertex3, vertex1).size());
        assertEquals(4, undirectedGraph.getPath(vertex7, vertex0).size());

        undirectedGraph.addVertex(vertex15);
        assertNull(undirectedGraph.getPath(vertex0, vertex15));

        assertNull(undirectedGraph.getPath(vertex3, vertex3));

        GraphException exception1 = assertThrows(GraphException.class, () -> undirectedGraph.getPath(vertex3, vertex14));
        assertEquals(exception1.getMessage(), "Both vertices must be in graph");
    }
}