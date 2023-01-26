import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toData() {
        return String.format("Todo | description: %s", this.description);
    }

    public static Task fromData(String data) {
        Pattern pattern = Pattern.compile("(description:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            String description = matcher.group(2);
            return new Todo(description);
        }
        return Task.EMPTY_TASK;
    }
}