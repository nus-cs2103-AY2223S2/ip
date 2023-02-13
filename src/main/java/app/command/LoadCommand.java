package app.command;

import java.io.IOException;
import java.util.Map;

import app.chatbot.Response;
import app.chatbot.Storage;
import app.task.TaskList;

public class LoadCommand extends Command {
    private static final String IO_ERROR = "IO Error - I dunno what went wrong :(";
    public LoadCommand() {
        this.isExit = false;
        this.isSave = false;
    }

    /**
     * Loads the Tasks specified in the storage text file into the TaskList by appending.
     * Informs the user of the number of tasks that have been successfully loaded.
     * @param tl
     * @param storage
     * @throws IOException propagated from FileWriter
     */
    @Override
    public Response execute(TaskList tl, Storage storage) {
        Map<String, Integer> successRates;
        try {
            successRates = storage.loadIntoTaskList(tl);
        } catch (IOException e) {
            return new Response(IO_ERROR, false);
        }
        int numSuccess = successRates.get("Successes");
        int numTotalRows = successRates.get("Total");
        return new Response("Successfully loaded "
                + numSuccess + " of " + numTotalRows
                +" task(s) from storage.", true);
    }
}
