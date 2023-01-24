package duke.exceptions;

// An exception for the case that a from/to date was not specified in an 'event' command.
public class EventFromToNotSpecified extends TaskInitError {

    /**
     * Constructor method.
     * @param errorMessage Error message to display to user
     */
    public EventFromToNotSpecified(String errorMessage) {
        super(errorMessage);
    }
}
