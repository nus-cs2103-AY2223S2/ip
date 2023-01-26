package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    public Deadline(String text, LocalDate date) {
        super(text);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy GGGG")) + ")";
    }
}
