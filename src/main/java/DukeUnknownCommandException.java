/**
 * DukeUnknownCommandException indicates that the command has a valid syntax, but does not
 * correspond to any known commands in the program.
 */
public class DukeUnknownCommandException extends DukeException {
    public DukeUnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
}

