package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates Duke.
     *
     * @param dirPath The path to the directory that the file is stored in.
     * @param fileName The name of the storage file.
     */
    public Duke(String dirPath, String fileName) {
        storage = new Storage(dirPath, fileName);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.read());
        } catch (DukeException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command cmd = Parser.parse(input);
                cmd.execute(tasks, storage, ui);
                isExit = cmd.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
        storage.write(tasks.getTaskList());
        return;
    }

    /**
     * Starts the program.
     *
     * @param args A string array of arguments from the command line.
     */
    public static void main(String[] args) {
        new Duke("./data", "DukeList.txt").run();
    }
}
