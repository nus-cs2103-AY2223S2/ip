package sam.task;

import sam.SamException;
import sam.Ui;

/**
 * A SamException thrown when a required task id
 * is missing from the user input.
 */
public class SamMissingTaskException extends SamException {
    public SamMissingTaskException() {
        super(Ui.Dialog.MISSING_TASK.getDialog());
    }
}
