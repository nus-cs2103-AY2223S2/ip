package duke;

public class DukeResponse {
    Boolean shouldExit;
    String message;

    public DukeResponse(String message, Boolean shouldExit) {
        this.message = message;
        this.shouldExit = shouldExit;
    }

    public DukeResponse(String message) {
        this.message = message;
        this.shouldExit = false;
    }
}
