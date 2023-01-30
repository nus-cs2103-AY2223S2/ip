package sam.storage;

import sam.SamException;

/**
 * A SamException thrown when an error occurs during saving the task list.
 */
public class SamSaveFailedException extends SamException {
    public SamSaveFailedException() {
        super("Oh no, there was a problem saving your list!");
    }
}
