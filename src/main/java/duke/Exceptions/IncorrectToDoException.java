package duke.Exceptions;

public class IncorrectToDoException extends NeroException {

    public IncorrectToDoException() {
        super("Description cannot be empty!!!");
    }
}
