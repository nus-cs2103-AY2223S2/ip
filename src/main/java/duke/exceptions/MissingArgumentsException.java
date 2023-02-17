package duke.exceptions;

/** An Exception class that informs user about missing arguments in a command */
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
