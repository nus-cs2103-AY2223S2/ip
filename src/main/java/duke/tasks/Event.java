package duke.tasks;


import duke.utilities.Parser;

import java.util.Date;

public class Event extends ITask {

    private final Date from;
    private final Date to;

    public Event(String description, Date from, Date to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public Event(String description, Date from, Date to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveFormat() {
        return "[E] " + "/from: " + from.getTime() + " /to: " + to.getTime() + " /content: " + super.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.outputFormat.format(this.from) + " to: "
                + Parser.outputFormat.format(this.to) + ")";
    }
}
