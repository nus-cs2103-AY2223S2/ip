package berry.exception;

/**
 * Signals that some given data in the clause input is missing.
 */
public class MissingClauseException extends BerryException {

    public MissingClauseException(String clause) {
        super("You have to include your '" + clause + "' clause ><");
    }
}
