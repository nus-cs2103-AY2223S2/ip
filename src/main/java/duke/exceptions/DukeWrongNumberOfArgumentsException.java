package duke.exceptions;

public class DukeWrongNumberOfArgumentsException extends DukeInvalidCommandSyntaxException {
    public DukeWrongNumberOfArgumentsException(String errorMessage) {
        super(errorMessage);
    }
}
