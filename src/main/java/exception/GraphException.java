package exception;

/**
 * Custom Graph exception. Will be thrown if wrong data are used for Graph creation
 */

public class GraphException extends RuntimeException {

    public GraphException(String error) {
        super(error);
    }

    public GraphException(String message, Throwable cause) {
        super(message, cause);
    }
}
