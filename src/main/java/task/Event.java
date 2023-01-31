package task;

import dukeexception.DukeException;
import utils.DateTimeUtils;

public class Event extends Task {
    private final String start;
    private final String end;
    private Event(String str, boolean checked, String start, String end) {
        super(str, checked);
        this.start = start;
        this.end = end;
    }
    public static Event generate(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("Missing description");
        }
        String[] startEndTime = input[1].split(" /from ");
        if (startEndTime.length < 2) {
            throw new DukeException("Missing Start Time");
        }
        String[] dateTime = startEndTime[1].split(" /to ");
        if (dateTime.length < 2) {
            throw new DukeException("Missing End Time");
        }
        return new Event(startEndTime[0], false, DateTimeUtils.dateFormatter(dateTime[0]), DateTimeUtils.dateFormatter(dateTime[1]));
    }

    public static Event generateTask(String[] taskLine) {
        boolean check = taskLine[1].equals("1");
        return new Event(taskLine[2], check, taskLine[3], taskLine[4]);
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getStoreTaskString() {
        return this.getTaskType() + " | " + this.getCheckedString() + " | " + this.getStr() + " | " + this.getStart() + " | " + this.getEnd();
    }


    @Override
    public String toString() {
        String str = this.getStr();
        boolean checked = this.isChecked();
        String startTime = this.getStart();
        String endTime = this.getEnd();
        if (checked) {
            return "[E][X] " + str + " (from: " + startTime +
                    " to: " + endTime + ")";
        } else {
            return "[E][ ] " + str + " (from: " + startTime +
                    " to: " + endTime + ")";
        }
    }
}
