package Exceptions;

public class UnspecifiedTimeException extends WessyException {
    public UnspecifiedTimeException(String keyword) {
        super(String.format("The '%s' timing is missing.", keyword));
    }
}
