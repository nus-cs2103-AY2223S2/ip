import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private static Pattern pattern = Pattern.compile("event\\s(.*)\\s/from(.*)\\s/to(.*)");
    private String[] period;

    public Event(String description, String[] period) {
        super(description);
        this.period = period;
    }

    public static String description(String input) {
        Matcher m = pattern.matcher(input);
        m.find();
        return m.group(1);
    }

    public static String[] period(String input) {
        Matcher m = pattern.matcher(input);
        m.find();
        return new String[] {m.group(2), m.group(3)};
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (from:" + this.period[0] + " to:" + this.period[1] + ")";
    }
}
