package app.command;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.task.TaskList;

import java.io.IOException;

/**
 * Command to save the current TaskList state to storage.
 * Serves to interact with the Storage - this command is not available
 * to the user.
 */
public class SaveCommand extends Command {
    private static final String SAVE_SUCCESSFUL_MSG = "Changes are saved to storage.";
    private static final String IO_ERROR = "Something prevented the changes from being saved :(";
    public SaveCommand() {
        this.isExit = false;
        this.isSave = false; // no data is changed in this command that warrants saving
    }

    /**
     * Saves the current TaskList to storage. If successful, notifies the user.
     * This Command is not presented as an option to the user and is exclusively
     * used for back-end only.
     * @param tl
     * @param storage
     * @throws Exception
     */
    @Override
    public Response execute(TaskList tl, Storage storage) {
        try {
            storage.saveToStorage(tl);
        } catch (IOException e) {
            return new Response(IO_ERROR, false);
        }
        return new Response(SAVE_SUCCESSFUL_MSG, true);
    }
}
