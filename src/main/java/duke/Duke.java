package duke;

import java.io.IOException;

import duke.exception.DukeException;
import duke.helper.Parser;
import duke.helper.TaskList;
import duke.helper.Ui;
import duke.storage.FileSystem;

/**
 * Main class of the project
 */
public class Duke {
    private final Ui ui;
    private Parser parser;
    private FileSystem db;
    private TaskList tasks;

    /**
     * Constructor for Duke class
     *
     * @param filePath path of the storage file
     */
    public Duke(String filePath) {
        this.ui = new Ui();

        try {
            db = new FileSystem(filePath);
            this.tasks = new TaskList(db.loadFromFile());
            this.parser = new Parser(tasks);
        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Runs the Duke program
     */
    public void run() {
        ui.showWelcome();
        String[] splitStr = ui.getNextLine();

        while (!splitStr[0].equals("bye")) {
            try {
                this.parser.parseInputs(splitStr);
            } catch (DukeException | IOException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                ui.showErrorMsg(splitStr[0]);
            } catch (IndexOutOfBoundsException e) {
                ui.showErrorMsg(tasks.getTasks().size());
            } finally {
                splitStr = ui.getNextLine();
            }
        }
        db.updateFile(tasks);
        ui.showExit();
    }

    /**
     * Main method to execute Duke program
     *
     * @param arg Command line argument
     */
    public static void main(String[] arg) {
        new Duke("data/dukeTasks.txt").run();
    }
}
