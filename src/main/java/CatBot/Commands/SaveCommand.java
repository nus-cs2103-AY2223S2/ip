package CatBot.Commands;

import CatBot.CatBotException;
import CatBot.Storage.Storage;
import CatBot.TaskList.TaskList;
import CatBot.Ui.Ui;

public class SaveCommand extends Command {

    public SaveCommand() {
        super.setExit();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        storage.saveToFile(tasks);
    }
}
