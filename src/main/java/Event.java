import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    Event(String task, String from, String to) throws DateTimeParseException {
        super(task);
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.from = LocalDateTime.parse(from, parser);
        this.to = LocalDateTime.parse(to, parser);
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + " to: " +
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    public String toFileString() {
        
        return "E | " + super.toFileString() + " | " + 
            from.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")) + 
            " | " + to.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

}
