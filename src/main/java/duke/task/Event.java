package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;
    public Event(String title, LocalDate from, LocalDate to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String str = "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        if (super.getSnoozed()) {
            str += " (snoozed)";
        }
        return str;
    }

    @Override
    public String toSavedString() {
        String str = "E|" + super.toSavedString() + "|"
                + from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + "|" + to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (super.getSnoozed()) {
            str += "|S";
        }

        return str;
    }
}
