package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.main.Tasklist;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int taskNum;
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException {
        tasklist.markUndone(this.taskNum - 1);
        storage.update(tasklist);
        ui.printUnmarkTaskMessage(tasklist.getTasks().get(this.taskNum - 1));
    }
}