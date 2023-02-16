package catbot.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The parent class of all tasks.
 */
public abstract class Task {
    protected boolean isDone;
    final String description;

    /**
     * Enum to express the type of task.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructor for a CatBot.TaskList.Task
     * @param description is the description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Setter for done
     * @param done is whether the task is marked as done
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Gets the mark icon for a task
     *
     * @return a string that should be placed in the slot indicating whether this task is marked
     */
    protected String getStatusIcon() {
        return isDone ? "✓" : " ";
    }

    protected String formatDate(LocalDateTime date) {
        String formatPattern;

        if (date.getYear() == LocalDateTime.now().getYear()) {
            formatPattern = "d MMM, hh:mm a";
            if (date.getDayOfYear() == LocalDateTime.now().getDayOfYear()) {
                formatPattern = "hh:mm a";
            }
        } else {
            formatPattern = "d MMM yyyy, hh:mm a";
        }

        return date.format(DateTimeFormatter.ofPattern(formatPattern));
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toCommand();
}
