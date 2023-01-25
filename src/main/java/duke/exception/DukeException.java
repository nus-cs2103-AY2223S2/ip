package duke.exception;

/**
 * Custom Excception for duke chatbot to display custom error messages.
 */
public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
