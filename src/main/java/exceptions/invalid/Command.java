package exceptions.invalid;

/**
 * Thrown when user attempts to enter an unknown command.
 */
public class Command extends exceptions.DukeException {
    /**
     * This method constructs an Invalid Command Exception.
     * Informs user of invalid command.
     */
    public Command() {
        super(String.format("%s I'm sorry, but I don't know what you just said means :-(", OOPS));
    }
}
