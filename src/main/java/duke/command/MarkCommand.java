package duke.command;

import duke.main.Storage;
import duke.main.Tasklist;
import duke.main.Ui;

import java.io.IOException;

public class MarkCommand extends Command {

    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException {
        tasklist.markDone(this.taskNum - 1);
        storage.update(tasklist);
        ui.printMarkTaskMessage(tasklist.getTasks().get(this.taskNum - 1));
    }
}