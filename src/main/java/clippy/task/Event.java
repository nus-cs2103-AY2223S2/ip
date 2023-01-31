package clippy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        // only show day of the week + day of the month + month
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("EEE dd MMM")),
                this.to.format(DateTimeFormatter.ofPattern("EEE dd MMM")));
    }

    @Override
    public String getCsvString() {
        return String.format("E,%s,%s,%s", super.getCsvString(), this.from, this.to);
    }
}
