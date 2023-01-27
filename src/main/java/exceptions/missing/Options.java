package exceptions.missing;

/**
 * Thrown when user misses out an option.
 */
public class Options extends exceptions.DukeException {
    /**
     * Constructs an Invalid Options Exception for the given TaskType.
     *
     * Example: Deadline [Description] /by [Parameter]
     * Missing `/by` will lead to this error message.
     *
     *  @param taskType The task type
     */
    public Options(String taskType) {
        super(String.format("%s Missing or incomplete options(s) for %s", OOPS, taskType));
    }
}
