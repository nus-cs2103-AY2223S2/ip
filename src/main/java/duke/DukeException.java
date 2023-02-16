package duke;

/**
 * Exceptions thrown in Duke
 */
public class DukeException extends Exception {

    /**
     * constructor for new DukeException instance
     * 
     * @param message exception message to be printed for users
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * String representation of exception
     * 
     * @return string for message
     */
    @Override
    public String toString() {
        return "OOPS! An error has occurred";
    }

}