package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected String by;
    protected String desc;
    LocalDate byDate;

    public Deadline(String desc, String by) {
        super(desc);
        this.desc = desc;
        this.by = by;
        byDate = LocalDate.parse(by);
    }

    public Deadline(String desc, String by, boolean b) {
        super(desc, b);
        this.desc = desc;
        this.by = by;
        byDate = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String asCSV() {
        if (super.isDone) {
            return "D,1" + desc + "," + by;
        } else {
            return "D,0" + desc + "," + by;
        }
    }
}
