package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

// class Event - Type of task with description, a start date + time and an end time
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    public Event(boolean isMarked, String content, String from, String to) {
        super(isMarked, content);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.from = LocalDateTime.parse(from.trim(), formatter);
            this.to = LocalDateTime.parse(to.trim(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format should be yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String addDivider() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").withLocale(Locale.ENGLISH);
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH:mm").withLocale(Locale.ENGLISH);

        return "E | " + super.toSavedString() + " | " + this.from.format(df) + " | " + this.to.format(df2);
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").withLocale(Locale.ENGLISH);
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH:mm").withLocale(Locale.ENGLISH);
        return "[E]" + super.toString() + " (from: " + from.format(df) + " to: " + to.format(df2) + ")";
    }

}