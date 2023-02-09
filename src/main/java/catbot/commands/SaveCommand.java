package catbot.commands;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

/**
 * Handles saving the entire task list while exiting the program.
 */
public class SaveCommand extends Command {

    public SaveCommand() {
        super.setExit();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        ui.setNextOutput("Nice to meet mew!");
        storage.saveToFile(tasks);
    }
}
