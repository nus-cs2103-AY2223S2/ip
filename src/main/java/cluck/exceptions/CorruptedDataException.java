package cluck.exceptions;

/**
 * The type Corrupted data exception is thrown when date from saved file is not in the right format.
 */
public class CorruptedDataException extends CluckException {
    /**
     * Instantiates a new Corrupted data exception.
     *
     * @param errorMessage the error message
     */
    public CorruptedDataException(String errorMessage) {
        super(errorMessage);
    }
}
