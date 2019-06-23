package vertex;

import exception.GraphException;

import java.util.Objects;

/**
 * Class for graph's vertex implementation
 */
public class Vertex {

    private int id;

    public Vertex(int id) {
        if (id < 0) {
            throw new GraphException(String.format("Id %d is invalid, should be positive or 0", id));
        }
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                '}';
    }
}
