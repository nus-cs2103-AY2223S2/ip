package duke.task;

import duke.exception.DukeException;
import duke.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class contains variables and methods related to Event tasks.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Creates an instance of Event task.
     * @param taskName name of Event task.
     * @param start LocalDate start date of Event task.
     * @param end LocalDate end date of Event task.
     */
    public Event(String taskName, LocalDate start, LocalDate end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    public static void processEvent(String command, TaskList lst) throws DukeException {
        String info = command.trim();
        if (info.isEmpty()) {
            throw new DukeException("event");
        }
        String[] details = info.split("/");
        if (details.length < 3) {
            throw new DukeException("timing");
        }
        try {
            String startString = details[1].split(" ", 2)[1];
            String endString = details[2].split(" ", 2)[1];
            LocalDate start = LocalDate.parse(startString);
            LocalDate end = LocalDate.parse(endString);
            Event e = new Event(details[0], start, end);
            lst.addTask(e);
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
        }
    }

    @Override
    public String toFile() {
        return String.format("E | %s | %s | %s\n", super.toFile(), this.start, this.end);
    }

    /**
     * Creates Event task from string from file.
     * @param taskNameData String containing taskName from file.
     * @param doneData String containing whether task is done from file string.
     * @param startData String containing start date from file.
     * @param endData String containing end date from file.
     * @return Event task.
     * @throws DukeException If String from file input is empty or of the wrong format.
     */
    public static Event toEventFromFileStr(String taskNameData, String doneData,
                                                String startData, String endData) throws DukeException {
        Event event = null;
        doneData = doneData.trim();
        startData = startData.trim();
        endData = endData.trim();
        taskNameData = taskNameData.trim();
        if (taskNameData.isEmpty()) {
            throw new DukeException("todo");
        }
        if (doneData.isEmpty()) {
            throw new DukeException("missing details");
        }
        if (startData.isEmpty() || endData.isEmpty()) {
            throw new DukeException("timing");
        }
        try {
            LocalDate start = LocalDate.parse(startData);
            LocalDate end = LocalDate.parse(endData);
            event = new Event(taskNameData, start, end);
            boolean completed = Integer.parseInt(doneData) == 1;
            event.setCompleted(completed);
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
        }
        return event;
    }

    @Override
    public String toString() {
        String s;
        String start = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endDate = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.isCompleted) {
            s = "[E]" + super.toString()
                    + " (from: " + start + " to: " + endDate + ")";
        } else {
            s = "[E]" + super.toString()
                    + " (from: " + start + " to: " + endDate + ")";
        }
        return s;
    }

}
