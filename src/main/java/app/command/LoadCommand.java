package app.command;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.TaskList;

public class LoadCommand extends Command {

    public LoadCommand() {
        this.isExit = false;
        this.isSave = false;
    }

    /**
     * Loads the Tasks specified in the storage text file into the TaskList by appending.
     * Informs the user of the number of tasks that have been successfully loaded.
     * @param tl
     * @param ui
     * @param storage
     * @throws Exception
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        Integer numLoaded = 0;
        try {
            numLoaded = storage.loadIntoTaskList(tl);
            return new Response("Successfully loaded " + numLoaded + " task(s) from storage.").toString();
        } catch (Exception e) {
            throw e;
        }
    }
}
