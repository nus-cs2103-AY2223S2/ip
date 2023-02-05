package sam.parser;

import sam.SamException;
import sam.Ui;

/**
 * A SamException thrown when an error occurs during parsing a date.
 */
public class SamInvalidDateException extends SamException {
    public SamInvalidDateException() {
        super(Ui.Dialog.INVALID_DATE.getDialog());
    }
}
