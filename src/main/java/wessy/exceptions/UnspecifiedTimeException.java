package wessy.exceptions;

public class UnspecifiedTimeException extends WessyException {
    public UnspecifiedTimeException(String keyword, boolean isBefore) {
        super("The input " + (isBefore ? "before '" : "after '") + keyword + "' is missing.");
    }
}
