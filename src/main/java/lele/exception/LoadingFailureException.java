package lele.exception;

/**
 * For when files fail to load due to something that happened with the system
 */
public class LoadingFailureException extends LeleException {
    public LoadingFailureException(String message) {
        super(message);
    }
}
