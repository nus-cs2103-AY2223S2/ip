package duke.exceptions;

public class MissingArgumentsException extends DukeException {
    /**
     * Initialize a MissingArguments exception, which represents
     * the error that there are not enough arguments to a command
     *
     * @return A MissingArguments exception
     */
    public MissingArgumentsException() {
        super("OOPS! Missing arguments to the command.");
    }
}
