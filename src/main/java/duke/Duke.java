package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * Main Class which runs the whole chatbot.
 */
public class Duke {

    private StorageList storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new StorageList(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(String.valueOf(e));
            tasks = new TaskList();
        }
    }

    /**
     * Method which starts the program to output the various messages and checks the commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.showLoadingError("Sorry i did not understand that command!");
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showLoadingError("Pls fill in the command");
            } finally {
                storage.updateStorage();
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }


}




