package exceptions.num_of_input_exceptions;

import exceptions.WessyException;

public class TooManyInputException extends WessyException {
    public TooManyInputException(String cmd) {
        super(String.format("The '%s' command only takes in 1 input.", cmd));
    }

}
