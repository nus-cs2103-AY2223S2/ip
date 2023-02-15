package spongebob.exception;

/**
 * Throws if user have repeated certain command before.
 */
public class SpongebobRepeatedCommandException extends SpongebobException {
    public SpongebobRepeatedCommandException(String msg) {
        super(msg);
    }
}
