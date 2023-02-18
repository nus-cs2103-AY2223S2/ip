package exceptions;

/**
 * This class is an exception for Duke finding insufficient arguments for task creation
 */
public class InsufficientArgumentsException extends DukeException {

    /**
     * Creates an exception to throw if insufficient arguments (separated by '/') are supplied
     * 
     * @param taskString type of task as a string ("todo", "deadline", "event")
     */
    public InsufficientArgumentsException(String taskString) {
        super("Insufficient arguments supplied for " + taskString + "!\n" + taskString
            + "requires " + (taskString.equalsIgnoreCase("todo")
                            ? "a name"
                            : (taskString.equalsIgnoreCase("deadline")
                                ? "a name and an end date"
                                : (taskString.equalsIgnoreCase("event")
                                    ? "a name, a start date and an end date"
                                    : "ERROR! THIS SHOULD NOT BE PRINTED!"))));
    }
}
