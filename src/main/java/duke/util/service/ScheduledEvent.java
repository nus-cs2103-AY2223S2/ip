package duke.util.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.util.Task;

/**
 * A more specific implementation of {@code Task}
 * as specified by the user.
 */

public class ScheduledEvent extends Task {
    private LocalDateTime dateBegin;
    private LocalDateTime dateEnd;

    /**
     * Construct a {@code Deadline} with the action specified
     * by the user, as well as the beginning date and end date of the action,
     * denoted in the user input as the keywords "/FROM"
     * and "/TO".
     */

    public ScheduledEvent(LocalDateTime dateBegin,
                          LocalDateTime dateEnd, String action) {
        super("E", action);
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public ScheduledEvent(LocalDateTime dateBegin,
                          LocalDateTime dateEnd, String action, boolean isDone) {
        super("E", action, isDone);
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public List<LocalDateTime> getDates() {
        return List.of(this.dateBegin, this.dateEnd);
    }

    @Override
    public String getAdditionalInfo() {
        return " /FROM " + dateBegin.format(
                DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy"))
                + " /TO " + dateEnd.format(
                        DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")";
    }

    @Override

    public String toString() {
        return super.toString() + " (FROM: " + dateBegin.format(
                DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy"))
                + " TO: " + dateEnd.format(
                        DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")";
    }
}
