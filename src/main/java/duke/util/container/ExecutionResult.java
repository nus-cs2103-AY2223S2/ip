package duke.util.container;

/**
 * Encapsulates result of executing a command.
 * <p>
 * Each executed command will generate a message and an exit status.
 */
public class ExecutionResult {

    private String message;
    private boolean exitStatus;

    /**
     * Creates a new {@code ExecutionResult} instance.
     *
     * @param message the message generated after executing a command
     * @param exitStatus the exit status generated after executing a command
     */
    public ExecutionResult(String message, boolean exitStatus) {
        this.message = message;
        this.exitStatus = exitStatus;
    }

    public String getMessage() {
        return message;
    }

    public boolean getExitStatus() {
        return exitStatus;
    }
}
