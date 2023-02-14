package spongebob.exception;

/**
 * Throws when user enter invalid argument for the command.
 */
public class SpongebobInvalidArgumentException extends SpongebobException {
    public SpongebobInvalidArgumentException(String msg) {
        super(msg);
    }
}
