package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.util.Map;
import java.util.HashMap;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The class that encompasses the main logic of running Duke, the name of the todo list
 */
public class Duke {
    static final String HOMEDIRECTORY = System.getProperty("user.dir");
    static final Path DUKELISTDIRECTORY = Paths.get(HOMEDIRECTORY, "SavedList.txt");
    static final HashMap<String, Integer> MINVALIDLENGTH = new HashMap<>(Map.of(
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
    static final HashMap<String, String> CORRECTFORMAT = new HashMap<>(Map.of(
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

    /**
     * Returns Duke with the Path specified as filePath and loads the stored tasks
     *
     * @param filePath Path of where the tasks are stored
     */
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

    /**
     * Runs Duke, allowing the user to use interact with Duke
     */
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

    /**
     * Creates Duke with the specified directory where the tasks are stored
     * In this case using the default DUKELISTDIRECTORY
     *
     * @param args String[] of any input
     */
    public static void main(String[] args) {
        new Duke(DUKELISTDIRECTORY).run();
    }
}
