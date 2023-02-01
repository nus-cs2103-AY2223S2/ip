package handlers;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import services.TaskList;
import types.IHandler;

/**
 * Command to mark a task as completed.
 */
public final class JMarkTask implements IHandler {
    private static final Pattern PATTERN = Pattern.compile("(un)?mark ([0-9]*)");
    private final TaskList ts;

    public JMarkTask(TaskList ts) {
        this.ts = ts;
    }

    @Override
    public String take(String s) {
        Matcher m = PATTERN.matcher(s);
        if (!m.matches()) {
            return "";
        }
        int no = Integer.parseInt(m.group(2));
        if (Objects.equals(m.group(1), "un")) {
            ts.unmarkByNo(no);
            return String.format("OK, I've marked this task as not done yet:\n[ ] %s\n", ts.getTaskByNo(no).getName());
        } else {
            ts.markByNo(no);
            return String.format("Nice! I've marked this task as done:\n[X] %s\n", ts.getTaskByNo(no).getName());
        }
    }

    @Override
    public boolean canTake(String s) {
        return PATTERN.matcher(s).matches();
    }
}
