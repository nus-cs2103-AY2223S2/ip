package dudu.exception;

/**
 * Exception class for duplicates
 */
public class DuplicateException extends DuduException {
    public DuplicateException() {
        super("duplicate task");
    }

    @Override
    public String toString() {
        return "OPPS!!! There has a similar task in your task list";
    }
}
