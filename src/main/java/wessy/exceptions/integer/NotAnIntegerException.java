package wessy.exceptions.integer;

import wessy.exceptions.WessyException;

public class NotAnIntegerException extends WessyException {
    public NotAnIntegerException() {
        super("What you just input is not an integer. Please input an integer.");
    }
}
