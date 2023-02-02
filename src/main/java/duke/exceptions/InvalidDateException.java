package duke.exceptions;

/**
 *  An exception thrown by the Duke chatbot whenever an invalid date format is provided.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for InvalidDateException.
     */
    public InvalidDateException() {
        super("Invalid Date Format, please input it as YYYY-MM-DD;\nFor events, make sure start date <= end date!");
    }
}
