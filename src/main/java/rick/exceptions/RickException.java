package rick.exceptions;

import java.lang.Exception;

/**
 * The main exception class for this app.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class RickException extends Exception{
    /**
     * Constructs the exception with the given message.
     * @param message The given message.
     */
    public RickException(String message) {
        super(message);
    }
}
