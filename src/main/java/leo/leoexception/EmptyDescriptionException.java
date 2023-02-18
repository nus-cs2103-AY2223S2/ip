package leo.leoexception;

/**
 * Represents an exception when no description is given in a Task.
 */
public class EmptyDescriptionException extends LeoException {

    public EmptyDescriptionException() {
        super("Uh oh! Description of task is empty :-(");
    }
}
