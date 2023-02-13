package duke;

/**
 * DukeException is used to define exceptions that can be encountered when running Duke
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Returns a String description of a duke exception
     *
     * @return String description of a duke exception.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
