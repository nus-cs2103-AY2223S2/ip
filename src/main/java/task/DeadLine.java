package task;

import dukeexception.DukeException;
import utils.DateTimeUtils;

public class DeadLine extends Task {
    private String dateTime;
    private DeadLine(String str, boolean checked, String dateTime) {
        super(str, checked);
        this.dateTime = dateTime;
    }

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
        return this.getTaskType() + " | " + this.getCheckedString() + " | " + this.getStr() + " | " + this.getDateTime();
    }

    @Override
    public String toString() {
        String str = this.getStr();
        boolean checked = this.isChecked();
        String dateTime = this.getDateTime();
        if (checked) {
            return "[D][X] " + str + " (by: " + dateTime + ")";
        } else {
            return "[D][ ] " + str + " (by: " + dateTime + ")";
        }
    }
}
