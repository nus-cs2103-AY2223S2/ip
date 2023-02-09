package duke.exceptions;

/**
 * DukeWrongNumberOfArgumentsException indicates that the command is valid, but the wrong number of arguments
 * is supplied.
 */
public class DukeWrongNumberOfArgumentsException extends DukeException {
    public DukeWrongNumberOfArgumentsException(String errorMessage) {
        super(errorMessage);
    }
}
