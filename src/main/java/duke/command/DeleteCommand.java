package duke.command;

import duke.main.Storage;
import duke.task.Task;
import duke.main.Tasklist;
import duke.main.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException {
        Task task = tasklist.getTasks().get(this.taskNum - 1);
        tasklist.deleteTask(this.taskNum - 1);
        storage.update(tasklist);
        ui.printDeleteTaskMessage(task, tasklist);
    }
}