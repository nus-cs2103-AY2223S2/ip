package duke.taskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String time;

    public Deadline(String cont, String time) {
        super(cont);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("LLL dd yyyy HH:mm");
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateOnlyFormatter1 = DateTimeFormatter.ofPattern("LLL dd yyyy");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(time, dateTimeFormatter);
            this.time = dateTime.format(dateTimeFormatter1);
        } catch (DateTimeParseException e) {
            try {
                LocalDate dateOnly = LocalDate.parse(time, dateOnlyFormatter);
                this.time = dateOnly.format(dateOnlyFormatter1);
            } catch (DateTimeParseException e1) {
                this.time = time;
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}
