package duke.tasks;

import duke.Parser;

import java.time.LocalDateTime;

public class Events extends Task {

    static protected String DEFAULT_TIME = "2359";

    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    public Events(String description, String startDate, String endDate) {
        super(description);
        this.startDate = Parser.stringToDateTime(startDate);
        this.endDate = Parser.stringToDateTime(endDate);
    }

    public String getStart() {
        return Parser.dateTimeToString(startDate);
    }

    public String getEnd() {
        return Parser.dateTimeToString(endDate);
    }


    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + this.getStart() +
                " to: " + this.getEnd() + ")";
    }

    public String parseStartSaving() {
        return Parser.dateTimeSaving(startDate);
    }

    public String parseEndSaving() {
        return Parser.dateTimeSaving(startDate);
    }

    @Override
    public String saveString() {
        return String.format("E|%s|%s|%s|%s", super.saveString(), super.description,
                this.parseStartSaving(), this.parseEndSaving());
    }
}
