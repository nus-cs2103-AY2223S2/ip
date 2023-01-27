package app.command;

import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.TaskList;

public class LoadCommand extends Command {

    public LoadCommand() {
        this.isExit = false;
        this.isSave = false;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        Integer numLoaded = 0;
        try {
            numLoaded = storage.loadIntoTaskList(tl);
            ui.reply("Successfully loaded " + numLoaded + " task(s) from storage.");
        } catch (Exception e) {
            ui.reply("Successfully loaded " + numLoaded + " task(s) from storage.");
            throw e;
        }
    }
}
