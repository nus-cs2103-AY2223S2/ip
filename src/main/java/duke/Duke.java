package duke;

import duke.commands.Command;
import duke.taskType.TaskList;
import duke.commands.*;
import javafx.application.Platform;

/**
 * The main class of this task list management bot.
 */
public class Duke {
    private TaskList lst;
    private Ui ui;
    private Storage storage;

    public Duke() {
    }

    /**
     * The default and only constructor of Duke class.
     * Initialize UI, Storage, and the list to store tasks.
     *
     * @param filePath the path of the file to store the task list
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.lst = storage.load();
    }

    public String dealWithCommand(String cmd) {
        Command command = Parser.parse(cmd);
        if (command.isBye()) {
            Platform.exit();
        }
        return command.operate(lst, ui, storage);
    }

    public String getResponse(String input) {
        return dealWithCommand(input);
    }
}
