package wessy.exceptions.integer;

import wessy.exceptions.WessyException;

public class InvalidIntegerException extends WessyException {
    public InvalidIntegerException(String cmd, int taskNum, int total) {
        super(String.format("You can't %s Task %d as you only have %d tasks on the list", cmd, taskNum, total));
    }
}
