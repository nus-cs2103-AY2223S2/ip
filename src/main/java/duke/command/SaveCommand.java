package duke.command;

import static duke.util.Ui.getUi;

import java.io.IOException;

import duke.DukeException;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * A command to save all current tasks to the disk.
 * @author Junyi
 */
public class SaveCommand extends Command {

    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructor for SaveCommand.
     * Saves the tasks to local storage.
     * @param taskList TaskList of Duke's tasks.
     * @param storage Storage instance of Duke.
     */
    public SaveCommand(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Factory method to create save command from user input string
     * @param inputString The mentioned input string from user.
     * @param taskList TaskList of Duke's tasks.
     * @param storage Storage of Duke.
     * @return An instance of SaveCommand.
     */
    public static SaveCommand createSaveCommand(String inputString, TaskList taskList, Storage storage)
            throws DukeException {
        return new SaveCommand(taskList, storage);
    }

    @Override
    public String execute() throws DukeException {
        try {
            storage.save(taskList);
            return getUi().showSavedDataMessage();
        } catch (IOException e) {
            return "Arii can't access your files... Fix your system first.";
        }
    }
}
