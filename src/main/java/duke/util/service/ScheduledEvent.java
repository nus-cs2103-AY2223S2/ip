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

    /**
     * Construct a {@code Deadline} with the action specified
     * by the user, as well as the beginning date and end date of the action,
     * denoted in the user input as the keywords "/FROM"
     * and "/TO". The marked/ unmarked status also needs to be provided
     *
     */

    public ScheduledEvent(LocalDateTime dateBegin,
                          LocalDateTime dateEnd, String action, boolean isDone) {
        super("E", action, isDone);
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    /**
     * Return the beginning date and ending date of an event
     * in the form of a list
     *
     * @return a list of {@code LocalDateTime} objects consiting of
     *         the beginning date and ending date of the event
     */

    public List<LocalDateTime> getDates() {
        return List.of(this.dateBegin, this.dateEnd);
    }

    /**
     * Return the timing of event in the form of a {@code String}
     */

    @Override
    public String getTimeInfo() {
        return " /FROM " + dateBegin.format(
                DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy"))
                + " /TO " + dateEnd.format(
                        DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return super.toString() + " (FROM: " + dateBegin.format(
                DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy"))
                + " TO: " + dateEnd.format(
                        DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy")) + ")";
    }
}
