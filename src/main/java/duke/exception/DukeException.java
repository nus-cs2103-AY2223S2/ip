package duke.exception;

public class DukeException extends Exception{

    /**
     * Constructs DukeException class.
     *
     * @param message Duke error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
