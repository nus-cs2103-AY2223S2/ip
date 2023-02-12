import duke.*;
import duke.Command.Command;
import duke.Exception.DukeException;

/**
 * The main class to run Duke
 * @author Zong Hao (ZHTang29)
 */

public class Duke {
    private TaskList tl;
    private Storage storage;
    private UI ui;

    /**
     * Constructor to set up Duke, takes in the filepath and tries to load an existing list from the file.
     * If successful, the current task list will be loaded with the contents of the file.
     * If unsuccessful, create a new file with the file name, and new empty task list to add to.
     * @param filepath The path of the file to check
     */
    public Duke(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);

        try {
            tl = new TaskList(storage.loadFromFile());
        } catch (DukeException exception) {
            ui.showLoadingError();
            tl = new TaskList();
        }
    }

    /**
     * Runs the Duke application
     */
    public void run() {
        ui.getWelcomeMessage();

        while (true) {
            try {
                String userInput = ui.readCommand();
                Command c = new Parser().parseCommand(userInput);
                c.execute(tl, ui, storage);
            } catch (DukeException exception) {
                ui.showError(exception.toString());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("tasks.txt");
        duke.run();
    }
}
