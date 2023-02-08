package leo.leoexception;

/**
 * Represents an exception when duration is not complete in an Event Task.
 */
public class IncompleteDurationException extends LeoException {

    public IncompleteDurationException() {
        super("Uh oh! The duration of the event is incomplete :-(");
    }

}
