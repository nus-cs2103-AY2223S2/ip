package duke.dukeExceptions;

public class EventException extends DukeException {
    /**
     * Exception used by event task to indicate invalid user input when creating event task
     */

    public EventException() {
        super("☹ OOPS!!! The description of a event cannot be empty.");
    }
}
