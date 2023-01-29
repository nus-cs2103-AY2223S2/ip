package duke.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isMarked) {
        this(description);
        this.isDone = isMarked;
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toData() {
        return String.format("Todo | marked: %s ; description: %s", this.isMarked(), this.description);
    }

    public static Task fromData(String data) {
        Pattern pattern = Pattern.compile("(marked:) (.*) ; (description:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            boolean isMarked = matcher.group(2).equals("1");
            String description = matcher.group(4);
            return new Todo(description, isMarked);
        }
        return Task.EMPTY_TASK;
    }
}