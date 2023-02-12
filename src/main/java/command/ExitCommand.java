package command;

import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String inputArr) {
        super(inputArr);
    }

    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) {
        ui.printByeMsg();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}