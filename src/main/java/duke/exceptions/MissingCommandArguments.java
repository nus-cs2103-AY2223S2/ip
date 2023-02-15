package duke.exceptions;

/** Parent of any exceptions related to missing information from user for a command. */
public class MissingCommandArguments extends CommandExecutionError {
    /**
     * Constructor method.
     *
     * @param errorMessage Error message to display to user
     */
    public MissingCommandArguments(String errorMessage) {
        super(errorMessage);
    }
}
