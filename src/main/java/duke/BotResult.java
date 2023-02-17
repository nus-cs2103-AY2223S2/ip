package duke;

/**
 * The BotResult class contains the result upon processing a user query.
 */
public class BotResult {
    private final BotStatus status;
    private final String response;

    /**
     * Creates a new bot result.
     * @param status bot status
     * @param response bot response
     */
    public BotResult(BotStatus status, String response) {
        this.status = status;
        this.response = response;
    }

    public BotStatus getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }

    /**
     * Represents bot current status.
     */
    public enum BotStatus {
        Successful,
        Failure,
        Exit
    }
}
