package berry.exception;

public class EmptyClauseException extends BerryException {
    public EmptyClauseException(String clause) {
        super("You cannot leave your '" + clause + "' clause empty ><");
    }
}
