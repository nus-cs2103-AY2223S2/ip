package berry.exception;

/**
 * Signals that the given string as date is not in the acceptable format.
 */
public class IncorrectDateException extends BerryException {

    public IncorrectDateException() {
        super("urm... ₍ ´• ˕ •` ₎ could I get you to key in your valid date in the format YYYY-MM-DD ?");
    }
}