package exceptions;

public class TimeSpecifierException extends WessyException {
    public TimeSpecifierException(String specifier) {
        super("Either '" + specifier + "' is missing, or it is used in the wrong format.");
    }
}
