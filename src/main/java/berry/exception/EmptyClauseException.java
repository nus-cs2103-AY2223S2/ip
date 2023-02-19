package berry.exception;

/**
 * Signals that some given data in the clause input is empty.
 */
public class EmptyClauseException extends BerryException {

    public EmptyClauseException(String clause) {
        super("You cannot leave your '" + clause + "' clause empty ><");
    }
}
