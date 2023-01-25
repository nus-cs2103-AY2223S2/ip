package duke.exceptions;

import java.lang.Exception;

/**
 * The main exception class for this app.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DukeException extends Exception{
    /**
     * Constructs the exception with the given message.
     * @param message The given message.
     */
    public DukeException(String message) {
        super(message);
    }
}
