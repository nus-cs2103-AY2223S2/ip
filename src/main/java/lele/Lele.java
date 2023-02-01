package lele;

import lele.command.Command;
import lele.exception.LeleException;
import lele.exception.EmptyStorageException;
import lele.parser.Parser;
import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;

import java.io.IOException;

/**
 * The main class that represents the chatbot, Lele, which helps
 * to build a checklist consisting of todos, deadlines, events.
 */
public class Lele {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructor for Lele.
     *
     * @param filePath For Duke to find the path to an existing data.
     */
    public Lele(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (EmptyStorageException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * The function that handles the parsing, storage
     * and ui of Lele. Also handles the exceptions coming
     * from Command.
     */
    public void run() {
        this.ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (LeleException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method. Initialises a Lele instance and
     * starts up the cogwheel.
     *
     * @param args Takes in the command line argument.
     */
    public static void main(String[] args) {
        new Lele("./data/lele.txt").run();
    }


}
