package duke;

/**
 * Class that defines types of exceptions specific to Duke
 */
public class DukeException extends IllegalArgumentException {

    /** Error message to be displayed when a DukeException is thrown*/
    private String message;

    /**
     * Constructor for DukeException
     * @param exceptionType Specifies the type of exception in user input
     */
    DukeException(String exceptionType) {
        switch (exceptionType) {
        case "todo":
            message = "OOPS!! This is an invalid todo task.\n" +
                    "Here's some valid examples:\n" +
                    "   todo borrow book\n" +
                    "   t borrow book";
            break;
        case "deadline":
            message = "OOPS!! This is an invalid deadline task.\n" +
                    "Here's some valid examples:\n" +
                    "   deadline return book /by 2020-12-23\n" +
                    "   d return book /by 2020-12-23";
            break;
        case "event":
            message = "OOPS!! This is an invalid event task.\n" +
                    "Here's some valid examples:\n" +
                    "   event project meeting /from 2020-12-15 /to 2021-12-23\n" +
                    "   e project meeting /from 2020-12-15 /to 2021-12-23";
            break;
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
