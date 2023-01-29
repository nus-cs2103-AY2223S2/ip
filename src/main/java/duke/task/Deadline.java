package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String encode() {
        return "deadline"
                + " " + this.description
                + " " + this.isDone
                + " " + this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
