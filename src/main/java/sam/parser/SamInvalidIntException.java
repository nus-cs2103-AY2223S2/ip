package sam.parser;

import sam.Dialog;
import sam.SamException;
/**
 * A SamException thrown when an error occurs during parsing an integer.
 */
public class SamInvalidIntException extends SamException {
    public SamInvalidIntException() {
        super(Dialog.INVALID_INT.getDialog());
    }
}
