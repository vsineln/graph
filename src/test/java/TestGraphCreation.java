import exception.GraphException;
import graph.Graph;
import graphImpl.DirectedGraph;
import graphImpl.UndirectedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertex.Vertex;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestGraphCreation {

    private Set<Vertex> setOfVertices;
    private Graph graph;
    private Vertex vertex1 = new Vertex(1);
    private Vertex vertex2 = new Vertex(2);
    private Vertex vertex3 = new Vertex(3);
    private Vertex vertex4 = new Vertex(4);
    private Vertex vertex5 = new Vertex(5);
    private Vertex vertex6 = new Vertex(6);
    private Vertex vertex7 = new Vertex(7);
    private Vertex vertex8 = new Vertex(8);
    private Vertex vertex9 = new Vertex(9);

    @BeforeEach
    private void init() {
        setOfVertices = Stream.of(vertex1, vertex2, vertex3,
                vertex5, vertex6, vertex7)
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Test
    public void testAddVertex() {
        graph = new DirectedGraph(setOfVertices);

        assertEquals(6, graph.verticesCount());
        graph.addVertex(vertex4);
        assertEquals(7, graph.verticesCount());

        GraphException exception1 = assertThrows(GraphException.class, () -> graph.addVertex(vertex2));
        assertEquals(exception1.getMessage(), "Vertex already exists");

        exception1 = assertThrows(GraphException.class, () -> graph.addVertex(null), "Vertex can not be null");
        assertEquals(exception1.getMessage(), "Vertex can not be null");
    }

    @Test
    public void testAddEdgeInDirectGraph() {
        graph = new DirectedGraph(setOfVertices);

        assertFalse(checkAdjacenty(vertex1, vertex2));
        graph.addEdge(vertex1, vertex2);
        assertTrue(checkAdjacenty(vertex1, vertex2));
        assertEquals(1, graph.getAdjVertices(vertex1).size());

        graph.addEdge(vertex1, vertex3);
        assertTrue(checkAdjacenty(vertex1, vertex3));
        assertEquals(2, graph.getAdjVertices(vertex1).size());

        GraphException exception1 = assertThrows(GraphException.class, () -> graph.addEdge(vertex1, vertex2));
        assertEquals(exception1.getMessage(), "Edge 1-2 already exists");

        exception1 = assertThrows(GraphException.class, () -> graph.addEdge(vertex1, null));
        assertEquals(exception1.getMessage(), "Vertex can not be null");

        exception1 = assertThrows(GraphException.class, () -> graph.addEdge(vertex1, vertex8));
        assertEquals(exception1.getMessage(), "Vertex 8 is not found");

        exception1 = assertThrows(GraphException.class, () -> graph.addEdge(vertex9, vertex2));
        assertEquals(exception1.getMessage(), "Vertex 9 is not found");

    }

    @Test
    public void testAddEdgeInUndirectGraph() {
        graph = new UndirectedGraph(setOfVertices);

        assertFalse(checkAdjacenty(vertex1, vertex2));
        graph.addEdge(vertex1, vertex2);
        assertTrue(checkAdjacenty(vertex1, vertex2));
        assertTrue(checkAdjacenty(vertex2, vertex1));
        assertEquals(1, graph.getAdjVertices(vertex1).size());

        graph.addEdge(vertex1, vertex3);
        assertTrue(checkAdjacenty(vertex1, vertex3));
        assertTrue(checkAdjacenty(vertex3, vertex1));
        assertEquals(2, graph.getAdjVertices(vertex1).size());

        GraphException exception1 = assertThrows(GraphException.class, () -> graph.addEdge(vertex1, vertex2));
        assertEquals(exception1.getMessage(), "Edge 1-2 already exists");

        exception1 = assertThrows(GraphException.class, () -> graph.addEdge(vertex1, null));
        assertEquals(exception1.getMessage(), "Vertex can not be null");

        exception1 = assertThrows(GraphException.class, () -> graph.addEdge(vertex1, vertex8));
        assertEquals(exception1.getMessage(), "Vertex 8 is not found");

        exception1 = assertThrows(GraphException.class, () -> graph.addEdge(vertex9, vertex2));
        assertEquals(exception1.getMessage(), "Vertex 9 is not found");

    }

    private boolean checkAdjacenty(Vertex vertex1, Vertex vertex2) {
        return graph.getAdjVertices(vertex1).stream().anyMatch(v -> v.equals(vertex2));

    }
}