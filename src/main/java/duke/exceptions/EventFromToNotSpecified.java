package duke.exceptions;

/** An exception for the case that a from/to date was not specified in an 'event' command. */
public class EventFromToNotSpecified extends MissingCommandArguments {
    /**
     * Constructor method.
     * 
     * @param errorMessage Error message to display to user
     */
    public EventFromToNotSpecified() {
        super("Missing '/from' or '/to' keyword.\n"
                + "Command format: 'event <task_name> /from <start_date> /to <end_date>");
    }
}
