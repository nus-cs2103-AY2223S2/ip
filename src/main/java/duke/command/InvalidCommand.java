package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.FileNotFoundException;

public class InvalidCommand extends Command{
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {
        ui.printText("Invalid command please try again");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
