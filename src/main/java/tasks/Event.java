package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Event(String description, String startDate, String endDate, boolean fromFile) {
        super(description);
        DateTimeFormatter formatter;
        if (fromFile) {
            formatter = this.outputDateTimeFormatter;
        } else {
            formatter = this.inputDateTimeFormatter;
        }
        this.startDate = LocalDateTime.parse(startDate, formatter);
        this.endDate = LocalDateTime.parse(endDate, formatter);
    }

    @Override
    public String toEncodedString() {
        return "[" + TaskType.E + "]" + super.toString() + " /from "
                + this.startDate.format(this.outputDateTimeFormatter) + " /to "
                + this.endDate.format(this.outputDateTimeFormatter);
    }

    @Override
    public String toString() {
        return "[" + TaskType.E + "]" + super.toString() + " (from: "
                + this.startDate.format(this.outputDateTimeFormatter) + " to: "
                + this.endDate.format(this.outputDateTimeFormatter) + ")";
    }
}
