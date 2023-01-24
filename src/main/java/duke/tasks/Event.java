package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime to;
    private LocalDateTime from;
    public Event(String taskText, LocalDateTime from, LocalDateTime to) {
        super(taskText);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")),
                this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }

    public String writeFile() {
        return String.format("E|%s|%s|%s|%s", this.getCurrentStatus(), this.getTaskText(),
                this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }

    public LocalDateTime getDate() {
        return this.from;
    }

    public String getTaskType() {
        return "E";
    }
}
