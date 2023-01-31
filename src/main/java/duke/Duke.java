package duke;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;

/**
 * Main class of Duke.
 * Starts a new Ui and Storage to prepare running duke and runs when they are ready.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor of Duke.
     * @param filePath the path of the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main method of Duke. Sets the path of the data file saved.
     */
    public static void main(String[] args) {
        new Duke("./src/main/java/duke/data.txt").run();
    }

    /**
     * Runs Duke by using ui to read command and execute the command.
     * Continues running until the ui reads EndCommand.
     */
    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        try {
            storage.writeToFile(tasks.toTxtString());
        } catch (IOException e) {
            System.out.println("Error during saving");
        }
    }
}
