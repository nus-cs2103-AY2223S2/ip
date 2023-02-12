package duke;

import duke.enums.Views;

/**
 * Exception for Duke methods to throw
 */
public class DukeException extends Exception {
    /**
     * Default exception thrown without any message
     */
    public DukeException() {
        super();
    }

    /**
     * Exception with custom message
     *
     * @param message
     */
    public DukeException(Views view) {
        super(view.str());
    }

    /**
     * Exception with custom message
     *
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Exception with custom message and cause
     *
     * @param message
     * @param cause
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with custom cause
     *
     * @param cause
     */
    public DukeException(Throwable cause) {
        super(cause);
    }
}
