package duke.tasks;

import duke.Parser;

import java.time.LocalDate;

public class Events extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Events(String description, String startDate, String endDate) {
        super(description);
        this.startDate = Parser.stringToDate(startDate);
        this.endDate = Parser.stringToDate(endDate);
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + this.startDate +
                " to: " + this.endDate + ")";
    }

    @Override
    public String saveString() {
        return String.format("E|%s|%s|%s|%s", super.saveString(), super.description,
                this.startDate, this.endDate);
    }
}
