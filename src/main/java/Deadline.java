import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String toData() {
        return String.format("Deadline | description: %s ; deadline: %s", this.description, this.by);
    }

    public static Task fromData(String data) {
        Pattern pattern = Pattern.compile("(description:) (.*) ; (deadline:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            String description = matcher.group(2);
            String deadline = matcher.group(4);
            return new Deadline(description, deadline);
        }
        return Task.EMPTY_TASK;
    }
}
