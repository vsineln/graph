import exception.GraphException;
import vertex.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class
 */
public class TestUtil {

    /**
     * Next format should be used for graph creation:
     * 1. first line includes number of vertices and number of edges separated by space
     * 2. each new line is an edge represented by its two vertices separated by space
     *
     * @param fileName with graph's data
     * @return adjacency list
     */
    public static ConcurrentMap<Vertex, List<Vertex>> getGraphFromFile(String fileName) {
        Path resources = Paths.get("src", "test", "resources", fileName);

        ConcurrentMap<Vertex, List<Vertex>> vertices = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(resources.toFile()));
            String s = reader.readLine();
            if (!s.isEmpty()) {
                String[] count = s.split(" ");
                int vertexCount = Integer.parseInt(count[0]);
                vertices = new ConcurrentHashMap<>(vertexCount);
                int edgeCount = Integer.parseInt(count[1]);
                for (int i = 0; i < edgeCount; i++) {
                    s = reader.readLine();
                    String[] edge = s.split(" ");
                    Vertex v1 = new Vertex(Integer.parseInt(edge[0]));
                    Vertex v2 = new Vertex(Integer.parseInt(edge[1]));
                    if (!vertices.containsKey(v1)) {
                        vertices.put(v1, Stream.of(v2).collect(Collectors.toList()));
                    } else {
                        List<Vertex> adj = vertices.get(v1);
                        adj.add(v2);
                        vertices.put(v1, adj);
                    }
                    if (!vertices.containsKey(v2)) {
                        vertices.put(v2, new LinkedList<>());
                    }
                }
            }
            return vertices;
        } catch (IOException e) {
            throw new GraphException("Unsupported format");
        }
    }
}
