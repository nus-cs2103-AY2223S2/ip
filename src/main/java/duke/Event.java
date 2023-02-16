package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

// class Event - Type of task with description, a start date + time and an end time
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.from = LocalDateTime.parse(from, formatter);
            this.to = LocalDateTime.parse(to, formatter);
        } catch(Exception e) {
            throw new DukeException("Date might not be the right format! Make sure it is <desc> /from <yyyy-MM-dd HH:mm> /to <yyyy-MM-dd HH:mm>");
        }
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
        String d = " | ";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return "E" + d + super.addDivider() + d + this.from.format(df) + d + this.to.format(df2);
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy HH a").withLocale(Locale.ENGLISH);
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("MMM dd yyyy HH a").withLocale(Locale.ENGLISH);
        return "[E]" + super.toString() + " (from: " + from.format(df) + " to: " + to.format(df2) + ")";
    }

}