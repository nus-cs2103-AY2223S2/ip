package duke.dukeexceptions;

/**
 * Exception when the user inputs an invalid argument in the command.
 */
public class UnknownCommandException extends DukeExceptions {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }

}
