package duke;

import duke.command.CommandParser;
import duke.exception.DukeException;
import duke.storage.FileStorage;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke chat bot.
 */
public class Duke {
    private Ui ui;

    private Storage storage;

    private TaskList taskList;

    private CommandParser commandParser;

    /**
     * Initializes Duke chat bot.
     *
     * @param saveDataDirPath The directory where the save data file should be located.
     * @param saveDataFilePath The save data file name.
     * @param ui The user interface to display responses.
     */
    public Duke(String saveDataDirPath, String saveDataFilePath, Ui ui) {
        this.ui = ui;
        this.storage = new FileStorage(saveDataDirPath, saveDataFilePath);
        this.taskList = new TaskList(storage.load());
        this.commandParser = new CommandParser();
    }

    /**
     * Starts the chatbot.
     */
    public void start() {
        ui.showStartup();
    }

    /**
     * Passes input for handling by command parser.
     */
    public void handleInput(String input) {
        try {
            commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage);
        } catch (DukeException exception) {
            ui.showLine();
            ui.showText(exception.getMessage());
            ui.showLine();
        }
    }
}
