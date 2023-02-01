package handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import services.TaskList;
import types.IHandler;
import types.data.Deadline;
import types.data.Event;
import types.data.Task;
import types.data.Todo;

/**
 * Command to add new task.
 */
public final class JAddTask implements IHandler {
    private static final Pattern TODO_PATTERN = Pattern.compile("(todo) (.*)");
    private static final Pattern EVENT_PATTERN = Pattern.compile("(event) (.*) /from (.*) /to (.*)");
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("(deadline) (.*) /by (.*)");
    private static final Pattern ALL_PATTERN = Pattern
            .compile("(todo) (.*)|(deadline) (.*) /by (.*)|(event) (.*) /from (.*) /to (.*)");
    private final TaskList ts;

    public JAddTask(TaskList ts) {
        this.ts = ts;
    }

    @Override
    public String take(String s) {
        if (!ALL_PATTERN.matcher(s).matches()) {
            return "";
        }

        Matcher m;
        Task t = null;

        m = TODO_PATTERN.matcher(s);
        if (m.matches()) {
            ts.addTask(t = Todo.create(m.group(2)));
        }

        m = EVENT_PATTERN.matcher(s);
        if (m.matches()) {
            ts.addTask(t = Event.create(m.group(2), m.group(3), m.group(4)));
        }

        m = DEADLINE_PATTERN.matcher(s);
        if (m.matches()) {
            ts.addTask(t = Deadline.create(m.group(2), m.group(3)));
        }

        return String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.\n", t,
                ts.getTaskCount());
    }

    @Override
    public boolean canTake(String s) {
        return ALL_PATTERN.matcher(s).matches();
    }
}
