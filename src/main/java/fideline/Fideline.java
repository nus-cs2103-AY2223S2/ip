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

    private boolean isOn;

    private boolean isLastCommandValid;

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
            isOn = true;
        } catch (DataFileNotFoundException e) {
            ui.showLoadError(e.getMessage());
            taskManager = new TaskManager();
            storage.createDataFile();
        }
    }

    public String getIntroduction() {
        return ui.getHello();
    }

    /**
     * Gets Fideline's response to user input.
     */
    public String getResponse(String userInput) {
        try {
            Command c = Parser.getCommand(userInput);
            isOn = !c.isExit();
            String output = c.execute(taskManager, storage, ui);
            isLastCommandValid = true;
            return output;
        } catch (FidelineException e) {
            isLastCommandValid = false;
            return ui.getErrorMsg(e.getMessage());
        }
    }

    public boolean isTerminated() {
        return !isOn;
    }

    public boolean isLastCommandValid() {
        return isLastCommandValid;
    }

}
