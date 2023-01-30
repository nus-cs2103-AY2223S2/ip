package duke.util.service;

import duke.util.Task;

/**
 * A more specific implementation of {@code Task}
 * as specified by the user.
 */

public class ScheduledEvent extends Task {
    String dateBegin;
    String dateEnd;

    /**
     * Construct a {@code Deadline} with the action specified
     * by the user, as well as the beginning date and end date of the action,
     * denoted in the user input as the keywords "/FROM"
     * and "/TO".
     */
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