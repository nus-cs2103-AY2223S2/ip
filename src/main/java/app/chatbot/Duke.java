package app.chatbot;

import app.command.Command;
import app.command.LoadCommand;
import app.command.SaveCommand;
import app.task.TaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private static final Path STORAGE_LOCATION = Paths.get(".", "data", "storage.txt");

    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    Duke(Path path) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(path);
            this.taskList = new TaskList();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        ui.showWelcome();

        // load in saved data
        Command loadCommand = new LoadCommand();
        try {
            loadCommand.execute(taskList, ui, storage);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
        ui.showLine();

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
        Duke duke = new Duke(STORAGE_LOCATION);
        duke.run();
    }
}
