import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public String toData() {
        return String.format("Event | description: %s ; from: %s ; to: %s", this.description, this.from, this.to);
    }

    public static Task fromData(String data) {
        Pattern pattern = Pattern.compile("(description:) (.*) ; (from:) (.*) ; (to:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            String description = matcher.group(2);
            String from = matcher.group(4);
            String to = matcher.group(6);
            return new Event(description, from, to);
        }
        return Task.EMPTY_TASK;
    }
}
