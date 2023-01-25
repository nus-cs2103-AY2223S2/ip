import java.time.format.DateTimeFormatter;

abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    protected static final DateTimeFormatter PRINTFORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

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
     * Formats the task into a form where it will be dumped into data.txt.
     */
    abstract String formatText();


}

