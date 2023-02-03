package page.quest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import page.PageException;

public class Deadline extends Quest {

    private static DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("[HHmm dd/MM/yy][dd MMM yyyy hh:mma]");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
    private LocalDateTime by;

    public Deadline(String description, String by) throws PageException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new PageException("Please format the date and time like this: 2359 31/12/99");
        }

    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " by: " + by.format(outputFormatter);
    }
}
