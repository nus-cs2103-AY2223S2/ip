package duke;

import util.DateTimeParser;
import util.DukeException;
import util.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String taskName, LocalDateTime start, LocalDateTime end) {
        super(taskName);
        this.start = start;
        this.end = end;
        this.taskType = "E";
    }

    public Event(String taskName, LocalDateTime start, LocalDateTime end, boolean completed) {
        super(taskName, completed);
        this.start = start;
        this.end = end;
        this.taskType = "E";
    }

    public static void createEvent(String command, TaskList t) throws DukeException {
        ArrayList<String> input = new ArrayList(Arrays.asList(command.split(" ")));
        if (input.size() <= 1) throw new DukeException("event");
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        String taskName = "";
        String start = "";
        String end = "";
        for (int i = 1; i < input.size(); i++) {
            if (i < fromIndex) {
                taskName += input.get(i);
                if (i < fromIndex - 1) {
                    taskName += " ";
                }
            } else if ((i > fromIndex) && (i < toIndex)) {
                start += input.get(i);
                if (i < toIndex) {
                    start += " ";
                }
            } else if (i > toIndex) {
                end += input.get(i);
                if (i < input.size() - 1) {
                    end += " ";
                }
            }
        }
        System.out.println(taskName);
        Event e = new Event(taskName, DateTimeParser.dateTimeParser(start.stripTrailing()), DateTimeParser.dateTimeParser(end));
        t.addTask(e);
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
