package duke.tasks;

import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class Task {
    /**
     * A formatter for inputs by the user.
     */
    protected static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * A formatter to dump into storage.
     */
    protected static final DateTimeFormatter PRINTFORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Gets the status icon from the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as complete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Formats the task into a form where it will be dumped into data.txt.
     */
    public abstract String formatText();

    /**
     * Obtains the description of a task.
     * @return a string representing a task's description.
     */
    public String getDescription() {
        return this.description;
    }

    public abstract List<String> getKeywords();


}

