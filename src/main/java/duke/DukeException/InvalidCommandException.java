package duke.DukeException;

public class InvalidCommandException extends Exception {

    private static final String HELP = " View all tasks with `list`";

    public static final String TASK_NOT_FOUND_EXCEPTION = "[ERROR] : No such task." + HELP;
    public static final String MARK_FORMAT_EXCEPTION = "[ERROR] : Mark/unmark tasks with task number." 
                                                        + HELP;
    public static final String NAME_FORMAT_EXCEPTION = "[ERROR] : Missing task name. ";
    public static final String ARG_FORMAT_EXCEPTION = "[ERROR] Please specify date and time. ";
    public static final String NULL_DELETE_EXCEPTION = "[ERROR] Missing task name to delete. Please specify a task."
                                                        + HELP;
    public static final String DELETE_FORMAT_EXCEPTION = "[ERROR] Delete task with task number."
                                                        + HELP;
    public String errorMessage;
    
    /**
     * Public constructor for InvalidCommandException
     * 
     * @param err Error message
     */
    public InvalidCommandException(String err) {
        super(err);
        this.errorMessage = err;
    }
}