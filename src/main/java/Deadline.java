import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private static Pattern p = Pattern.compile("deadline\\s(.*)\\s/by\\s(.*)");
    private String doBy;

    public Deadline (String description, String deadline) {
        super(description);
        this.doBy = deadline;
    }

    public static String description(String input) {
        Matcher m = p.matcher(input);
        m.find();
        return m.group(1);
    }

    public static String deadline(String input) {
        Matcher m = p.matcher(input);
        m.find();
        return m.group(2);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + this.doBy + ")";
    }
}
