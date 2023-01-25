package exceptions;

/**
 * Represents custom exceptions thrown by the chatbot.
 */
public class DukeException extends Exception {
    /**
     * Constructs an exception with the given description.
     *
     * @param description Description of the problem.
     */
    public DukeException(String description) {
        super(description);
    }
}
