package sam.task;

import sam.SamException;
import sam.ui.Dialog;

/**
 * A SamException thrown when a required task argument
 * is missing from the user input.
 */
public class SamMissingTaskArgException extends SamException {
    public SamMissingTaskArgException() {
        super(Dialog.MISSING_TASK_ARG.getDialog());
    }
}
