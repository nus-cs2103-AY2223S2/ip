package duke;

import util.DateTimeParser;
import util.DukeException;
import util.TaskList;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;


/**
 * Subclass of Task which has a start and end datetime.
 *
 * @author Merrick
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor of Event.
     * @param taskName Description of Event Task.
     * @param start Start datetime for the Event.
     * @param end End datetime for the Event.
     */
    public Event(String taskName, LocalDateTime start, LocalDateTime end) {
        super(taskName);
        this.start = start;
        this.end = end;
        this.taskType = "E";
    }

    /**
     * Constructor of Event.
     * @param taskName Description of Event Task.
     * @param start Start datetime for the Event.
     * @param end End datetime for the Event.
     * @param completed Completion status for the Event.
     */
    public Event(String taskName, LocalDateTime start, LocalDateTime end, boolean completed) {
        super(taskName, completed);
        this.start = start;
        this.end = end;
        this.taskType = "E";
    }

    /**
     * Static method to create a Event Task from user input.
     * @param command User input to be used to create the Event Task.
     * @param t TaskList object to add the newly created Task into the list of tasks.
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
        StringBuilder end = new StringBuilder();
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
                end.append(input.get(i));
                if (i < input.size() - 1) {
                    end.append(" ");
                }
            }
        }
        Event e = new Event(taskName.toString(), DateTimeParser.dateTimeParser(start.stripTrailing()),
                DateTimeParser.dateTimeParser(end.toString()));
        return t.addTask(e);
    }

    @Override
    public String saveTaskString() {
        return String.format(super.saveTaskString() + "|%s|%s", this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                DateTimeParser.datetimeFormatter(this.start), DateTimeParser.datetimeFormatter(this.end));
    }
}
