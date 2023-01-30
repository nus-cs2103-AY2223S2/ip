package sam.task;

import sam.SamException;

/**
 * A SamException thrown when a required task argument
 * is missing from the user input.
 */
public class SamMissingTaskArgException extends SamException {
    public SamMissingTaskArgException() {
        super("Oops, you're missing an argument!");
    }
}
