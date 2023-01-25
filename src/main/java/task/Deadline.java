package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String by;
    private String date;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = super.dateFormatter(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

    // add Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException
    //when user does not input the task;
}
