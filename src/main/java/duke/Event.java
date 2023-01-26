package duke;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

// class Event - Type of task with description, a start date + time and an end time
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalTime to;

    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MM-yyyy HH:mm")
                .toFormatter(Locale.ENGLISH);
        DateTimeFormatter df2 = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("HH:mm")
                .toFormatter(Locale.ENGLISH);
        this.from = LocalDateTime.parse(from, df);
        this.to = LocalTime.parse(to, df2);
    }

    @Override
    public String toSavedString() {
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
