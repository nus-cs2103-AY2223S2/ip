import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Todo extends Task {
    private static Pattern p = Pattern.compile("todo\\s(.*)");

    public Todo (String description) {
        super(description);
    }

    public static String description(String input) {
        Matcher m = p.matcher(input);
        m.find();
        return m.group(1);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.getDescription();
    }
}
