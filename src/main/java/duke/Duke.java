package duke;

import java.util.Scanner;

import duke.command.CommandParser;
import duke.exception.DukeException;
import duke.storage.FileStorage;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.CommandLineUi;
import duke.ui.Ui;

/**
 * Duke chat bot.
 */
public class Duke {
    private static final String SAVE_DATA_FILE_PATH = "saveData.txt";
    private static final String SAVE_DATA_DIR_PATH = "./data";

    private Ui ui;

    private Storage storage;

    private TaskList taskList;

    private CommandParser commandParser;

    /**
     * Initialize Duke chat bot.
     *
     * @param saveDataDirPath The directory where the save data file should be located.
     * @param saveDataFilePath The save data file name.
     */
    public Duke(String saveDataDirPath, String saveDataFilePath) {
        ui = new CommandLineUi();
        storage = new FileStorage(saveDataDirPath, saveDataFilePath);
        taskList = new TaskList(storage.load());
        commandParser = new CommandParser();
    }

    /**
     * Runs the chatbot by listening to input and executing commands.
     */
    public void run() {
        ui.showStartup();

        Scanner scanner = new Scanner(System.in);
        while (!commandParser.hasUserQuit()) {
            String input = scanner.nextLine();

            // Try executing command from input
            try {
                commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage);
            } catch (DukeException exception) {
                ui.showLine();
                ui.showText(exception.getMessage());
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(SAVE_DATA_DIR_PATH, SAVE_DATA_FILE_PATH).run();
    }
}
