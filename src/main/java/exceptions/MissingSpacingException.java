package exceptions;

public class MissingSpacingException extends WessyException {
    public MissingSpacingException(String cmd) {
        super(String.format("The spacing after '%s' is missing.", cmd));
    }
}
