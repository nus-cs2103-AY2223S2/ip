package duke.tasklist;

import java.io.Serializable;

public class Event extends Task implements Serializable {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
    }

    @Override
    public String toString() {
            return "[E]" + super.toString() + "(from:" + from + " to:" + to + ")";
    }
    @Override
    public String toSave() {
            return "[E]" + super.toString() + "(from:" + from + " to:" + to + ")";
    }
}



