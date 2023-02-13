package sam.task;

import sam.SamException;
import sam.ui.Dialog;

/**
 * A SamException thrown when a required task id
 * is missing from the user input.
 */
public class SamMissingTaskException extends SamException {
    public SamMissingTaskException() {
        super(Dialog.MISSING_TASK.getDialog());
    }
}
