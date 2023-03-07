package sam.task;

import sam.SamException;
import sam.ui.Dialog;

/**
 * A SamException thrown when a an argument from the user input
 * is missing its value.
 */
public class SamMissingTaskValueException extends SamException {
    public SamMissingTaskValueException() {
        super(Dialog.MISSING_TASK_VALUE.getDialog());
    }
}
