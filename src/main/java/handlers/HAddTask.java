package handlers;


import services.TaskStorage;
import types.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HAddTask implements ICommand {
    private final Pattern todo_p = Pattern.compile("(todo) (.*)");
    private final Pattern event_p = Pattern.compile("(event) (.*) /from (.*) /to (.*)");
    private final Pattern deadline_p = Pattern.compile("(deadline) (.*) /by (.*)");
    private final Pattern p = Pattern.compile("(todo) (.*)|(deadline) (.*) /by (.*)|(event) (.*) /from (.*) /to (.*)");
    private final TaskStorage ts;

    public HAddTask(TaskStorage ts) {
        this.ts = ts;
    }

    @Override
    public void take(String s) {
        if (!p.matcher(s).matches()) {
            return;
        }

        Matcher m;
        Task t = null;

        m = todo_p.matcher(s);
        if (m.matches()) {
            ts.addTask(t = Todo.create(m.group(2)));
        }

        m = event_p.matcher(s);
        if (m.matches()) {
            ts.addTask(t = Event.create(m.group(2), m.group(3), m.group(4)));
        }

        m = deadline_p.matcher(s);
        if (m.matches()) {
            ts.addTask(t = Deadline.create(m.group(2), m.group(3)));
        }

        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.\n", t, ts.getTaskCount());
    }

    @Override
    public boolean canTake(String s) {
        return p.matcher(s).matches();
    }
}