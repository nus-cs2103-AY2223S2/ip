package duke;

public class InvalidTodoException extends DukeException {
    public InvalidTodoException() {
        super("OOPS!!! Invalid input ><\nInput should be: todo <task name>");
    }
}
