package rick.exceptions;

/**
 * The exception that arises when an invalid store index was entered.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class RickTaskIndexMissingException extends RickException {

    /**
     * Given a command type which accesses the store, formats the exception accordingly.
     *
     * @param type The command type.
     */
    public RickTaskIndexMissingException(String type) {
        super(String.format(
                "An index was not provided for the %s action. Usage: %s {index}",
                type, type
        ));
    }
}
