package duke.exceptions;

public class DukeInvalidTodoCommandException extends DukeException {

    public DukeInvalidTodoCommandException() {
        super("Usage: todo <task name>");
    }
}
