package exceptions;

public class WessyException extends Exception {
    private final String message;
    static String OPENING = "☹ OOPS!!! ";

    protected WessyException(String str) {
        this.message = OPENING + str;
    }

    @Override
    public String toString() {
        return message;
    }
}
