package duke.tasks;
import duke.dukeexceptions.DukeExceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final String tag = "E";

    public Events(String description, String from, String to) {
        super(description);
        try {
            String[] fromSplit = from.split(" ");
            LocalDate fromLocalDate = LocalDate.parse(fromSplit[0]);
            LocalTime fromLocalTime = LocalTime.parse(fromSplit[1]);
            this.from = LocalDateTime.of(fromLocalDate, fromLocalTime);

            String[] toSplit = to.split(" ");
            LocalDate toLocalDate = LocalDate.parse(toSplit[0]);
            LocalTime toLocalTime = LocalTime.parse(toSplit[1]);
            this.to = LocalDateTime.of(toLocalDate, toLocalTime);

        } catch (DateTimeParseException e) {
            throw new DukeExceptions("Format of date was not recognized, use YYYY-MM-DD and HH:MM");
        }
    }

    public String saveTask() {
        String completed = this.isDone? "1":"0";
        return this.tag + " | " + completed + " | "
                + this.description
                + " | " + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " - " + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from:" + this.from.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a"))
                + " to:" + this.to.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a"))
                + ")" +"\n";
    }
}