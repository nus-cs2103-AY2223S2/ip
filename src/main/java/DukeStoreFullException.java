/**
 * The exception class that indicates the Duke Store instance is full.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DukeStoreFullException extends DukeException{
    public DukeStoreFullException() {
        super("Rick is busy and can't take any more tasks");
    }
}
