package berry;

import java.io.FileNotFoundException;

import berry.command.Command;
import berry.exception.BerryException;
import berry.exception.IncorrectDateException;
import berry.parser.Parser;
import berry.storage.Storage;
import berry.task.TaskList;
import berry.ui.Ui;

/**
 * Initialises the application and starts the interaction with the user.
 */
public class Berry {
    // Default path for if given file path throws an {@code InvalidStorageFilePathException}
    public static final String DEFAULT_PATH = "data/tasks.txt";

    // Functionality
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Berry() throws Storage.InvalidStorageFilePathException {
        this(DEFAULT_PATH);
    }

    /**
     * Initialises user interaction interface, storage files and task list.
     *
     * @param filePath is the file path to load data from/save data into
     * @throws Storage.InvalidStorageFilePathException if the given file path is not valid
     */
    public Berry(String filePath) throws Storage.InvalidStorageFilePathException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IncorrectDateException e) {
            ui.show(e.getMessage());
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            String berryOutput = c.execute(tasks, ui, storage);
            return berryOutput;
        } catch (BerryException e) {
            return e.getMessage();
        }
    }
}
