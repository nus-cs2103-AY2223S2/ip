package sam.parser;

import sam.SamException;
import sam.Ui;
/**
 * A SamException thrown when an error occurs during parsing an integer.
 */
public class SamInvalidIntException extends SamException {
    public SamInvalidIntException() {
        super(Ui.Dialog.INVALID_INT.getDialog());
    }
}
