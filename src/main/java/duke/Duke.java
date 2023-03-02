package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.util.Map;
import java.util.HashMap;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    /**
     * Minimum length of a string command is given by
     * The length of the command +2 (for whitespace and
     * at least 1 letter for the command)
     */
    static final String HOME_DIRECTORY = System.getProperty("user.dir");
    static final Path DUKE_LIST_DIRECTORY = Paths.get(HOME_DIRECTORY, "SavedList.txt");
    static final HashMap<String, Integer> MIN_VALID_LENGTH = new HashMap<>(Map.of(
            "todo", 6,
            "deadline", 10,
            "event", 7,
            "mark", 6,
            "unmark", 8,
            "delete", 8
    ));
    /**
     * Correct formatting of commands given that the name of the command is correct
     */
    static final HashMap<String, String> CORRECT_FORMAT = new HashMap<>(Map.of(
            "todo", "todo THE TASK",
            "deadline", "deadline THE TASK /by yyyy-mm-ddThh:mm:ss",
            "event", "event THE TASK /from TIME /to TIME",
            "mark", "mark NUMBER",
            "unmark", "unmark NUMBER",
            "delete", "delete NUMBER",
            "find", "find WORDS"
    ));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(DUKE_LIST_DIRECTORY).run();
    }
}
