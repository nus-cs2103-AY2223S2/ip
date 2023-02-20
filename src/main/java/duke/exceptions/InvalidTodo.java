package duke.exceptions;

public class InvalidTodo extends DukeExceptions {
    public InvalidTodo() {
        super("ToDo invalid. Please fill in your todo properly eg. todo [description]");
    }
}