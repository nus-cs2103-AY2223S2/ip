import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this(description, false);
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    void completeTask() {
        this.isDone = true;
    }

    void undoTask() {
        this.isDone = false;
    }
    String formatDateTime (LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return dateTime.format(formatter);
    }

    String formatSavedDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    abstract String getSaveTaskString();

    @Override
    public String toString() {
        return String.format("%s | %s", isDone ? "X" : " ", description);
    }
}
