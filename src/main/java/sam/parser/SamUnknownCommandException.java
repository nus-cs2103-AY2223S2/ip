package sam.parser;

import sam.SamException;
import sam.ui.Dialog;

/**
 * A SamException thrown when the user gives an unknown command.
 */
public class SamUnknownCommandException extends SamException {
    public SamUnknownCommandException() {
        super(Dialog.UNKNOWN_COMMAND.getDialog());
    }
}
