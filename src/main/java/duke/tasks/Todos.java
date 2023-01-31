package duke.tasks;

import java.util.List;

/**
 * A specific kind of task with no time-frames.
 */
public class Todos extends Task {
    private static final List<String> keywords = List.<String>of();
    public Todos(String description) {
        this(false, description);
    }

    public Todos(boolean isDone, String description) {
        super(isDone, description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the task into a form where it will be dumped into data.txt.
     */
    public String formatText() {
        String divider = " | ";
        String isMarked = this.isDone ? "1" : "0";
        return "T" + divider + isMarked + divider + this.description;
    }

    public List<String> getKeywords() {
        return this.keywords;
    }
}
