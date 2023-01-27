import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

public class Task {

    private final String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatToPrint = DateTimeFormatter.ofPattern("MMM dd yyyy KK:mm a");
        return dateTime.format(formatToPrint);

    }

    public void mark() {
        this.isDone = true;
    }
    
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + mark + "] " + this.description;
    }
}
