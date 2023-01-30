package duke.util.service;

import duke.util.Task;

public class ScheduledEvent extends Task {
    String dateBegin;
    String dateEnd;
    public ScheduledEvent(String dateBegin, String dateEnd, String action) {
        super("E", action);
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return super.toString() + " (FROM: " + dateBegin + " TO: " + dateEnd + ")";
    }
}