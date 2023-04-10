package rick.exceptions;

/**
 * Represents the exception that arises from an invalid user command.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class RickInvalidCommandException extends RickException {
    /**
     * Constructs the exception instance.
     */
    public RickInvalidCommandException() {
        super("I don't know that command yet. Give me another!");
    }
}
