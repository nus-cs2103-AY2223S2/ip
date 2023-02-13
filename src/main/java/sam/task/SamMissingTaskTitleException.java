package sam.task;

import sam.Dialog;
import sam.SamException;

/**
 * A SamException thrown when a required task title
 * is missing from the user input.
 */
public class SamMissingTaskTitleException extends SamException {
    public SamMissingTaskTitleException() {
        super(Dialog.MISSING_TASK_TITLE.getDialog());
    }
}
