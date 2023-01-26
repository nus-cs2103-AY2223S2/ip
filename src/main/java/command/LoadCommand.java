package command;

import chatbot.Storage;
import chatbot.Ui;
import task.TaskList;

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
