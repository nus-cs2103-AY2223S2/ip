package duke.exception;

public class DukeException extends Exception{
    /**
     * Constructor of DukeException
     *
     * @param error The error message.
     */
    public DukeException(String error) {
        super(error);
    }
}
