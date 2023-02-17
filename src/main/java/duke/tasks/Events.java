package duke.tasks;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.Parser;

/**
 * Event Task.
 */
public class Events extends Task {

    /**
     * Default time that will be set, if user does not provide a time.
     */
    private static String DEFAULT_TIME = "2359";
    private static Integer DAYS_IN_A_WEEK = 7;

    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Date when this task is marked completed.
     */
    private LocalDateTime completionDate = null;

    /**
     * Date when this task is added.
     */
    private LocalDateTime addedDate;

    /**
     * Creates an Event task.
     * Sets added date to current timing, when this method is called.
     * @throws DukeException
     */
    public Events(String description, String startDate, String endDate) throws DukeException {
        super(description);
        this.startDate = Parser.stringToDateTime(startDate);
        this.endDate = Parser.stringToDateTime(endDate);
        this.addedDate = LocalDateTime.now();
        if (this.endDate.isBefore(this.startDate)) {
            throw new DukeException("Event is Invalid! Your Start Date is after your EndDate!");
        }
    }

    /**
     * Creates an Event task.
     * Sets added date according to the date provided from the Storage file.
     * @throws DukeException
     */
    public Events(String description, String startDate, String endDate, String addedDate) throws DukeException {
        super(description);
        this.startDate = Parser.stringToDateTime(startDate);
        this.endDate = Parser.stringToDateTime(endDate);
        this.addedDate = Parser.stringToDateTime(addedDate);
        if (this.endDate.isBefore(this.startDate)) {
            throw new DukeException("Event is Invalid! Your Start Date is after your EndDate!");
        }
    }

    public String getStart() {
        return Parser.dateTimeToString(startDate);
    }

    public String getEnd() {
        return Parser.dateTimeToString(endDate);
    }

    @Override
    public String getStatusIcon() {
        return super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * Parses the LocalDateTime start date to a String.
     */
    public String parseStartSaving() {
        return Parser.dateTimeSaving(startDate);
    }

    /**
     * Parses the LocalDateTime end date to a String.
     */
    public String parseEndSaving() {
        return Parser.dateTimeSaving(startDate);
    }

    /**
     * Parses the LocalDateTime added date to a String.
     */
    public String parseAddSaving() {
        return Parser.dateTimeSaving(addedDate);
    }

    /**
     * Marks the task as Done and set the Completion Date
     */
    @Override
    public void mark() {
        super.mark();
        completionDate = LocalDateTime.now();
    }

    /**
     * Unmarks the task and reset the Completion Date to null
     */
    @Override
    public void unmark() {
        super.unmark();
        completionDate = null;
    }

    @Override
    public boolean checkUrgent() {
        if (isDone) {
            return false;
        } else {
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime softDeadLine = currentDateTime.plusDays(DAYS_IN_A_WEEK);
            return (endDate.isBefore(softDeadLine) || endDate.isAfter(currentDateTime));
        }
    }

    /**
     * Returns if the current task is completed within the past week.
     *
     * @return a Boolean of the current task, whether is completed within the past week.
     */
    public boolean completedWithinWeek() {

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime pastWeek = currentDateTime.minusDays(DAYS_IN_A_WEEK);

        if (isDone) {
            return this.completionDate.isAfter(pastWeek)
                    && this.completionDate.isBefore(currentDateTime);
        } else {
            return false;
        }
    }

    /**
     * Returns if the current task is added within the past week.
     *
     * @return a Boolean of the current task, whether is added within the past week.
     */
    public boolean addedWithinWeek() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime pastWeek = currentDateTime.minusDays(DAYS_IN_A_WEEK);

        return this.addedDate.isAfter(pastWeek)
                && this.addedDate.isBefore(currentDateTime);
    }

    /**
     * Produces a String that adheres to our Storage formatting
     * holding all the relevant information.
     *
     * @return the String of the specific task for saving
     */
    @Override
    public String saveString() {
        return String.format("E|%s|%s|%s|%s|%s", super.saveString(), super.description,
                this.parseStartSaving(), this.parseEndSaving(), this.parseAddSaving());
    }

    /**
     * Returns a String with all the Event Task Information.
     *
     * @return a String of all the information of the Event task to be printed by the Ui
     */
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.getStart(), this.getEnd());
    }
}
