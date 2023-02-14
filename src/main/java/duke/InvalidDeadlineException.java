package duke;

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("OOPS!!! Invalid input ><\nInput should be: deadline <task name> /by <deadline>");
    }
}
