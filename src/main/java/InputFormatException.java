public class InputFormatException extends DukeException {
    public InputFormatException(String source, String reason, Throwable err) {
        super("Incorrect input format. Error found during: " + source + "\n Reason: " + reason, err);
    }
}
