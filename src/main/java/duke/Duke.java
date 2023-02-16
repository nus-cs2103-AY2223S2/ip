package duke;

import command.Command;
import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Parser;
import userinteraction.Ui;

/**
 * Runs the whole application for users to store and track tasks.
 */
public class Duke {
    private static final String FILE_PATH = "src/data/tasks.txt";
    private static final String DIRECTORY_PATH = "src/data";
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Public constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH, DIRECTORY_PATH);
        tasks = storage.readData();
    }

    /**
     * Runs entire program
     */
    public void run() {
        ui.printWelcomeMsg();
        ui.printLineString();
        boolean isBye = false;
        while (!isBye) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                if (command != null) {
                    command.execute(tasks, ui, storage);
                    isBye = command.isExit();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NullPointerException e) {
            return "Something is missing!";
        } catch (Exception e) {
            return "Unknown error, please try again!";
        }
    }

    public Ui getUi() {
        return ui;
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
