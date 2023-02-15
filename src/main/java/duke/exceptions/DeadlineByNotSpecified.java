package duke.exceptions;

/** An exception for the case that a due date was not specified in a 'deadline' command. */
public class DeadlineByNotSpecified extends MissingCommandArguments {
    /**
     * Constructor method.
     *
     * @param errorMessage Error message to display to user
     */
    public DeadlineByNotSpecified() {
        super("Missing '/by' keyword.\nCommand Format: 'deadline <task_name> /by <due_date>'");
    }
}
