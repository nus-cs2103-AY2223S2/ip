package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
            ui.printUnMarkStatus(taskList, index);
            storage.updateStorage();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
