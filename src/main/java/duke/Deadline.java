package duke;

import duke.exceptions.DukeyException;

import java.time.LocalDate;

/**
 * Deadline is a type of Task which contains a name and a date (in the form of
 * a LocalDate) by which the task has to be
 * completed.
 */
public class Deadline extends Task {
    private static final String TYPE = "[D]";
    private LocalDate deadline;

    /**
     * Returns a Deadline with a name, deadline, and sets its status to undone.
     * @param name the name of the Deadline
     * @param deadline the time which the Deadline must be completed
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns a Deadline with a name and sets its status accordingly.
     * @param name the name of the Deadline
     * @param deadline the time by which the Deadline must be completed
     * @param isMarked the status of the Deadline
     */
    public Deadline(String name, LocalDate deadline, boolean isMarked) {
        super(name, isMarked);
        this.deadline = deadline;
    }

    /**
     * Returns a Deadline created with details taken from user input.
     * @param ui object to handle user interactions
     * @return Deadline created based on the user's input
     */
    public static Deadline createDeadline(Ui ui) throws DukeyException {
        String deadlineName = ui.readTaskName("Deadline");
        LocalDate deadlineTime = ui.readTime("Deadline");

        return new Deadline(deadlineName, deadlineTime);

    }

    /**
     * Returns the deadline
     * @return deadline of the task
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns a Deadline created from its log string. This method "loads"
     * a Deadline from a save.
     * @param logStringArray an array of strings where each string is a component of the log string that
     *                       has been split up
     * @return Deadline created from its log string.
     */
    public static Deadline createDeadlineFromLog(String[] logStringArray) {
        String name = logStringArray[2];
        String deadlineString = logStringArray[3];
        boolean isMarked = !logStringArray[1].equals("0");
        LocalDate deadline = LocalDate.now();
        try {
            deadline = DukeyTime.getDateFromString(deadlineString);
        } catch (DukeyException e) {
            e.getMessage();
        }

        return new Deadline(name, deadline, isMarked);
    }

    /**
     * Returns a message to be printed whenever a Deadline is added.
     * @return the message to be printed
     */
    @Override
    public String messageWhenAdded() {
        return "DukeyList just added a new deadline:";
    }

    /**
     * Returns the log string of a Deadline, which is a string containing details about the task. The log
     * string will be used to save the task locally when the task list is saved.
     * @return the log string of the Deadline
     */
    @Override
    public String getLogString() {
        return "D" + "," + this.isDoneStatus() + "," + this.getName() + "," + this.getDeadline();
    }


    /**
     * Returns the string representation of a Deadline
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X] " + this.getName() + " (by " + DukeyTime.dateToString(this.deadline) + ")";
        }
        return TYPE + "[ ] " + this.getName() + " (by " + DukeyTime.dateToString(this.deadline) + ")";
    }
}
