package duke.exceptions;

/**
 * The exception that arises when an invalid task was entered.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DukeInvalidTaskException extends DukeException {
    /**
     * The default constructor for this exception.
     */
    public DukeInvalidTaskException() {
        super("I don't know what that means yet. Give me another task.");
    }
}
