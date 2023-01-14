package SebastianExceptions;

import Utilities.Utilities;

public class DeadlineFormatMismatchException extends InputFormatMismatchException{
    public DeadlineFormatMismatchException() {
        super(
                "Please specify a deadline in the following format:" + "\n" +
                Utilities.space() + "deadline [deadline] /by [end_time]"
        );
    }
}
