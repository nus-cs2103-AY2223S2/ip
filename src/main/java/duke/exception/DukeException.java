package duke.exception;

/**
 * Represents an exception that can be thrown while interacting with the chatbot.
 */
public class DukeException extends Exception {

    protected DukeException(String message) {
        super(message);
    }

}
