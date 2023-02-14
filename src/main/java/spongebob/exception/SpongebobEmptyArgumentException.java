package spongebob.exception;

/**
 * Exception that occur in Duke to indicate user passing an empty argument for the command.
 */
public class SpongebobEmptyArgumentException extends SpongebobException {
    public SpongebobEmptyArgumentException(String msg) {
        super(msg);
    }
}
