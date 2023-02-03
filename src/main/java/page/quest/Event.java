package page.quest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import page.PageException;

public class Event extends Quest {
    private static DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("[HHmm dd/MM/yy][dd MMM yyyy hh:mma]");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, String from, String to) throws PageException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, inputFormatter);
            this.to = LocalDateTime.parse(to, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new PageException("Please format the date and time like this: 2359 31/12/99");
        }
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " from: "
                + from.format(outputFormatter) + " to: " + to.format(outputFormatter);
    }
}
