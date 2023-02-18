package leo.leoexception;

/**
 * Represents an exception when no date is entered to view schedule.
 */
public class NoDateFoundException extends LeoException {

    public NoDateFoundException() {
        super("No date was entered to view scheudle.");
    }
}
