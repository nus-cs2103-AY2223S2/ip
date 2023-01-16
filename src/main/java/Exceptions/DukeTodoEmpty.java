package Exceptions;

public class DukeTodoEmpty extends DukeException {

    public DukeTodoEmpty() {
        super("Hey! The description of a todo cannot be empty!");
    }

}
