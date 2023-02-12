package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Period is a task that needs to be done within a certain period
 */
public class Period extends Tasks {

    /**
     * Starting date and time of the period task
     */
    protected LocalDateTime between;
    /**
     * Ending date and time of the period task
     */
    protected LocalDateTime and;

    /**
     * Creates a period task with a description, start and end date and time
     * @param description describes the period task
     * @param between starting date and time
     * @param and ending date and time
     */
    public Period(String description, LocalDateTime between, LocalDateTime and) {
        super(description);
        this.between = between;
        this.and = and;
    }

    /**
     * Strign representation of the period task
     */
    @Override
    public String toString() {
        return "[P]" + super.toString() + " (to be done between: "
            + this.between.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
                + " and: " + this.between.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) + ")";
    }

    /**
     * logs the period task into data file
     */
    @Override
    public String log() {
        return "P" + super.log() + " | " + this.between + " - " + this.and + "\n";
    }
}
