package duke.Exceptions;

public class IncorrectFormatException extends NeroException {

    public IncorrectFormatException() {
        super("Add a task description and deadline in yyyy-mm-dd format!!!");
    }
}
