package Exceptions;

public class EmptyTodoException extends DukeException {
    public EmptyTodoException(String e) {
        super("Hey! The description of a todo cannot be empty!");
    }
}
