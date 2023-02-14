package duke.exceptions;

/**
 * Exception is thrown when format for rescheduling is incorrect
 */
public class RescheduleException extends NeroException{

    public RescheduleException() {
        super("For Deadline: reschedule taskNum by yyyy-mm-dd \n " +
                "For Event: reschedule taskNum from yyyy-mm-dd to yyyy-mm-dd");
    }
}
