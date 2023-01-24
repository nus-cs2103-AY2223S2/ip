package exceptions.invalid;

public class Command extends exceptions.DukeException {
    /**
     * Constructs an Invalid Command Exception.
     * Informs user of invalid command.
     */
    public Command() {
        super(String.format("%s I'm sorry, but I don't know what you just said means :-(", OOPS));
    }
}
