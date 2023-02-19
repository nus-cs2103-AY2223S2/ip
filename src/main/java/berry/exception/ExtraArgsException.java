package berry.exception;

/**
 * Signals that the given string as date is not in the acceptable format.
 */
public class ExtraArgsException extends BerryException {

    public ExtraArgsException(String command) {
        super("Please remove any arguments after your '" + command + "' command ><");
    }
}
