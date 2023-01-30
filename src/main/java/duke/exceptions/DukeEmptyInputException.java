package duke.exceptions;

public class DukeEmptyInputException extends DukeException {
    public DukeEmptyInputException() {
        super("Some fields appear empty. Please enter actual inputs.");
    }
}
