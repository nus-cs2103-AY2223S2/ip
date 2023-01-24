package duke.exceptions;

//An exception for the case that a due date was not specified in a 'deadline' command.
public class DeadlineByNotSpecified extends TaskInitError {

    /**
     * Constructor method.
     * @param errorMessage Error message to display to user
     */
    public DeadlineByNotSpecified(String errorMessage) {
        super(errorMessage);
    }
}
