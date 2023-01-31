package duke.tasks;

import duke.helper.DateTimeParser;

import java.time.LocalDate;

//solution adapted from 2103 website
public class Deadline extends Task {

    protected LocalDate endTime;

    static final String type  = "D";

    public static String getType() {
        return type;
    }

    public LocalDate getEndTime() {
        return this.endTime;
    }

    public Deadline(String name, String endTime) {
        super(name);
        this.endTime = DateTimeParser.parse(endTime);
    }



    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime.toString() + ")";
    }
}
