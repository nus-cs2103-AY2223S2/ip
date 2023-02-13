package sam.parser;

import sam.Dialog;
import sam.SamException;

/**
 * A SamException thrown when the user gives an unknown command.
 */
public class SamUnknownCommandException extends SamException {
    public SamUnknownCommandException() {
        super(Dialog.UNKNOWN_COMMAND.getDialog());
    }
}
