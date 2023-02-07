package duke.model;

public class ExecutionResult {

    private String message;
    private boolean exitStatus;

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
