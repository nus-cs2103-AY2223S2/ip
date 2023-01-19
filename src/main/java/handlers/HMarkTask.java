package handlers;

import services.TaskStorage;
import types.ICommand;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HMarkTask implements ICommand {
    private static final Pattern p = Pattern.compile("(un)?mark ([0-9]*)");
    private final TaskStorage ts;

    public HMarkTask(TaskStorage ts) {
        this.ts = ts;
    }

    @Override
    public void take(String s) {
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            return;
        }
        int no = Integer.parseInt(m.group(2));
        if (Objects.equals(m.group(1), "un")) {
            ts.unmarkByNo(no);
            System.out.printf("OK, I've marked this task as not done yet:\n[ ] %s\n", ts.getTaskByNo(no).getName());
        } else {
            ts.markByNo(no);
            System.out.printf("Nice! I've marked this task as done:\n[X] %s\n", ts.getTaskByNo(no).getName());
        }
    }

    @Override
    public boolean canTake(String s) {
        return p.matcher(s).matches();
    }
}
