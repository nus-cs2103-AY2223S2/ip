package duke;

/**
 * Class that defines types of exceptions specific to Duke
 */
public class DukeException extends IllegalArgumentException {

    /** Error message to be displayed when a DukeException is thrown*/
    private String message;

    /**
     * Constructor for DukeException
     * @param taskType Specifies the type of error in user input
     */
    DukeException(String taskType) {
        if (taskType.equals("todo")) {
            message = "OOPS!! This is an invalid todo task.\n" +
                    "Here's a valid example:\n" +
                    "   todo borrow book";
        } else if (taskType.equals("deadline")) {
            message = "OOPS!! This is an invalid deadline task.\n" +
                    "Here's a valid example:\n" +
                    "   deadline return book /by Sunday";
        } else if (taskType.equals("event")) {
            message = "OOPS!! This is an invalid event task.\n" +
                    "Here's a valid example:\n" +
                    "   event project meeting /from Mon 2pm /to 4pm";
        }
    }

    /**
     * Default constructor for non-specific cases of exception
     */
    DukeException() {
        message = "OOPS!! I'm sorry, I don't know what that means :(";
    }

    @Override
    public String toString() {
        return message;
    }
}
