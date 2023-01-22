package tasks;

import uitilties.Parser;

import java.util.Date;

public class Event extends ITask {

    private final Date from;
    private final Date to;

    public Event(String description , Date from, Date to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.outputFormat.format(this.from) + "to: "
                + Parser.outputFormat.format(this.to) + ")";
    }
}
