package duke.exceptions;

// An exception for the case that a task name was not specified when creating a task.
public class TaskNameNotSpecified extends TaskInitError {

    /**
     * Constructor method.
     * @param errorMessage Error message to display to user
     */
    public TaskNameNotSpecified(String errorMessage) {
        super(errorMessage);
    }
}