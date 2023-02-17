package smudge.command;

import smudge.main.SmudgeException;
import smudge.main.Storage;
import smudge.main.Ui;
import smudge.task.Task;
import smudge.main.Tasklist;

import java.io.IOException;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public String execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException, SmudgeException {
        if (tasklist.containsTask(this.task)) {
            throw new SmudgeException("☹ Meow!!! This task already exists.");
        }
        else {
            int initialSize = tasklist.getTasksNum();
            tasklist.addTask(this.task);
            assert tasklist.getTasksNum() == initialSize + 1 : "tasklist size must increment after addition of task";
            storage.update(tasklist);
            return ui.printAddTaskMessage(this.task, tasklist);
        }
    }
}