package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class defines a basic task object
 * Task (String description, boolean isDone)
 *
 * @author He Shuimei
 * @version 0
 */
public class Task {
    private final String desc;
    private boolean isDone;

    /**
     * Task object
     *
     * @param desc String description
     * @param isDone boolean isDone
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Changes the status of done to true
     */
    public void toggleDone() {
        this.isDone = true;
    }

    /**
     * Changes the status of done to false
     */
    public void toggleNotDone() {
        this.isDone = false;
    }

    /**
     * Returns String "X" if done, else returns empty string
     * @return string
     */
    public String getDoneStatus() {
        return isDone ? "X" : " ";
    }

    /**
     * Formats input date to "yyyy/MM/dd hh:mm am/pm"
     *
     * @param dateTime LocalDateTime object
     * @return formatted dateTime string
     */
    public String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");
        return dateTime.format(dtf);
    }

    /**
     * Formats input date to "yyyy/MM/dd HHmm"
     *
     * @param dateTime LocalDateTime object
     * @return formatted dateTime string
     */
    public String format24HrDate(LocalDateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return dateTime.format(dtf);
    }

    /**
     * Getter for done
     *
     * @return this.done
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * getter for description
     *
     * @return this.desc
     */
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        return "[" + this.getDoneStatus() + "] " + this.desc;
    }
}
