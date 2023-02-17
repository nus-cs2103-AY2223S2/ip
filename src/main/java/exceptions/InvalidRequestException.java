package exceptions;

/**
 * Duke Exception for users error when inputing command
 */
public class InvalidRequestException extends ArrayIndexOutOfBoundsException {
    public InvalidRequestException(String err) {
        super(err);
    }
}
