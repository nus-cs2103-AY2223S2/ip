package berry.exception;

public class MissingClauseException extends BerryException {

    public MissingClauseException(String clause) {
        super("You have to include your '" + clause +  "' clause ><");
    }
}
