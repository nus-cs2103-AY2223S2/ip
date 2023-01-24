package duke.exceptions;

// Parent of any exceptions related to initializing a task. 
public class TaskInitError extends Exception {

    /**
     * Constructor method.
     * @param errorMessage Error message to display to user
     */
    public TaskInitError(String errorMessage) {
        super(errorMessage);
    }
}