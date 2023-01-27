import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    void completeTask() {
        this.isDone = true;
    }

    void undoTask() {
        this.isDone = false;
    }
    String formatDateTime (LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH: mm");
        return dateTime.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
