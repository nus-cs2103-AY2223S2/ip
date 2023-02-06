package sam.parser;

import sam.SamException;
import sam.Ui;

/**
 * A SamException thrown when a non-existing task is retrieved.
 */
public class SamInvalidTaskException extends SamException {
    public SamInvalidTaskException() {
        super(Ui.Dialog.INVALID_TASK.getDialog());
    }
}
