package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //mark done task with X
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone(){
        isDone = false;
    }

    public String dateFormatter(String str) {
        //"d/M/yy H:mm" for auto detection of AM/PM
        LocalDateTime localDateTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("d/M/yy h:mma"));
        String dt = localDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"));
        return dt;
    }

    @Override
    public String toString() {
        String str = "[" + this.getStatusIcon() + "] " + this.description;
        return str;
    }
}
