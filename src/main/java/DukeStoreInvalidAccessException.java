import java.lang.Exception;

/**
 * The exception class that indicates that an invalid index request was made to
 * a DukeStore instance.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DukeStoreInvalidAccessException extends Exception {
    public DukeStoreInvalidAccessException() {
        super("Invalid index entered. Please try again");
    }
}
