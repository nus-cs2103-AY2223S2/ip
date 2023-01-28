package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeBadInstructionFormatException;


/**
 * <code>Task</code> class used by <code>Duke</code> to keep track of user's tasks inputted.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public abstract class Task {
    /**
     * Format of the stored date and time.
     */
    public static final String STORE_DATE_TIME_FORMAT = "dd/MM/yyyy HHmm";
    /**
     * Format of the displayed date and time.
     */
    public static final String DISPLAY_DATE_TIME_FORMAT = "dd LLL yyyy HHmm";
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
     * Constructor for a <code>Task</code>.
     *
     * @param description String describing the <code>Task</code>.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Parses a string date time format in <code>STORE_DATE_TIME_FORMAT</code>
     * and returns it of type <code>LocalDateTime</code>.
     * @param dateTime <code>String</code> format of a date time.
     * @return <code>LocalDateTime</code> format of a date time.
     * @throws DateTimeParseException If users enters the date time in the wrong format.
     */
    public static LocalDateTime getLocalDateTime(String dateTime)
            throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STORE_DATE_TIME_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime,
                formatter);
        return localDateTime;
    }
    /**
     * Converts a <code>LocalDateTime</code> in <code>DISPLAY_DATE_TIME_FORMAT</code>
     * to a <code>String</code>
     * and returns it of type <code>LocalDateTime</code>.
     * @param dateTime <code>String</code> format of a date time.
     * @return <code>LocalDateTime</code> format of a date time.
     * @throws DateTimeParseException If users enters the date time in the wrong format.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DISPLAY_DATE_TIME_FORMAT);
        return dateTime.format(formatter);
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
        String[] splitted = fileFormat.split("@", 5);

        switch (splitted[0]) {

        case "T":
            ToDo toDoTask = new ToDo(splitted[2]);
            if (splitted[1].equals("true")) {
                toDoTask.markAsDone();
            }
            return toDoTask;

        case "D":
            Deadline deadlineTask = new Deadline(splitted[2], splitted[3]);
            if (splitted[1].equals("true")) {
                deadlineTask.markAsDone();
            }
            return deadlineTask;

        case "E":
            Event eventTask = new Event(splitted[2], splitted[3], splitted[4]);
            if (splitted[1].equals("true")) {
                eventTask.markAsDone();
            }
            return eventTask;

        default:
            System.out.println("unknown task from txt file");
            return null;
        }

    }
}
