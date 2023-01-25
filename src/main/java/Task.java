import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task object.
 */
public class Task {
    private boolean isDone;
    private String title;
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    };

    public String getTitle() {
        return this.title;
    }
    public boolean getIsDone() {
        return this.isDone;
    }
    public void setIsDone(boolean status) {
        this.isDone = status;
    }

    LocalDateTime parseDateString(String dateString) throws DateTimeParseException {
        return LocalDateTime.parse(dateString);
    }
    String dateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu, HH.mm a"));
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "x" : " ", this.title);
    }

}
