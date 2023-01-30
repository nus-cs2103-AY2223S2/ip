package sam.task;

import sam.SamException;

/**
 * A SamException thrown when a an argument from the user input
 * is missing its value.
 */
public class SamMissingTaskValueException extends SamException {
    public SamMissingTaskValueException() {
        super("Oops, an argument is missing a value!");
    }
}
