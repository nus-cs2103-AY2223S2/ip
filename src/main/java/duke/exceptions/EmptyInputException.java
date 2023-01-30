package duke.exceptions;

public class EmptyInputException extends DukeException {
    public EmptyInputException() {
        super("The input cannot be empty.");
    }
}
