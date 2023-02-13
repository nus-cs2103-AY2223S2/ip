package sam.task;

import sam.Dialog;
import sam.SamException;

/**
 * A SamException thrown when a required task argument
 * is missing from the user input.
 */
public class SamMissingTaskArgException extends SamException {
    public SamMissingTaskArgException() {
        super(Dialog.MISSING_TASK_ARG.getDialog());
    }
}
