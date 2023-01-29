import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private String description;
    private boolean isDone;
    private static Pattern p = Pattern.compile("(todo|event|deadline).*");

    public Task (String input) {
        this.description = input;
        this.isDone = false;
    }

    public static boolean isTask(String input) {
        return p.matcher(input).find();
    }

    public static String taskType(String input) {
        Matcher m = p.matcher(input);
        m.find();
        return m.group(1);
    }
    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String addedTask() {
        return "Added: " + this.description;
    }

    @Override
    public String toString() {
        return "Added: " + this.description;
    }
}
