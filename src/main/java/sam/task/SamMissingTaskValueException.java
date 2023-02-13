package sam.task;

import sam.Dialog;
import sam.SamException;

/**
 * A SamException thrown when a an argument from the user input
 * is missing its value.
 */
public class SamMissingTaskValueException extends SamException {
    public SamMissingTaskValueException() {
        super(Dialog.MISSING_TASK_VALUE.getDialog());
    }
}
