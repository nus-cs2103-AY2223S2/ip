package app.chatbot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import app.command.Command;
import app.command.LoadCommand;
import app.command.SaveCommand;
import app.task.TaskList;

public class Duke {
    private static final Path STORAGE_LOCATION = Paths.get(".", "data", "storage.txt");

    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage(this.STORAGE_LOCATION);
            this.taskList = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the chatbot session. Upon startup, attempts to load in stored data
     * for tasks at the location <i>"./data/storage"</i>. Ends session when user types in
     * <i>"bye"</i>.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show divider
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();

                if (c.isSave()) {
                    Command save = new SaveCommand();
                    save.execute(taskList, ui, storage);
                }

            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }


    public String getResponse(String fullCommand) {
        boolean isExit = false;
        StringBuilder response = new StringBuilder();
        try {
            Command c = Parser.parse(fullCommand);
            response.append(c.execute(taskList, ui, storage));
            isExit = c.isExit();
            if (c.isSave()) {
                Command save = new SaveCommand();
                response.append(save.execute(taskList, ui, storage));
            }
            return response.toString();
        } catch (Exception e) {
            return(e.getMessage());
        }
    }

    public String loadStorageData() {
        Command loadCommand = new LoadCommand();
        try {
            String response = loadCommand.execute(taskList, ui, storage);
            return response;
        } catch (Exception e) {
            return(e.getMessage());
        }
    }
}
