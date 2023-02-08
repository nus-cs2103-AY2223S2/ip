package handlers;

import java.util.Objects;
import java.util.regex.Pattern;

import services.TaskList;
import types.IHandler;
import types.data.Task;

/**
 * Command to show all tasks.
 */
public final class JShowTaskList implements IHandler {
    private static final Pattern PATTERN = Pattern.compile("list");
    private final TaskList ts;

    public JShowTaskList(TaskList ts) {
        this.ts = ts;
    }

    @Override
    public String take(String s) {
        assert Objects.nonNull(ts);
        int no = 1;
        StringBuilder sb = new StringBuilder(20 * ts.getTaskCount());
        for (Task i : ts.getTasks()) {
            sb.append(String.format("%d. %s\n", no++, i));
        }
        return sb.toString();
    }

    @Override
    public boolean canTake(String s) {
        return PATTERN.matcher(s).matches();
    }
}
