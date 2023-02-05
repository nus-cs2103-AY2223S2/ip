package app.command;

import java.io.IOException;
import java.util.Map;

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
     * @throws IOException propagated from FileWriter
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage)
            throws IOException {
        Map<String, Integer> successRates = storage.loadIntoTaskList(tl);
        int numSuccess = successRates.get("Successes");
        int numTotalRows = successRates.get("Total");
        return new Response("Successfully loaded "
                + numSuccess + " of " + numTotalRows
                +" task(s) from storage.").toString();
    }
}
