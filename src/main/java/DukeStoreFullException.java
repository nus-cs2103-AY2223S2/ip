import java.lang.Exception;

/**
 * The exception class that indicates the Duke Store instance is full.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DukeStoreFullException extends Exception{
    public DukeStoreFullException() {
        super("SOCCat is full and has run out of capacity");
    }
}
