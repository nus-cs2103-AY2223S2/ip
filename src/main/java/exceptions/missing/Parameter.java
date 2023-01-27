package exceptions.missing;

/**
 * Thrown when user misses out a parameter.
 */
public class Parameter extends exceptions.DukeException {
    /**
     * Constructs a Missing Parameter Exception for the given TaskType
     * @param taskType The task type
     */
    public Parameter(String taskType) {
        super(String.format("%s Missing parameter(s) for %s", OOPS, taskType));
    }
}
