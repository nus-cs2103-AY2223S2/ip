package roody.exceptions;

/**
 * A custom exception for missing task
 */
public class TaskNotFoundException extends RoodyException {
    public TaskNotFoundException() {
        super("Sorry, that task doesn't exist");
    }
}
