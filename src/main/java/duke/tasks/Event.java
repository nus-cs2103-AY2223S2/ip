package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.EventFromToNotSpecified;
import duke.exceptions.TaskNameNotSpecified;
import duke.parsing.Parser;

/**
 * Wrapper class for 'Event' tasks
 */
public class Event extends Task {
    private String fromDate;
    private String toDate;
    private LocalDate chronoFromDate;
    private LocalDate chronoToDate;

    /**
     * Constructor method.
     *
     * @param taskName Task name
     * @param fromDate Start date of task
     * @param toDate End date of task
     * @param isDone Task completion status
     */
    public Event(String taskName, String fromDate, String toDate, boolean isDone) {
        super(taskName, "E", isDone);

        this.fromDate = fromDate;
        this.chronoFromDate = Parser.parseDate(fromDate);

        this.toDate = toDate;
        this.chronoToDate = Parser.parseDate(toDate);

        this.isDone = isDone;
    }

    /**
     * Factory method.
     *
     * @param commandInput Command line input that the user entered.
     * @return New Event task
     * @throws TaskNameNotSpecified Task name was not specified
     * @throws EventFromToNotSpecified Task due date was not specified
     */
    public static Event create(String commandInput) throws TaskNameNotSpecified, EventFromToNotSpecified {
        String[] parseInfo = Parser.parseEventCmd(commandInput);
        return new Event(parseInfo[0], parseInfo[1], parseInfo[2], false);
    }

    /**
     * Represents fields of this task as a string
     *
     * @return String representation of fields in this task
     */
    @Override
    public String stringFields() {
        String fromDateString = this.chronoFromDate == null ? this.fromDate : this.chronoFromDate
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String toDateString = this.chronoToDate == null ? this.toDate : this.chronoToDate
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format(" (from: %s to: %s)", fromDateString, toDateString);
    }

    @Override
    public String saveStringFields() {
        return String.format(" (from: %s to: %s)", this.fromDate, this.toDate);
    }


    /**
     * Gets due date of task.
     *
     * @return due date of task
     */
    @Override
    public LocalDate getEndDate() {
        return this.chronoToDate;
    }
}
