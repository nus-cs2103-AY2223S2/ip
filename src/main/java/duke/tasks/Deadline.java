package duke.tasks;

import duke.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {

    static protected String DEFAULT_TIME = "2359";
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = Parser.stringToDateTime(by);
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    public String getBy() {
        return Parser.dateTimeToString(by);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + this.getBy() + ")";
    }

    public String parseBySaving() {
        return Parser.dateTimeSaving(by);
    }

    @Override
    public String saveString() {
        return String.format("D|%s|%s|%s", super.saveString(), super.description, this.parseBySaving());
    }
}
