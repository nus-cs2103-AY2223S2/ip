package duke;

public class BadCommandException extends Exception {
    public BadCommandException(String errMsg) {
        super(errMsg);
    }
}
