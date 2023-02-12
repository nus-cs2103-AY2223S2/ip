package duke;

/**
 * Thrown when the command that is placed in the user input does not match
 * any of the existing commands
 */
public class DontKnowWhatThatMeansException extends Exception {
    public DontKnowWhatThatMeansException(String message) {
        super(message);
    }
}
