package duke;

public class EmptyTodoException extends DukeException {
    /**
     * Constructor
     */
    public EmptyTodoException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
