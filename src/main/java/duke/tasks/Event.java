package duke.tasks;
import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String by;
    private LocalDate from;
    private LocalDate to;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected boolean isDone;

    public Event(String description, String from, String to, boolean isDone) throws DukeException {
        super(description, isDone);
        try {
            this.from = LocalDate.parse(from.trim());
            this.to = LocalDate.parse(to.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("\tPlease enter a valid date in e.g /from yyyy-mm-dd /to yyyy-mm-dd format!");
        }
    }

    @Override
    public String toString() {
        return "\t[E]" + super.toString()
                + " (from: " + from.format(formatter) + " to: "
                + to.format(formatter) + ")";
    }
    public String saveFormat(){
        return String.format("E | %s | %s to %s", super.saveFormat(), this.from, this.to);
    }
}
