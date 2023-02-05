package sam.parser;

import sam.SamException;
import sam.Ui;

/**
 * A SamException thrown when the user gives an unknown command.
 */
public class SamUnknownCommandException extends SamException {
    public SamUnknownCommandException() {
        super(Ui.Dialog.UNKNOWN_COMMAND.getDialog());
    }
}
