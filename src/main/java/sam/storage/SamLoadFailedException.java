package sam.storage;

import sam.SamException;

/**
 * A SamException thrown when an error occurs during loading the task list.
 */
public class SamLoadFailedException extends SamException {
    public SamLoadFailedException() {
        super("Oh no, there was a problem loading your list!");
    }
}
