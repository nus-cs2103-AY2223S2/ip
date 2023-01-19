package handlers;

import services.TaskStorage;
import types.IHandler;
import types.data.Task;

import java.util.regex.Pattern;

public final class JShowTaskList implements IHandler {
    private static final Pattern p = Pattern.compile("list");
    private final TaskStorage ts;

    public JShowTaskList(TaskStorage ts) {
        this.ts = ts;
    }

    @Override
    public void take(String s) {
        int no = 1;
        for (Task i : ts.getTasks()) {
            System.out.printf("%d. %s\n", no++, i);
        }
    }

    @Override
    public boolean canTake(String s) {
        return p.matcher(s).matches();
    }
}