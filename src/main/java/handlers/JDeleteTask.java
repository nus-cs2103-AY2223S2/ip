package handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import services.TaskList;
import types.IHandler;
import types.data.Task;

/**
 * Command to delete a task.
 */
public class JDeleteTask implements IHandler {
    private static final Pattern PATTERN = Pattern.compile("delete ([0-9]*)");
    private final TaskList ts;

    public JDeleteTask(TaskList ts) {
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
        } else {
            Task removed = ts.getTaskByNo(no);
            ts.deleteByNo(no);
            return String.format("Noted. I've removed this task:\n%s\nNow you have %d task(s) in the list.\n", removed,
                    ts.getTaskCount());
        }
    }

    @Override
    public boolean canTake(String s) {
        return PATTERN.matcher(s).matches();
    }
}
