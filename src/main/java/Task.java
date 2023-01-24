import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
/**
 * Represents a Task that can be kept track of.
 */
public abstract class Task {
    /** The name of the task. */
    protected String taskName;
    /** The status of the task. **/
    protected boolean isDone = false;

    /**
     * Constructs a user task.
     *
     * @param taskName The name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Sets status of task to be done
     */
    public void setDoneStatus() {
        isDone = true;
    }

    /**
     * Sets status of task to be undone
     */
    public void setUndoneStatus() {
        isDone = false;
    }

    /**
     * Gets the status of the task with the task name.
     *
     * @return a String indicating the type and status of the task.
     */
    public abstract String getStatusOfTaskInString();

    /**
     * Gets the status of the task.
     *
     * @return true if task is done, else return false.
     */
    public boolean getStatusOfTask() {
        return isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return the name of the task.
     */
    public String getNameOfTask() {
        return this.taskName;
    }


    /**
     * Formats a date, returning a string in either yyyy-MM-dd HH:mm or yyyy-MM-dd format.
     *
     * @return a string representing the formatted deadline.
     */
    public static String formatDate(Temporal date) {
        if (date instanceof LocalDateTime) {
            //Case 1: Got date and time
            LocalDateTime dateTimeObject = (LocalDateTime) date;
            String monthString = dateTimeObject.getMonth().toString().charAt(0) +
                    dateTimeObject.getMonth().toString().substring(1).toLowerCase();
            String dayString = (dateTimeObject.getDayOfMonth() < 10)
                    ? "0" + Integer.toString(dateTimeObject.getDayOfMonth())
                    : Integer.toString(dateTimeObject.getDayOfMonth());
            String hourString = (dateTimeObject.getHour() < 10)
                    ? "0" + Integer.toString(dateTimeObject.getHour())
                    : Integer.toString(dateTimeObject.getHour());
            String minuteString = (dateTimeObject.getMinute() < 10)
                    ? "0" + Integer.toString(dateTimeObject.getMinute())
                    : Integer.toString(dateTimeObject.getMinute());

            return monthString + " " + dayString + " " + dateTimeObject.getYear() + " " +  hourString + ":"
                    + minuteString;
        } else {
            //Case 2: Got date only
            LocalDate dateObject = (LocalDate) date;
            String monthString = dateObject.getMonth().toString().charAt(0) +
                    dateObject.getMonth().toString().substring(1).toLowerCase();
            String dayString = (dateObject.getDayOfMonth() < 10)
                    ? "0" + Integer.toString(dateObject.getDayOfMonth())
                    : Integer.toString(dateObject.getDayOfMonth());

            return monthString + " " + dayString + " " + dateObject.getYear();
        }
    }
}
