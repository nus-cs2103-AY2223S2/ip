package exceptions;

/**
 * Exception for when Duke encounters an unknown commands not recognisable from Commands Enum class.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
