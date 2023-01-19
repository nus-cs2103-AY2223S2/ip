package handlers;

import services.TaskStorage;
import types.ICommand;
import types.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HDeleteTask implements ICommand {
    private final Pattern p = Pattern.compile("delete ([0-9]*)");
    private final TaskStorage ts;

    public HDeleteTask(TaskStorage ts) {
        this.ts = ts;
    }

    @Override
    public void take(String s) {
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            return;
        }
        int no = Integer.parseInt(m.group(1));
        if (no <= 0 || no > ts.getTaskCount()) {
            System.out.println("Invalid task number.");
        } else {
            Task removed = ts.getTaskByNo(no);
            ts.deleteByNo(no);
            System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d task(s) in the list.\n", removed,
                    ts.getTaskCount());
        }
    }

    @Override
    public boolean canTake(String s) {
        return p.matcher(s).matches();
    }
}
