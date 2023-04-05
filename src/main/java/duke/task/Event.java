package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm"));
    }

    public Event(String name, String from, String to, String status, String tag) {
        super(name, status, tag);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm"));
        } catch (DateTimeParseException e) {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s %d %d %d:%02d, to: %s %d %d %d:%02d)",
                super.toString(), from.getMonth(), from.getDayOfMonth(), from.getYear(),
                from.getHour(), from.getMinute(), to.getMonth(), to.getDayOfMonth(),
                to.getYear(), to.getHour(), to.getMinute());
    }

    @Override
    public String asTokens() {
        return "E," + super.asTokens() +
                ',' + this.from +
                ',' + this.to;
    }
}
