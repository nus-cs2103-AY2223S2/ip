package duke.dukeexceptions;

/**
 *  Exceptions for Duke Application.
 */
public class DukeException extends Exception {

    /**
     * Constructor for the Exception.
     *
     * @param s string to be thrown during exception.
     */
    public DukeException(String s) {
        super(s);
    }
}
