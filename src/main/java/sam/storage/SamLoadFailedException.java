package sam.storage;

import sam.SamException;
import sam.ui.Dialog;

/**
 * A SamException thrown when an error occurs during loading the task list.
 */
public class SamLoadFailedException extends SamException {
    public SamLoadFailedException() {
        super(Dialog.LOAD_FAILED.getDialog());
    }
}
