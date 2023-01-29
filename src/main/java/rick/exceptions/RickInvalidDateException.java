package rick.exceptions;

/**
 * The exception that arises when an invalid date format was entered.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class RickInvalidDateException extends RickException {
    /**
     * The default constructor for this exception.
     */
    public RickInvalidDateException() {
        super(
            "An invalid date was entered. Please use this format:\n"
            + "{day}/{month}/{year} {hour}{minute}\n"
            + "Example: 2/2/23 1200"
        );
    }
}
