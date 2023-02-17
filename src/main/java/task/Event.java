package task;

import dukeexception.DukeException;
import utils.DateTimeUtils;

/**
 * Handles all events.
 */
public class Event extends Task {
    /**
     * Stores start date of event.
     */
    private final String start;
    /**
     * Stores end date of event.
     */
    private final String end;

    /**
     * Private constructor.
     *
     * @param str Task name.
     * @param checked Boolean for whether current task is marked.
     * @param start Start date of event.
     * @param end Start time of event.
     */
    private Event(String str, boolean checked, String start, String end) {
        super(str, checked);
        this.start = start;
        this.end = end;
    }

    /**
     * Factory method that returns event task from user input.
     *
     * @param input User input.
     * @return Event task.
     * @throws DukeException Checks valid user input.
     */
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
        return new Event(startEndTime[0], false,
                DateTimeUtils.dateFormatter(dateTime[0]), DateTimeUtils.dateFormatter(dateTime[1]));
    }

    /**
     * Factory method that returns event task from saved file.
     *
     * @param taskLine Input from saved file.
     * @return Event task.
     */
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
        return this.getTaskType() + " | " + this.getCheckedString() + " | "
                + this.getTask() + " | " + this.getStart() + " | " + this.getEnd();
    }


    @Override
    public String toString() {
        String str = this.getTask();
        boolean checked = this.isChecked();
        String startTime = this.getStart();
        String endTime = this.getEnd();
        if (checked) {
            return "[E][X] " + str + " (from: " + startTime
                    + " to: " + endTime + ")";
        } else {
            return "[E][ ] " + str + " (from: " + startTime
                    + " to: " + endTime + ")";
        }
    }
}
