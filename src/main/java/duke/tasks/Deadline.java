package duke.tasks;

import duke.Parser;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
            super(description);
            this.by = Parser.stringToDate(by);
    }
    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + this.by + ")";
    }

    @Override
    public String saveString() {
        return String.format("D|%s|%s|%s", super.saveString(), super.description, this.by);
    }
}
