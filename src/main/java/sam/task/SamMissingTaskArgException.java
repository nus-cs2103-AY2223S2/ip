package sam.task;

import sam.SamException;
import sam.Ui;

/**
 * A SamException thrown when a required task argument
 * is missing from the user input.
 */
public class SamMissingTaskArgException extends SamException {
    public SamMissingTaskArgException() {
        super(Ui.Dialog.MISSING_TASK_ARG.getDialog());
    }
}
