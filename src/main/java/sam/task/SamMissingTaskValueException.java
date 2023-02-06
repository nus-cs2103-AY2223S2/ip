package sam.task;

import sam.SamException;
import sam.Ui;

/**
 * A SamException thrown when a an argument from the user input
 * is missing its value.
 */
public class SamMissingTaskValueException extends SamException {
    public SamMissingTaskValueException() {
        super(Ui.Dialog.MISSING_TASK_VALUE.getDialog());
    }
}
