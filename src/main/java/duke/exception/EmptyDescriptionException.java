package duke.exception;

public class EmptyDescriptionException extends Exception{
    public EmptyDescriptionException() {
        super("☹ OOPS!!! The description of a task cannot be empty.");
    }

}
