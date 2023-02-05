package sam.storage;

import sam.SamException;
import sam.Ui;

/**
 * A SamException thrown when an error occurs during saving the task list.
 */
public class SamSaveFailedException extends SamException {
    public SamSaveFailedException() {
        super(Ui.Dialog.SAVE_FAILED.getDialog());
    }
}
