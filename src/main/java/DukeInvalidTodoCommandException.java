public class DukeInvalidTodoCommandException extends DukeException {

    DukeInvalidTodoCommandException() {
        super("Usage: todo <task name>");
    }
}
