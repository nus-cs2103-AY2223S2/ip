package model;

import utils.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public static final String TAG = "[D]";

    private String deadline;

    public Deadline(String title, String deadline) {
        super(title);

        try {
            LocalDateTime dateTime = DateTimeParser.parse(deadline);
            this.deadline = DateTimeParser.format(dateTime);
        } catch (DateTimeParseException e) {
            this.deadline = deadline;
        }
    }

    @Override
    public String toString() {
        return Deadline.TAG + super.toString() + " (by: " + this.deadline + ")";
    }
}
