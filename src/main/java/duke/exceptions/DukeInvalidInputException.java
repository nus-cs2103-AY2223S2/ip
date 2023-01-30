package duke.exceptions;

public class DukeInvalidInputException extends DukeException {
    public DukeInvalidInputException(String errorMessage) {
        super(String.format("That's an invalid input. %s", errorMessage));
    }
}
