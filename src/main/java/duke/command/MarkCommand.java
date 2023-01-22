package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int taskNo;

    public MarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(taskNo).markAsDone();
        ui.showMarkDone(tasks, taskNo);
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}