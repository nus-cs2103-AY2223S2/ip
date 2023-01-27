package duke.exceptions;

/**
 * A wrapper around the Exception class for the duke.Duke Chatbot.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
