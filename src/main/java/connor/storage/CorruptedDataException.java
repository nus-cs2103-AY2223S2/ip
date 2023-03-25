package connor.storage;

/**
 * Exception to indicate that information in Memory is unreadable.
 */
public class CorruptedDataException extends Exception {

    public CorruptedDataException() {
        super("DATA IS CORRUPTED! PLEASE TRY AGAIN");
    }
}
