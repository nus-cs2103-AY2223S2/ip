public class DukeException extends IllegalArgumentException {
    // Attributes
    String message;

    // Error with incomplete command for todos, deadlines and events
    DukeException(String task_type) {
        if (task_type.equals("todo")) {
            message = "OOPS!! This is an invalid todo task.\n" +
                    "Here's a valid example:\n" +
                    "   todo borrow book";
        } else if (task_type.equals("deadline")) {
            message = "OOPS!! This is an invalid deadline task.\n" +
                    "Here's a valid example:\n" +
                    "   deadline return book /by Sunday";
        } else if (task_type.equals("event")) {
            message = "OOPS!! This is an invalid event task.\n" +
                    "Here's a valid example:\n" +
                    "   event project meeting /from Mon 2pm /to 4pm";
        }
    }

    // Error with invalid command (only accept todos, deadlines and events)
    DukeException() {
        message = "OOPS!! I'm sorry, I don't know what that means :(";
    }

    @Override
    public String toString() {
        return message;
    }
}
