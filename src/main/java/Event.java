import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime until;

    public Event(String name, LocalDateTime from, LocalDateTime until) {
        super(name);
        this.from = from;
        this.until = until;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String taskToData() {
        return String.format("[E] | %s | %s | %s | %s", this.getStatusIcon(), this.name, this.from, this.until);
    }


    @Override
    public String toString() {
        return name + " (from: " + this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + " to: " + this.until .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ")";
    }
}
