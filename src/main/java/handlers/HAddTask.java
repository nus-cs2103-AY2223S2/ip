package handlers;


import services.TaskStorage;
import types.ICommand;
import types.Task;

import java.util.regex.Pattern;

public class HAddTask implements ICommand {
    private final Pattern p = Pattern.compile(".*");
    private final TaskStorage ts;

    public HAddTask(TaskStorage ts) {
        this.ts = ts;
    }

    @Override
    public void take(String s) {
        ts.addTask(Task.ofName(s));
        System.out.printf("added: %s\n", s);
    }

    @Override
    public boolean canTake(String s) {
        return p.matcher(s).matches();
    }
}