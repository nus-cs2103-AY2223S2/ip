package duke.util.container;

/**
 * Encapsulates result of executing a command.
 * <p>
 * Each executed command will generate a message and an exit status.
 */
public class ExecutionResult {

    private String message;
    private boolean isExit;

    /**
     * Creates a new {@code ExecutionResult} instance.
     *
     * @param message the message generated after executing a command
     * @param isExit the exit status generated after executing a command
     */
    public ExecutionResult(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    public String getMessage() {
        return message;
    }

    public boolean isExit() {
        return isExit;
    }
}
