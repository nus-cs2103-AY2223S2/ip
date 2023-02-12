package duke.exceptions;

public class CommandException extends DukeException {
    public CommandException(String errorMessage) {
        super("Command error: " + errorMessage);
    }
}
