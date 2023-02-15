package spongebob.exception;

/**
 * Exception that occur in Duke to indicate user passing an empty argument for the command.
 */
public class SpongebobEventOverlapException extends SpongebobException {
    public SpongebobEventOverlapException(String msg) {
        super(msg);
    }
}
