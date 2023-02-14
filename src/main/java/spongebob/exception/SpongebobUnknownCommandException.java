package spongebob.exception;

/**
 * Exception will be thrown when unknown command is given by the user.
 */
public class SpongebobUnknownCommandException extends SpongebobException {
    public SpongebobUnknownCommandException(String msg) {
        super(msg);
    }
}
