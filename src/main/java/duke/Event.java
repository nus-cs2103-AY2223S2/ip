package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import util.DateTimeParser;
import util.DukeException;
import util.TaskList;


/**
 * Event task which has a start and end datetime.
 * @author Merrick
 */
public class Event extends Task {
    protected LocalDateTime start;

    /**
     * Constructor of Event.
     * @param taskName Description of Event Task.
     * @param start Start datetime for the Event.
     * @param deadline End datetime for the Event.
     */
    public Event(String taskName, LocalDateTime start, LocalDateTime deadline) {
        super(taskName);
        assert start != null : "Start Time is not valid";
        assert deadline != null : "End Time is not valid";
        this.start = start;
        this.deadline = deadline;
        this.taskType = "E";
    }

    /**
     * Constructor of Event.
     * @param taskName Description of Event Task.
     * @param start Start datetime for the Event.
     * @param deadline End datetime for the Event.
     * @param isCompleted Completion status for the Event.
     */
    public Event(String taskName, LocalDateTime start, LocalDateTime deadline, boolean isCompleted) {
        super(taskName, isCompleted);
        this.start = start;
        this.deadline = deadline;
        this.taskType = "E";
    }

    /**
     * Creates an Event Task from user input.
     * @param command User input to be used to create the Event Task.
     * @param t TaskList object to add the newly created Task into the list of tasks.
     * @return Message to be shown to the user.
     * @throws DukeException If command is invalid.
     */
    public static String createEvent(String command, TaskList t) throws DukeException {
        ArrayList<String> input = new ArrayList<>(Arrays.asList(command.split(" ")));
        if (input.size() <= 1) {
            throw new DukeException("event");
        }
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        StringBuilder taskName = new StringBuilder();
        String start = "";
        StringBuilder deadline = new StringBuilder();
        for (int i = 1; i < input.size(); i++) {
            if (i < fromIndex) {
                taskName.append(input.get(i));
                if (i < fromIndex - 1) {
                    taskName.append(" ");
                }
            } else if ((i > fromIndex) && (i < toIndex)) {
                start += input.get(i);
                if (i < toIndex) {
                    start += " ";
                }
            } else if (i > toIndex) {
                deadline.append(input.get(i));
                if (i < input.size() - 1) {
                    deadline.append(" ");
                }
            }
        }
        Event e = new Event(taskName.toString(), DateTimeParser.dateTimeParser(start.stripTrailing()),
                DateTimeParser.dateTimeParser(deadline.toString()));
        return t.addTask(e);
    }

    /**
     * Changes the deadline of the Event Task.
     * @param days Number of days to shift back.
     * @param hours Number of hours to shift back.
     * @param minutes Number of minutes to shift back.
     * @return new deadline of the Event Task.
     */
    public String snoozeDeadline(int days, int hours, int minutes) {
        this.deadline = this.deadline.plusDays(days);
        this.deadline = this.deadline.plusHours(hours);
        this.deadline = this.deadline.plusMinutes(minutes);
        return String.format("New deadline is %s!", DateTimeParser.datetimeFormatter(this.deadline));
    }

    /**
     * Changes the deadline of the Event by a default value of 5 minutes.
     * @return New deadline of the Event.
     */
    public String snoozeDeadline() {
        return this.snoozeDeadline(0, 0, 5);
    }

    @Override
    public String saveTaskString() {
        return String.format(super.saveTaskString() + "|%s|%s", this.start, this.deadline);
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                DateTimeParser.datetimeFormatter(this.start), DateTimeParser.datetimeFormatter(this.deadline));
    }
}
