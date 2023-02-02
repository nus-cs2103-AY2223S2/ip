package duke.exceptions;

/**
 * General exception thrown by the Duke chatbot.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class DukeException extends Exception {
    /**
     * Constructor for the exception class.
     * @param msg Message to describe the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Displays string representation of the exception.
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return String.format("☹ OOPS!!! %s", super.getMessage());
    }
}
