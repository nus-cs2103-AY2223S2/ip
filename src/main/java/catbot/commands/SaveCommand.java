package catbot.commands;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

public class SaveCommand extends Command {

    public SaveCommand() {
        super.setExit();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        storage.saveToFile(tasks);
    }
}
