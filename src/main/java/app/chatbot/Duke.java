package app.chatbot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import app.command.Command;
import app.command.LoadCommand;
import app.command.SaveCommand;
import app.command.SummaryCommand;
import app.task.TaskList;

/**
 * Entry point to the app backend.
 */
public class Duke {
    private static final Path STORAGE_LOCATION = Paths.get(".", "data", "storage.txt");
    private static final String FIRST_LOAD_MESSAGE = "Heyo, I think this is your first time loading "
            + "up this app! I have some default tasks written in for you below - feel free to edit or delete "
            + "them at your leisure.\n\n"
            + "Also, be sure to check out the docs at https://ajjajjajjajj.github.io/ip/!\n\n";
    private Storage storage;
    private TaskList taskList;


    /**
     * Constructor for Duke.
     */
    public Duke() {
        try {
            this.storage = new Storage(STORAGE_LOCATION);
            this.taskList = new TaskList();
        } catch (IOException | Storage.InvalidStorageException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the chatbot to process a String input into a Command and
     * executes it, returning a response string suitable for the chatbot
     * to reply with.
     * @param fullCommand
     * @return
     */
    public String getResponse(String fullCommand) {
        StringBuilder response = new StringBuilder();
        try {
            Command c = Parser.parse(fullCommand);
            Response cmdResponse = c.execute(taskList, storage);
            response.append(cmdResponse.toString());
            if (c.isSave() && cmdResponse.isSuccess()) {
                Command save = new SaveCommand();
                response.append(save.execute(taskList, storage));
            }
            return response.toString();
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    /**
     * Loads saved data from storage.
     * @return Success message for loaded storage, or a message informing a load failure.
     */
    public String loadStorageData() {
        Command loadCommand = new LoadCommand();
        System.out.println("Loading storage data...");
        String message = loadCommand.execute(taskList, storage).toString();

        // saves default storage into real storage
        if (storage.isFirstLoad()) {
            Command saveDefaultStorageCommand = new SaveCommand();
            saveDefaultStorageCommand.execute(taskList, storage);
            message = FIRST_LOAD_MESSAGE + message;
        }
        return message;
    }

    public String showSummary() {
        Command summaryCommand = new SummaryCommand();
        return summaryCommand.execute(taskList, storage).toString();
    }
}
