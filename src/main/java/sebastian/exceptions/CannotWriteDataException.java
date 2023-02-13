package sebastian.exceptions;

/**
 * Exception that data cannot be written to tge hard disk
 */
public class CannotWriteDataException extends SebastianException {
    /**
     * Constructor
     */
    public CannotWriteDataException() {
        super("Task cannot be saved to disk, exiting the program will cause data to be lost");
    }
}
