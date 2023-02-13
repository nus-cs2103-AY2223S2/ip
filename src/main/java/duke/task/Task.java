package duke.task;

import duke.exception.DukeBadInstructionFormatException;
import duke.reminder.Reminder;
import duke.storage.Storage;


/**
 * <code>Task</code> class used by <code>Duke</code> to keep track of user's tasks inputted.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public abstract class Task {
    public static final String TODO_FILE_FORMAT = "T";
    public static final String DEADLINE_FILE_FORMAT = "D";
    public static final String EVENT_FILE_FORMAT = "E";
    /**
     * Longest total fields in a task, the <code>Event</code> is 6.
     */
    private static final int max_fields = 6;
    /**
     * Boolean representing if user set <code>Task</code> to 'done' state. True if done. False
     * otherwise.
     */
    protected boolean isDone;
    /**
     * String describing <code>Task</code>.
     */
    protected String description;
    /**
     * <code>LocalDateTime</code> of the day to remind
     */
    protected Reminder reminder;

    /**
     * Constructor for a <code>Task</code>.
     *
     * @param description String describing the <code>Task</code>.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.reminder = null;
    }
    /**
     * Returns string representation of <code>Task</code> status.
     *
     * @return "X" if isDone is true. " " otherwise.
     */
    public String getStatusIcon() {

        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Sets isDone to true.
     */
    public void markAsDone() {

        this.isDone = true;
    }
    /**
     * Sets isDone to false.
     */
    public void markAsNotDone() {

        this.isDone = false;
    }

    /**
     * Returns the string representation of a <code>Task</code>.
     *
     * @return The string representation of a <code>Task</code>.
     */
    @Override
    public String toString() {

        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the format of the <code>Task</code> to be stored
     * in tasks.txt.
     * @return <code>String</code> representing the <code>Task</code>.
     */
    public abstract String getFileFormatString();

    /**
     * Converts a <code>Task</code> stored in tasks.txt to its instance.
     * @param fileFormat The <code>String</code> representation of a <code>Task</code>.
     * @return The instance of the stored <code>Task</code>.
     * @throws DukeBadInstructionFormatException If stored instruction is of wrong format.
     */
    public static Task getTaskFromFileFormat(String fileFormat)
            throws DukeBadInstructionFormatException {
        String s = Storage.SPLITTER;
        String[] splitted = fileFormat.split(s, max_fields);
        assert splitted.length >= 3 : "Insufficient fields in task";
        String command = splitted[0];
        String markStatus = splitted[1];
        String description = splitted[2];
        switch (command) {

        case TODO_FILE_FORMAT:
            ToDo toDoTask = new ToDo(description);
            if (markStatus.equals("true")) {
                toDoTask.markAsDone();
            }
            return toDoTask;

        case DEADLINE_FILE_FORMAT:
            String by = splitted[3];
            System.out.println("debug" + by);
            Deadline deadlineTask = new Deadline(description, by);
            if (markStatus.equals("true")) {
                deadlineTask.markAsDone();
            }
            return deadlineTask;

        case EVENT_FILE_FORMAT:
            String from = splitted[3];
            String to = splitted[4];
            Event eventTask = new Event(description, from, to);
            if (markStatus.equals("true")) {
                eventTask.markAsDone();
            }
            return eventTask;

        default:
            System.out.println("unknown task from txt file");
            return null;
        }

    }

    /**
     * Returns true if this task has a reminder today, False otherwise.
     * @return <code>true</code> if task has reminder today,<code>false</code>otherwise.
     */
    public boolean hasReminderToday() {
        boolean ret = this.reminder.isToday() && !isDone;
        return ret;
    }
}
