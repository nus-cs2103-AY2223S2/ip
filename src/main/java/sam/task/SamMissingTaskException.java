package sam.task;

import sam.Dialog;
import sam.SamException;

/**
 * A SamException thrown when a required task id
 * is missing from the user input.
 */
public class SamMissingTaskException extends SamException {
    public SamMissingTaskException() {
        super(Dialog.MISSING_TASK.getDialog());
    }
}
