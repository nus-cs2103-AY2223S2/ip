package command;

import chatbot.Storage;
import chatbot.Ui;
import task.TaskList;

public class SaveCommand extends Command {
    public SaveCommand() {
        this.isExit = false;
        this.isSave = false; // no data is changed in this command that warrants saving
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        try {
            storage.saveToStorage(tl);
            ui.reply("Changes are saved to storage.");
        } catch (Exception e) {
            throw e;
        }
    }
}
