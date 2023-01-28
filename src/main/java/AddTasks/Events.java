package AddTasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Events(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String convertFrom = null;
        String convertTo = null;
        try {
            convertFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            convertTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (NullPointerException e) {
        }
        return " " + "[E]" + "[" + super.getStatusIcon() + "]" + super.toString() + "(From: " + convertFrom + " | To: " + convertTo + ")";
    }
}

