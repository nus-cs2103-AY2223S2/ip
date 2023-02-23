package duke;

/**
 * DukeException class for Duke throw when unexpected behaviour occurs during runtime.
 */
public class DukeException extends Exception{
    /**
     * Generates a DukeException that informs user about problems.
     *
     * @param msg The error message to be printed to user.
     */
    public DukeException(String msg) {
        super(msg);
  }
}
