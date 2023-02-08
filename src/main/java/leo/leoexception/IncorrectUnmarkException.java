package leo.leoexception;

/**
 * Represents an exception when an unmarked Task is set to be unmark.
 */
public class IncorrectUnmarkException extends LeoException {

    public IncorrectUnmarkException() {
        super("This task has not been marked.");
    }

}
