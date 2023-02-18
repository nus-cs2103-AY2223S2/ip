package exceptions;

/**
 * This class is an exception for Duke finding insufficient arguments for task creation
 */
public class InsufficientArgumentsException extends DukeException {
    public InsufficientArgumentsException(String taskString) {
        super("Insufficient arguments supplied for " + taskString + "!\n" + taskString 
            + "requires" + (taskString.equalsIgnoreCase("todo")
                            ? "a name"
                            : taskString.equalsIgnoreCase("deadline")
                                ? "a name and an end date"
                                : "a name, a start date and an end date"));
    }
}
