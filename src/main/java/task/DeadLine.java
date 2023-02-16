package task;

import dukeexception.DukeException;
import utils.DateTimeUtils;

/**
 *  Handles all deadLines.
 */
public class DeadLine extends Task {
    /**
     * Store for deadLine date.
     */
    private final String dateTime;

    /**
     * Private constructor.
     *
     * @param str Task name.
     * @param checked Boolean for whether current task is marked.
     * @param dateTime DeadLine date.
     */
    private DeadLine(String str, boolean checked, String dateTime) {
        super(str, checked);
        this.dateTime = dateTime;
    }

    /**
     * Factory method that returns deadLine task from user input.
     *
     * @param inputLine Input from user.
     * @return DeadLine Task.
     * @throws DukeException Checks valid input from user.
     */
    public static DeadLine generate(String[] inputLine) throws DukeException {
        if (inputLine.length < 2) {
            throw new DukeException("Missing description");
        }
        String[] toPrintSplit = inputLine[1].split(" /by ", 2);
        if (toPrintSplit.length < 2) {
            throw new DukeException("Missing deadline");
        }
        return new DeadLine(toPrintSplit[0], false, DateTimeUtils.dateFormatter(toPrintSplit[1]));
    }

    /**
     * Factory method that returns deadLine task from saved file.
     *
     * @param taskLine Input from saved file.
     * @return DeadLine Task.
     */
    public static DeadLine generateTask(String[] taskLine) {
        boolean check = taskLine[1].equals("1");
        return new DeadLine(taskLine[2], check, taskLine[3]);
    }

    public String getDateTime() {
        return dateTime;
    }
    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getStoreTaskString() {
        return this.getTaskType() + " | " + this.getCheckedString()
                + " | " + this.getTask() + " | " + this.getDateTime();
    }

    @Override
    public String toString() {
        String str = this.getTask();
        boolean checked = this.isChecked();
        String dateTime = this.getDateTime();
        if (checked) {
            return "[D][X] " + str + " (by: " + dateTime + ")";
        } else {
            return "[D][ ] " + str + " (by: " + dateTime + ")";
        }
    }
}
