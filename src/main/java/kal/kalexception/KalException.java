package kal.kalexception;

/**
 * This class handles exceptions pertaining to Kal.
 */
public class KalException extends Exception {
    /**
     * Creates a KalException object.
     *
     * @param message The error message of this exception.
     */
    public KalException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
