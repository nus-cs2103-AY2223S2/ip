package duke.exceptions;

/** Parent of any exceptions related to executing a command. */
public class CommandExecutionError extends Exception {
    /**
     * Constructor method.
     * 
     * @param errorMessage Error message to display to user
     */
    public CommandExecutionError(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Craft string representation of the exception.
     * 
     * @return String representation of exception, without its class name
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
