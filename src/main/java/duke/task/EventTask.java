package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.InvalidInputException;

/**
 * A DeadlineTask that encapsulates the information and starting and ending
 * dates of a Deadline Task.
 */
public class EventTask extends DukeTask {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructor for EventTask that takes in the information of the task
     * and its starting date and ending date.
     *
     * @param info The information of the task
     * @param from The starting date of the task
     * @param to The ending date of the task
     * @throws InvalidInputException Throws exception when the staring date is after the end date
     */
    public EventTask(String info, LocalDate from, LocalDate to) throws InvalidInputException {
        super(info, TaskType.EVENT);
        this.from = from;
        this.to = to;
        if (from.isAfter(to)) {
            throw new InvalidInputException("☹ OOPS!!! Start date can not be after than the End date");
        }
    }

    @Override
    public String storageString() {
        String status;
        if (this.getStatus()) {
            status = "[X] | ";
        } else {
            status = "[ ] | ";
        }
        return "[D] | " + status + this.getInformation() + " | " + this.from + " | " + this.to;
    }

    @Override
    public boolean matchesDate(LocalDate date) {
        return date.isEqual(this.from) || date.isEqual(this.to)
                || (date.isAfter(this.from) && date.isBefore(this.to));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
