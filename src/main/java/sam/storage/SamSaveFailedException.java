package sam.storage;

import sam.SamException;
import sam.ui.Dialog;

/**
 * A SamException thrown when an error occurs during saving the task list.
 */
public class SamSaveFailedException extends SamException {
    public SamSaveFailedException() {
        super(Dialog.SAVE_FAILED.getDialog());
    }
}
