import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getDate(LocalDate date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyy");
        return date.format(format);
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[X] " + this.name
                : "[ ] " + this.name;
    }
}