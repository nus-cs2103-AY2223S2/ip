package duke;

import java.time.LocalDateTime;
public class Deadline extends Task {

    protected LocalDateTime by;


    public Deadline(String value, LocalDateTime by, boolean mark) {
        super(value,mark);
        this.by = by;
    }

    public String toFile() {
        return "deadline," + super.isMark() + "," + super.getValue() + "," + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }


}
