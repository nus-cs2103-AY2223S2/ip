package duke;

/**
 * Represents a response sent by duke in reaction to input.
 */
public class DukeResponse {
    private Boolean shouldExit;
    private String message;

    /**
     * Constructs a DukeResponse with the given arguments.
     *
     * @param message  message to the application being responded to
     * @param shouldExit indicating if the application being responded to should exit
     */
    public DukeResponse(String message, Boolean shouldExit) {
        this.message = message;
        this.shouldExit = shouldExit;
    }

    /**
     * Constructs a DukeResponse with the given message. shouldExit() will default to false.
     *
     * @param message  message to the application being responded to
     */
    public DukeResponse(String message) {
        this.message = message;
        this.shouldExit = false;
    }

    public Boolean shouldExit() {
        return shouldExit;
    }

    public String getMessage() {
        return message;
    }
}
