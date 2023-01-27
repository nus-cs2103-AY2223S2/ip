package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int taskNo;

    public UnmarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.get(taskNo).unmarkAsDone();
        ui.showUnmarkDone(tasks, taskNo);
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}