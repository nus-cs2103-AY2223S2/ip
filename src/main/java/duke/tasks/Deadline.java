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
        return super.getStatusIcon();
    }

    public String getBy() {
        return Parser.dateTimeToString(by);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    public String parseBySaving() {
        return Parser.dateTimeSaving(by);
    }

    @Override
    public String saveString() {
        return String.format("D|%s|%s|%s", super.saveString(), super.description, this.parseBySaving());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getBy());
    }
}
