package duke;

public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super("OOPS!!! Invalid input ><\nInput should be: event <task name> /from <start> /to <end>");
    }
}
