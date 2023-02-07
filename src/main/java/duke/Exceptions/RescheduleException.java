package duke.Exceptions;

public class RescheduleException extends NeroException{

    public RescheduleException() {
        super("For Deadline: reschedule taskNum by yyyy-mm-dd \n " +
                "For Event: reschedule taskNum from yyyy-mm-dd to yyyy-mm-dd");
    }
}
