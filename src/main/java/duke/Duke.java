package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * The chatting bot Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialization of Duke and loading of file.
     *
     * @param filePath The path of the file that stores the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = loadTasks();
    }

    private TaskList loadTasks() {
        TaskList taskList;
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        return taskList;
    }

    /**
     * Process of user's input and storage of the new taskList.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            processUserInput();
            isExit = checkForExit();
        }
    }

    private void processUserInput() {
        try {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }
    }

    private boolean checkForExit() {
        String fullCommand = ui.readCommand();
        Command c = null;
        try {
            c = Parser.parse(fullCommand);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        return c.isExit();
    }

    /**
     * Set the filePath and activate run function.
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}



