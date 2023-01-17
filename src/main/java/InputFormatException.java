public class InputFormatException extends DukeException {
    public InputFormatException(String source, String reason, Throwable err) {
        super("Haiya input format wrong. Here is where you mess up: " + source + "\n Here is why you wrong: " + reason, err);
    }
}
