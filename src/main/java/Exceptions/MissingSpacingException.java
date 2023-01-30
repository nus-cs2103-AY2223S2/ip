package Exceptions;

public class MissingSpacingException extends WessyException {
    public MissingSpacingException(String keyword, boolean after) {
        super(String.format("The spacing %s '%s' is missing", after ? "after" : "before", keyword));
    }
}
