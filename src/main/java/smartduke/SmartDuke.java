package smartduke;

import smartduke.command.Command;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SmartDuke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public SmartDuke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Begins the chat session with the user.
     */
    public void run() {
        /* Greet the user */
        ui.showLine();
        ui.showWelcome();
        ui.showLine();

        boolean isExit = false;

        while (!isExit) {
            try {
                String userCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(userCommand);
                c.execute(this.taskList, this.ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.close();

        try {
            this.storage.save(this.taskList);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new SmartDuke(Paths.get(".", "data", "smartduke.txt")).run();
    }
}