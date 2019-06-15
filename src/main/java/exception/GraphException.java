package exception;

/**
 * Custom Graph exception
 */

public class GraphException extends RuntimeException {
    private String error;

    public GraphException(String error) {
        this.error = error;
    }
}
