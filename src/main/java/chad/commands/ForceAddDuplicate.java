package chad.commands;

import chad.backend.TaskList;
import chad.tasks.Task;

public class ForceAddDuplicate extends Command {
    private Task task;
    private TaskList tasklist;

    public ForceAddDuplicate(Task task, TaskList tasklist) {
        this.task = task;
        this.tasklist = tasklist;
    }


    @Override
    public String execute() {
        tasklist.add(task);
        return "Okay, added this duplicate task.";
    }
}
