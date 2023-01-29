package duke.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {

    public static final Task EMPTY_TASK = new Task("");
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markTask() {
        isDone = true;
    }

    public void unmarkTask() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public boolean isEmpty() {
        return equals(EMPTY_TASK);
    }

    public String getMarkedStatus() {
        return isDone ? "1" : "0";
    }

    public String toData() {
        return String.format("Task | marked: %s ; description: %s", getMarkedStatus(), description);
    }

    public static Task fromData(String data) {
        Pattern pattern = Pattern.compile("(marked:) (.*) ; (description:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            boolean isMarked = matcher.group(2).equals("1");
            String description = matcher.group(4);
            return new Task(description, isMarked);
        }
        return Task.EMPTY_TASK;
    }
}

