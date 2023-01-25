package task;

import util.DukeException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String by;
    private String date;
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        this.date = super.dateFormatter(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

    //deadline test program /by 25/12/23 11:50PM
}
