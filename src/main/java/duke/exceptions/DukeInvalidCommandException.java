package duke.exceptions;

/**
 * The exception that arises from an invalid user command.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DukeInvalidCommandException extends DukeException{
    /**
     * The default constructor for this exception.
     */
    public DukeInvalidCommandException() {
        super("I don't know that command yet. Give me another!");
    }
}
