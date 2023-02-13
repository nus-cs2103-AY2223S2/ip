package jarvis.exception.command;

/**
 * Exception from commands provided with invalid indices.
 */
public class InvalidIndexException extends InvalidParameterException {
    public InvalidIndexException(int from, int to) {
        super("Invalid index", String.format("The index should be from %d to %d", from, to));
    }
}
