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

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    abstract String formatText();


}

