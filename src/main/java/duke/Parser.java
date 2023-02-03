package duke;

/**
 * Decodes the input base on the keyword the user input.
 */
public class Parser {

    /**
     * Returns the event type associated to the user input
     *
     * @param input User input.
     * @return EventType
     * @throws DukeException If keyword is not recognised.
     */
    protected static EventType parse(String input) throws DukeException {
        String[] arr = input.split(" ");

        if (arr[0].equals("bye")) {
            return EventType.BYE;
        }
        if (arr[0].equals("list")) {
            return EventType.LIST;
        }
        if (arr[0].equals("mark")) {
            return EventType.MARK;
        }
        if (arr[0].equals("unmark")) {
            return EventType.UNMARK;
        }
        if (arr[0].equals("delete")) {
            return EventType.DELETE;
        }
        if (arr[0].equals("todo")) {
            return EventType.TODO;
        }
        if (arr[0].equals("deadline")) {
            return EventType.DEADLINE;
        }
        if (arr[0].equals("event")) {
            return EventType.EVENT;
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
