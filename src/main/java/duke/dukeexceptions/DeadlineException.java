package duke.dukeexceptions;

public class DeadlineException extends DukeException {
    public DeadlineException() {
        super("☹ OOPS!!! The description of a deadline cannot be empty.");
    }
}
