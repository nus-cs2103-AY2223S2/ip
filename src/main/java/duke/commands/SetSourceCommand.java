package duke.commands;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.utils.Formatter;

/**
 * Represents a command that changes the data source of Duke
 */
public class SetSourceCommand extends Command {
    private final String filePath;

    /**
     * Constructs a SetSourceCommand that sets data source of Duke to a new file
     * @param filePath String representation of the new source file path
     */
    public SetSourceCommand(String filePath) {
        assert filePath != null;
        this.filePath = filePath;
    }

    /**
     * Sets new file path for storage, loads tasks from file, sets new task list.
     * If new file is empty
     * @param tasks TaskList attached to Duke
     * @param storage Storage attached to Duke
     * @return response to mainWindow
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            storage.setPath(this.filePath);
            ArrayList<Task> newTasks = storage.load();
            tasks.setList(newTasks);
            return Formatter.formatMultipleMessages("New source file successfully loaded.",
                    "You are now writing to: ", this.filePath);
        } catch (DukeException e) {
            tasks.setList(new ArrayList<>());
            return "LOADING... ERROR! Failed to load task file.";
        }
    }
}
