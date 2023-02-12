package duke.tasks;

import duke.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {

    static private String DEFAULT_TIME = "2359";
    static private Integer DAYS_IN_A_WEEK = 7;
    protected LocalDateTime dateBy;
    private LocalDateTime completionDate = null;
    private LocalDateTime addedDate;

    public Deadline(String description, String by) {
        super(description);
        this.dateBy = Parser.stringToDateTime(by);
        this.addedDate = LocalDateTime.now();
    }

    public Deadline(String description, String by, String addedDate) {
        super(description);
        this.dateBy = Parser.stringToDateTime(by);
        this.addedDate = Parser.stringToDateTime(addedDate);
    }

    @Override
    public String getStatusIcon() {
        return super.getStatusIcon();
    }

    public String getBy() {
        return Parser.dateTimeToString(dateBy);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    public String parseBySaving() {
        return Parser.dateTimeSaving(dateBy);
    }

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
            return (dateBy.isBefore(softDeadLine) || dateBy.isAfter(currentDateTime));
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
        return String.format("D|%s|%s|%s|%s", super.saveString(), super.description,
                this.parseBySaving(), this.parseAddSaving());
    }

    /**
     * All the Information of the Deadline task
     *
     * @return a String of all the information of the Deadline task to be printed by the Ui
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getBy());
    }
}
