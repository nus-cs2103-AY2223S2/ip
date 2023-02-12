package wessy.exceptions;

public abstract class WessyException extends Exception {
    private static final String OPENING = "☹ OOPS!!! ";
    private final String message;

    protected WessyException(String str) {
        this.message = OPENING + str;
    }

    @Override
    public String toString() {
        return message;
    }
}
