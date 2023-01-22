import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task class used by Duke to keep track of user's tasks inputted.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

public abstract class Task {

    /**
     * String describing task.
     */
    protected String description;

    public static final String STORE_DATE_TIME_FORMAT = "dd/MM/yyyy HHmm";

    public static final String DISPLAY_DATE_TIME_FORMAT = "dd LLL yyyy HHmm";

    /**
     * Boolean representing if user set task to 'done' state. True if done. False
     * otherwise.
     */
    protected boolean isDone;

    /**
     * Constructor for a task.
     *
     * @param description String describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string representation of task status.
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

    public static LocalDateTime getLocalDateTime(String dateTime)
            throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STORE_DATE_TIME_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime,
                formatter);
        return localDateTime;
    }

    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DISPLAY_DATE_TIME_FORMAT);
        return dateTime.format(formatter);
    }

    /**
     * Returns the string representation of a task.
     *
     * @return The string representation of a task.
     */
    @Override
    public String toString() {
           return "[" + getStatusIcon() + "] " + this.description;
    }

    public abstract String getFileFormatString();

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
