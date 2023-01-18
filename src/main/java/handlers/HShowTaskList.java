package handlers;


import services.TaskStorage;
import types.ICommand;
import types.Task;

import java.util.regex.Pattern;

public final class HShowTaskList implements ICommand {
    private final Pattern p = Pattern.compile("list");
    private final TaskStorage ts;

    public HShowTaskList(TaskStorage ts) {
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