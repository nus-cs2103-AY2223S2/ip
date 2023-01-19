package DukeExceptions;

public class DukeTooManyInputsException extends DukeInvalidInputException {
    public DukeTooManyInputsException() {
        super("Sorry you need to specify a single input.");
    }
}
