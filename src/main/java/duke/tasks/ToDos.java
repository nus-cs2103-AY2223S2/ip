package duke.tasks;

import java.time.LocalDateTime;

import duke.Parser;

/**
 * ToDo tasks.
 */
public class ToDos extends Task {

    private static Integer DAYS_IN_A_WEEK = 7;

    /**
     * Date when this task is marked completed.
     */
    private LocalDateTime completionDate = null;

    /**
     * Date when this task is added.
     */
    private LocalDateTime addedDate;

    /**
     * Creates a ToDo task.
     * Sets added date to current timing, when this method is called.
     */
    public ToDos(String description) {
        super(description);
        this.addedDate = LocalDateTime.now();
    }

    /**
     * Creates a ToDo task.
     * Sets added date according to the date provided from the Storage file.
     */
    public ToDos(String description, String addedDate) {
        super(description);
        this.addedDate = LocalDateTime.now();
        this.addedDate = Parser.stringToDateTime(addedDate);
    }

    @Override
    public String getStatusIcon() {
        return super.getStatusIcon();
    }

    /**
     * Parses the LocalDateTime added date to a String.
     */
    public String parseAddSaving() {
        return Parser.dateTimeSaving(addedDate);
    }

    /**
     * Produces a String that adheres to our Storage formatting
     * holding all the relevant information.
     *
     * @return the String of the specific task for saving
     */
    @Override
    public String saveString() {
        return String.format("T|%s|%s|%s", super.saveString(), super.description,
                this.parseAddSaving());
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
     * All the Information of the ToDos task
     *
     * @return a String of all the information of the ToDos task to be printed by the Ui
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
