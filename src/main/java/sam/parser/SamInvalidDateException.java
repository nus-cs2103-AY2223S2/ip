package sam.parser;

import sam.SamException;
import sam.ui.Dialog;

/**
 * A SamException thrown when an error occurs during parsing a date.
 */
public class SamInvalidDateException extends SamException {
    public SamInvalidDateException() {
        super(Dialog.INVALID_DATE.getDialog());
    }
}
