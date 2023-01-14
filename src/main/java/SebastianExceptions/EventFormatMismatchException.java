package SebastianExceptions;

import Utilities.Utilities;

public class EventFormatMismatchException extends InputFormatMismatchException{
    public EventFormatMismatchException() {
        super(
                "Please specify a deadline in the following format: " + "\n" +
                        Utilities.space() + "event [event] /from [start_time] /to [end_time]"
        );
    }
}
