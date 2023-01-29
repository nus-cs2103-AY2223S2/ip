package duke;

/**
 * DukeException is an unchecked exception that is thrown when Duke does not recognise the input.
 */
public class DukeException extends Exception {
    /**
     * A constructor for Duke Exception.
     *
     * @param message contains the error message to be outputted.
     */
    public DukeException(String message) {
        super(message);
    }
}
