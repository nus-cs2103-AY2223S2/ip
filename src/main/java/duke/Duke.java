package duke;

import java.io.IOException;

import duke.command.Command;
import duke.display.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.StorageFileException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

<<<<<<< HEAD
=======
/**
 * The main Duke class to run
 */
>>>>>>> branch-A-CodingStandard
public class Duke {
    private final Storage storage;
    private TaskList list;
    private final Ui ui;

    /**
     * The constructor that takes in a String filePath that specifies the path for the storage file.
     *
     * @param filePath Specifies the path for the storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        list = new TaskList();

        try {
            list = storage.load();
            System.out.println(list);
        } catch (InvalidInputException e) {
            ui.displayWithBar(e.getMessage());
            list = new TaskList();
        } catch (StorageFileException e) {
            ui.displayWithBar(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Executes the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(list, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayWithBar(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
