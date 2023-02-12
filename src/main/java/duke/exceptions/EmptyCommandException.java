package duke.exceptions;

/**
 * EmptyCommandException represents an exception if
 * the existing command is empty.
 */
public class EmptyCommandException extends Exception {

    /**
     * Creates a EmptyCommandException
     */
    public EmptyCommandException(String taskType) {
        super("OOPS!!! The description of a " + taskType + " cannot be empty.");
    }

}
