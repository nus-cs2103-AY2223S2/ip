package dukeexception;

/**
 * Exception for handling invalid ids in task lists.
 */
public class NotFoundException extends DukeException {
    public NotFoundException(String source, String reason, Throwable err) {
        super(String.format("Haiya nothing here in the %s lah. \n%s", source, reason), err);
    }
}
