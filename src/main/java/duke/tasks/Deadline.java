package duke.tasks;
import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    LocalDate dateTime;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, isDone);
        this.by = by;
        try {
            this.dateTime = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("\tPlease enter a date in e.g yyyy-mm-dd format!");
        }
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
    }

    public String saveFormat(){
        return String.format("D | %s | %s", super.saveFormat(), this.by);
    }
}

