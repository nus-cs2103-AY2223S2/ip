package sam.parser;

import sam.SamException;
import sam.ui.Dialog;

/**
 * A SamException thrown when a non-existing task is retrieved.
 */
public class SamInvalidTaskException extends SamException {
    public SamInvalidTaskException() {
        super(Dialog.INVALID_TASK.getDialog());
    }
}
