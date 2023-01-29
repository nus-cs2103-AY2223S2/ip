import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {

    public static final Task EMPTY_TASK = new Task("");
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public boolean isEmpty() {
        return this.equals(EMPTY_TASK);
    }

    public String isMarked() {
        return this.isDone ? "1" : "0";
    }

    public String toData() {
        return String.format("Task | marked: %s ; description: %s", this.isMarked() ,this.description);
    }

    public static Task fromData(String data) {
        Pattern pattern = Pattern.compile("(marked:) (.*) ; (description:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            boolean isMarked = matcher.group(2).equals("1") ? true : false;
            String description = matcher.group(4);
            return new Task(description, isMarked);
        }
        return Task.EMPTY_TASK;
    }
}

