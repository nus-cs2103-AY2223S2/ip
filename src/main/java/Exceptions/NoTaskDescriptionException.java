package exceptions;

/**
 * This class is an exception thrown for missing task name
 */
public class NoTaskDescriptionException extends DukeException {

    /**
     * Creates an exception of Duke receiving a command to
     * add a task without a task name
     *
     * @param string
     */
    public NoTaskDescriptionException(String string) {
        super("OOPS!!! The description of a " + string + " task cannot be empty!");
    }
}
