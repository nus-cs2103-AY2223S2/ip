package sam.task;

import sam.SamException;

/**
 * A SamException thrown when a required task title
 * is missing from the user input.
 */
public class SamMissingTaskTitleException extends SamException {
    public SamMissingTaskTitleException() {
        super("Oops, you forgot a title for your task!");
    }
}
