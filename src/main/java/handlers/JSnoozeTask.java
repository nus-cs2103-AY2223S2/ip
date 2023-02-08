package handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import services.TaskList;
import types.IHandler;
import types.data.Deadline;
import types.data.Task;
import utilities.DateTimeParser;

/**
 * Command to snooze a task.
 */
public class JSnoozeTask implements IHandler {
    private static final Pattern PATTERN = Pattern.compile("snooze ([0-9]*) /by (.*)");
    private final TaskList ts;

    public JSnoozeTask(TaskList ts) {
        this.ts = ts;
    }

    @Override
    public String take(String s) {
        Matcher m = PATTERN.matcher(s);
        if (!m.matches()) {
            return "";
        }
        int no = Integer.parseInt(m.group(1));
        if (no <= 0 || no > ts.getTaskCount()) {
            return "Invalid task number.\n";
        }

        Task t = ts.getTaskByNo(no);
        if (!(t instanceof Deadline)) {
            return "Error, the specified Task is not a Deadline. No scheduling available\n";
        }
        Deadline d = (Deadline) t;
        d.reschedule(DateTimeParser.parse(m.group(2)));
        return "Noted. I've rescheduled it.";
    }

    @Override
    public boolean canTake(String s) {
        return PATTERN.matcher(s).matches();
    }
}
