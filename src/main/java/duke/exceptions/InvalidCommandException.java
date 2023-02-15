package duke.exceptions;

/** An Exception class that informs user about an invalid command entered */
public class InvalidCommandException extends DukeException {
    /**
     * Initialize an duke.exceptions.InvalidCommandException exception, which represents
     * the error that the command is not valid.
     *
     * @return A duke.exceptions.InvalidCommandException exception
     */
    public InvalidCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-( Double check your spellings or spaces");
    }
}
