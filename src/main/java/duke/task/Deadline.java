package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected String date;
    protected LocalTime time = LocalTime.of(23,59);


    public Deadline(String description, String date, String remarks) {
        super(description, remarks);
        LocalDate d = LocalDate.parse(date);
        this.date = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Deadline(String description, String date, String time, String remarks) {
        this(description, date, remarks);
        this.time = LocalTime.parse(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + " " + time + ")";
    }
}