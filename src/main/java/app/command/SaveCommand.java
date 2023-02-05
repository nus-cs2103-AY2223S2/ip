package app.command;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.TaskList;

/**
 * Command to save the current TaskList state to storage.
 * Serves to interact with the Storage - this command is not available
 * to the user.
 */
public class SaveCommand extends Command {
    public SaveCommand() {
        this.isExit = false;
        this.isSave = false; // no data is changed in this command that warrants saving
    }

    /**
     * Saves the current TaskList to storage. If successful, notifies the user.
     * This Command is not presented as an option to the user and is exclusively
     * used for back-end only.
     * @param tl
     * @param ui
     * @param storage
     * @throws Exception
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        storage.saveToStorage(tl);
        return new Response("Changes are saved to storage.").toString();
    }
}
