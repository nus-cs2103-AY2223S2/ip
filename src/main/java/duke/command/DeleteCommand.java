package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task delTask = tasks.get(taskNo);
        tasks.remove(taskNo);
        ui.showDel(delTask, tasks);
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
