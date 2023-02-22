package exceptions;

public class DukeTodoNoDescription extends DukeException{

    public DukeTodoNoDescription() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
