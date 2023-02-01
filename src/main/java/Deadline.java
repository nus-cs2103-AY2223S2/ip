package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String deadline;
    private LocalDate date;
    private boolean isDate;
    private static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String deadline) {
        super(description);
        try {
            this.date = LocalDate.parse(deadline);
            this.isDate = true;
        } catch (DateTimeParseException ex) {
            this.isDate = false;
        }
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String dateToPrint = this.isDate ? this.date.format(DATE_OUTPUT_FORMAT) : this.deadline;
        return String.format("[D]%s (by: %s)", super.toString(), dateToPrint);
    }
}
