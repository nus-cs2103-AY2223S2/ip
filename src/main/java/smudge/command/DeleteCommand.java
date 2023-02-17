package smudge.command;

import smudge.main.SmudgeException;
import smudge.main.Storage;
import smudge.task.Task;
import smudge.main.Tasklist;
import smudge.main.Ui;
import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public String execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException, SmudgeException {
        try {
            int initialSize = tasklist.getTasksNum();
            //assert taskNum > 0 && taskNum <= initialSize : "task number must be > 0 and <= to size of tasklist";
            Task task = tasklist.getTasks().get(this.taskNum - 1);
            tasklist.deleteTask(this.taskNum - 1);
            //assert tasklist.getTasksNum() == initialSize - 1 : "tasklist size must decrement after deletion of task";
            storage.update(tasklist);
            return ui.printDeleteTaskMessage(task, tasklist);
        } catch (IndexOutOfBoundsException e) {
            throw new SmudgeException("☹ Meow!!! Task number to be unmarked is out of bounds of current list. " +
                    "Please use a task number within current list.");
        }
    }
}