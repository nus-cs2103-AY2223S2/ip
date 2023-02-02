package fideline;

import fideline.exception.CorruptedDataFileException;
import fideline.exception.DataFileNotFoundException;
import fideline.exception.FidelineException;
import fideline.exception.UnableToCreateDataFileException;
import fideline.execution.Command;
import fideline.save.Storage;
import fideline.task.TaskManager;
import fideline.user.Parser;
import fideline.user.Ui;

/**
 * Fideline is a chatbot that handles a list of tasks for the user.
 *
 * @author Fun Leon
 */
public class Fideline {

    /** Manages creation of new tasks and handles old ones */
    private TaskManager taskManager;

    /** Handler for storage of existing tasks locally */
    private Storage storage;

    /** Handler for display messages to the user */
    private Ui ui;


    /**
     * Boots up Fideline. Attempts to load existing saved data.
     *
     * @param filePath Path of the data file.
     * @throws CorruptedDataFileException Signals to the user that the saved
     *                                    data is corrupted and cannot be read.
     * @throws UnableToCreateDataFileException Signals to the user that saved
     *                                         data cannot be located and a
     *                                         new file is unable to be created.
     */
    public Fideline(String filePath) throws CorruptedDataFileException,
            UnableToCreateDataFileException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskManager = new TaskManager(storage.load());
        } catch (DataFileNotFoundException e) {
            ui.loadError(e.getMessage());
            taskManager = new TaskManager();
            storage.createDataFile();
        }
    }

    /**
     * Starts Fideline's program. Fideline takes in the user's input
     * until it is turned off with a "bye" command.
     */
    public void run() {
        ui.showLine();
        ui.hello();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getNextCommand();
                ui.showLine();
                Command c = Parser.getCommand(userInput);
                c.execute(taskManager, storage, ui);
                isExit = c.isExit();
            } catch (FidelineException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws CorruptedDataFileException,
            UnableToCreateDataFileException {
        new Fideline("./task-data.txt").run();
    }

}
