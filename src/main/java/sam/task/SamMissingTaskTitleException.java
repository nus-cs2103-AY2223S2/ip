package sam.task;

import sam.SamException;
import sam.Ui;

/**
 * A SamException thrown when a required task title
 * is missing from the user input.
 */
public class SamMissingTaskTitleException extends SamException {
    public SamMissingTaskTitleException() {
        super(Ui.Dialog.MISSING_TASK_TITLE.getDialog());
    }
}
