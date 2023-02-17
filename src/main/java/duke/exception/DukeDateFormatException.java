package duke.exception;

import duke.exception.DukeException;

public class DukeDateFormatException extends DukeException {
    private static final String INVALID_DATE_FORMAT_MESSAGE = "Date is an invalid format! Should be yyyy-MM-dd HH:mm";
    public DukeDateFormatException() {
        super(INVALID_DATE_FORMAT_MESSAGE);
    }
}
