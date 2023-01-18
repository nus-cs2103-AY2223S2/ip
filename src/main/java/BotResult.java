public class BotResult {
    public BotStatus resultStatus;
    public String response;

    public BotResult(BotStatus status, String response) {
        this.resultStatus = status;
        this.response = response;
    }

    public enum BotStatus {
        Successful,
        Failure,
        Exit
    }
}
