package duke.tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.dukeexceptions.DukeExceptions;

public class Deadline extends Task {

    protected LocalDateTime by;
    private static final String tag = "D";

    public Deadline(String description, String by) {
        super(description);
        try {
            LocalDate byLocalDate = LocalDate.parse(by.split(" ")[0]);
            LocalTime byLocalTime = LocalTime.parse(by.split(" ")[1]);

            this.by = LocalDateTime.of(byLocalDate, byLocalTime);
        } catch (DateTimeParseException e) {
            throw new DukeExceptions("Format of date was not recognized, use YYYY-MM-DD and HH:MM");
        }
    }

    public String saveTask() {
        String completed = this.isDone? "1":"0";
        String formattedDate = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return this.tag + " | " + completed + " | "
                + this.description + " | " + formattedDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by:" + by.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a")) + ")"
                +"\n";
    }
}