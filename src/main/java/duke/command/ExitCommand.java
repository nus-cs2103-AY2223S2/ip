package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.FileNotFoundException;

public class ExitCommand extends Command{
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) throws FileNotFoundException {
        storage.updateFile(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
