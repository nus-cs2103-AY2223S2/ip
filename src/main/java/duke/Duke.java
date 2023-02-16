package duke;

import command.Command;
import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Parser;
import userinteraction.Ui;

/**
 * The main class of the program.
 */
public class Duke {
    private static final String FILE_PATH = ".\\src\\data\\duke.txt";
    private static final String HELP_FILEPATH = ".\\src\\main\\resources\\explanation\\help.txt";

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Class constructor.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH, HELP_FILEPATH);
        taskList = storage.loadData();
    }

    /**
     * Runs the whole program.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                if (command != null) {
                    command.process(taskList, ui, storage);
                    isExit = command.isExit();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.process(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Main method of the Duke.
     *
     * @param args String array argument
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
