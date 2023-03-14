package duke.exception;

/**
 * Custom Exception for duke chatbot to display custom error messages.
 */
public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
