package DukeBot;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Date;

public class Deadline extends Task {

    private static final String typeToString = "[D]";
    private final LocalDateTime deadline;

    public Deadline(String task, String deadline) throws DateTimeParseException {
        super(task);
        System.out.println(deadline);
        this.type = Types.DEADLINE;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
        System.out.println(this.deadline);
    }

    @Override
    public String status() {
        String status = this.completed ? "[X]" : "[ ]";
        return typeToString + status + details + " (by: " +
                this.deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " [" +
                this.deadline.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "]" + ")";
    }
}
